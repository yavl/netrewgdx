package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yavl.netrew.Main
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.globals.Engine

fun EntityFactory.createHouse(): Entity {
    val entity = Engine.createEntity()
    val transform = Engine.createComponent(TransformComponent::class.java)
    val sprite = Engine.createComponent(SpriteComponent::class.java)
    with(sprite) {
        image = Image(World.houseTexture)
    }
    Main.stage.addActor(sprite.image)
    with(entity) {
        add(transform)
        add(sprite)
    }
    return entity
}