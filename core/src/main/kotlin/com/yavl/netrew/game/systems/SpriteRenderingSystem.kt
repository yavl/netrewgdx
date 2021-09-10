package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.globals.get

class SpriteRenderingSystem : IteratingSystem(Family.all(SpriteComponent::class.java, TransformComponent::class.java).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val image = entity.get(SpriteComponent::class.java).image
        val transform = entity.get(TransformComponent::class.java)
        image.x = transform.pos.x - image.width / 2f
        image.y = transform.pos.y - image.height / 2f
        image.rotation = transform.rotation
    }
}