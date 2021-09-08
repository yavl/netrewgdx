package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent

fun EntityFactory.createHuman(): Entity {
    val entity = EntityFactory.engine.createEntity()
    val transform = EntityFactory.engine.createComponent(TransformComponent::class.java)
    val velocity = EntityFactory.engine.createComponent(VelocityComponent::class.java)
    val sprite = EntityFactory.engine.createComponent(SpriteComponent::class.java)
    with(sprite) {
        image = Image(World.characterTexture)
    }
    entity.add(transform)
    entity.add(velocity)
    entity.add(sprite)
    return entity
}