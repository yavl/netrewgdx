package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.yavl.netrew.game.components.Mappers
import com.yavl.netrew.game.components.TransformComponent

class TreeCuttingSystem : IteratingSystem(Family.all(TransformComponent::class.java).get()) {
    // WIP
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = Mappers.transform.get(entity)
    }

    fun findClosestTree(entity: Entity): Entity? {
        val transform = Mappers.transform.get(entity)
        val treeFamily = Family.all(TransformComponent::class.java)
        var closest: Entity? = null
        val closestDistance = Float.MAX_VALUE
        for (each in engine.getEntitiesFor(treeFamily.get())) {
            val treeTransform = Mappers.transform.get(each)
            if (transform.pos.dst2(treeTransform.pos) < closestDistance) {
                closest = each
            }
        }
        return closest
    }
}