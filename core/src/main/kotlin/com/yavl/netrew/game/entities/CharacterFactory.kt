package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent

class CharacterFactory {
    private val engine = World.engine

    fun build(): Entity {
        val entity = engine.createEntity()
        engine.createComponent(TransformComponent::class.java)
        engine.createComponent(VelocityComponent::class.java)
        engine.createComponent(SpriteComponent::class.java)
        return entity
    }
}