package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yavl.netrew.Main
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.*
import com.yavl.netrew.globals.Engine
import com.yavl.netrew.globals.Layer

fun EntityFactory.buildTree(position: Vector2, color: Color = Color.WHITE): Entity {
    val entity = Engine.createEntity()
    val transform = Engine.createComponent(TransformComponent::class.java)
    with(transform) {
        pos.x = position.x
        pos.y = position.y
    }
    val sprite = Engine.createComponent(SpriteComponent::class.java)
    with(sprite) {
        image = Image(World.treeTexture)
    }
    with(sprite.image) {
        x = transform.pos.x
        y = transform.pos.y
        setColor(color)
        Layer.trees.addActor(this)
        setZIndex(Main.stage.actors.size-1)
    }
    with(entity) {
        add(transform)
        add(sprite)
    }
    return entity
}