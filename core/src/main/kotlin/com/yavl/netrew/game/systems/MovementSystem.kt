package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.yavl.netrew.Main
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent
import com.yavl.netrew.game.pathfinding.TiledNode
import com.yavl.netrew.globals.get
import ktx.math.minus

class MovementSystem : IteratingSystem(Family.all(TransformComponent::class.java, VelocityComponent::class.java).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity.get(TransformComponent::class.java)
        val velocity = entity.get(VelocityComponent::class.java)

        if (velocity.hasTargetPosition) {
            velocity.speed = velocity.MAX_SPEED * Main.timeScale * deltaTime
        }

        World.grid.getNodeByPosition(transform.pos)?.let { node ->
            if (node.type == TiledNode.TILE_WATER) {
                velocity.speed = velocity.WATER_SPEED * Main.timeScale * deltaTime
            }
        }

        if (velocity.hasTargetPosition && velocity.targetPosition.dst(transform.pos) <= velocity.speed) {
            if (velocity.targetPositions.size > 0) {
                velocity.targetPosition = velocity.targetPositions[0]
                velocity.direction = (velocity.targetPosition - transform.pos).nor()
                velocity.targetPositions.removeIndex(0)
            }
            else {
                transform.pos.set(velocity.targetPosition)
                velocity.hasTargetPosition = false
                velocity.speed = 0f
                velocity.targetPositions.clear()
            }
        }

        velocity.previousPosition = transform.pos
        transform.pos.x += velocity.speed * velocity.direction.x
        transform.pos.y += velocity.speed * velocity.direction.y
    }
}