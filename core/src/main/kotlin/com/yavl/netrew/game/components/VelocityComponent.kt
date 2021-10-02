package com.yavl.netrew.game.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Pool
import com.badlogic.gdx.utils.Array

/**
 * @param speed pixels per second
 */
class VelocityComponent : Component, Pool.Poolable {
    var speed = 0f
    var maxSpeed = 100f
    var direction = Vector2(0f, 0f)

    // vars for pathfinding
    var hasTargetPosition = false
    var targetPosition = Vector2.Zero
    var targetPositions = Array<Vector2>()

    override fun reset() {
        speed = 0f
        maxSpeed = 100f
        direction.set(0f, 0f)
        hasTargetPosition = false
        targetPosition = Vector2.Zero
        targetPositions.clear()
    }
}