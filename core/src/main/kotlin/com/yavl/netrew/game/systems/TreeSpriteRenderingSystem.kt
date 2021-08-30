package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.Mappers
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent

class TreeSpriteRenderingSystem : IteratingSystem(Family.all(SpriteComponent::class.java, TransformComponent::class.java).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val sprite = Mappers.sprite.get(entity)
        val transform = Mappers.transform.get(entity)
        sprite.image.x = transform.pos.x - World.TILE_SIZE / 2f
        sprite.image.y = transform.pos.y
        sprite.image.rotation = transform.rotation
    }
}