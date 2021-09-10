package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.globals.get

class TreeCuttingSystem : IteratingSystem(Family.all(TransformComponent::class.java).get()) {
    // WIP
    override fun processEntity(entity: Entity, deltaTime: Float) {
    }

    fun findClosestTree(entity: Entity): Entity? {
        val transform = entity.get(TransformComponent::class.java)
        val treeFamily = Family.all(TransformComponent::class.java)
        var closest: Entity? = null
        val closestDistance = Float.MAX_VALUE
        for (entity in engine.getEntitiesFor(treeFamily.get())) {
            //val treeTransform = entity.get(TreeComponent::class.java)
            //if (transform.pos.dst2(treeTransform.pos) < closestDistance) {
            //    closest = each
            //}
        }
        return closest
    }
}