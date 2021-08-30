package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.yavl.netrew.game.components.Mappers
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent

class MovementSystem : IteratingSystem(Family.all(TransformComponent::class.java, VelocityComponent::class.java).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = Mappers.transform.get(entity)
        val velocity = Mappers.velocity.get(entity)
        transform.pos.x += velocity.speed * velocity.direction.x
        transform.pos.y += velocity.speed * velocity.direction.y
    }
}