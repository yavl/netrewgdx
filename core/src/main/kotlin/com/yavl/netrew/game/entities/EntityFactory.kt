package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yavl.netrew.Main
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent

object EntityFactory {
    private val engine = World.engine

    fun createHuman(): Entity {
        val entity = engine.createEntity()
        val transform = engine.createComponent(TransformComponent::class.java)
        val velocity = engine.createComponent(VelocityComponent::class.java)
        val sprite = engine.createComponent(SpriteComponent::class.java)
        with(sprite) {
            image = Image(World.characterTexture)
        }
        entity.add(transform)
        entity.add(velocity)
        entity.add(sprite)
        return entity
    }
}