package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yavl.netrew.Main
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.HumanComponent
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent
import com.yavl.netrew.globals.Console
import com.yavl.netrew.globals.Engine
import com.yavl.netrew.globals.toWorldPos
import ktx.actors.onClick

fun EntityFactory.createHuman(): Entity {
    val entity = Engine.createEntity()
    val transform = Engine.createComponent(TransformComponent::class.java)
    val velocity = Engine.createComponent(VelocityComponent::class.java)
    val sprite = Engine.createComponent(SpriteComponent::class.java)
    with(sprite) {
        image = Image(World.characterTexture)
    }
    with(sprite.image) {
        onClick {
            Console.log("${transform.pos.x} : ${transform.pos.y}, ${sprite.image.x} : ${sprite.image.y}")
        }
    }
    Main.stage.addActor(sprite.image)
    val humanComponent = Engine.createComponent(HumanComponent::class.java)
    with(entity) {
        add(transform)
        add(velocity)
        add(sprite)
        add(humanComponent)
    }
    return entity
}