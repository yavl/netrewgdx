package com.yavl.netrew.globals

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.yavl.netrew.Main

/// Kotlin extensions below

/** Mouse pos to world pos */
fun Vector2.toWorldPos(): Vector2 {
    val cam = Main.cam
    val worldPos = Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
    val vec = cam.unproject(worldPos)
    return Vector2(vec.x, vec.y)
}

fun Pixmap.flipY() {
    val temp = Pixmap(width, height, format)
    for (x in 0 until width) {
        for (y in 0 until height) {
            temp.drawPixel(x, y, getPixel(x, height - 1 - y))
        }
    }
    for (x in 0 until width) {
        for (y in 0 until height) {
            setColor(temp.getPixel(x, y))
            drawPixel(x, y)
        }
    }
    temp.dispose()
}

fun <T : Component> Entity.hasComponent(componentType: Class<T>): Boolean {
    return ComponentMapper.getFor(componentType).get(this) != null
}

fun <T : Component> Entity.get(componentType: Class<T>): T {
    return ComponentMapper.getFor(componentType).get(this)
}

inline fun <T : Actor> T.onHover(crossinline listener: T.() -> Unit): ClickListener {
    val clickListener = object : ClickListener() {
        override fun enter(event: InputEvent, x: Float, y: Float, pointer: Int, fromActor: Actor?) = listener()
        override fun isOver(actor: Actor?, x: Float, y: Float): Boolean {
            return super.isOver(actor, x, y)
        }
    }
    addListener(clickListener)
    return clickListener
}

inline fun <T : Actor> T.onHoverEnd(crossinline listener: T.() -> Unit): ClickListener {
    val clickListener = object : ClickListener() {
        override fun exit(event: InputEvent, x: Float, y: Float, pointer: Int, fromActor: Actor?) = listener()
    }
    addListener(clickListener)
    return clickListener
}

inline fun <T : Actor> T.onRightClick(crossinline listener: T.() -> Unit): ClickListener {
    val clickListener = object : ClickListener(Input.Buttons.RIGHT) {
        override fun clicked(event: InputEvent, x: Float, y: Float) = listener()
    }
    addListener(clickListener)
    return clickListener
}