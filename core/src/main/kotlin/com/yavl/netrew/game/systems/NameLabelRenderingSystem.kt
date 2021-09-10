package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.yavl.netrew.game.components.LabelComponent
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.globals.get

class NameLabelRenderingSystem : IteratingSystem(Family.all(
        LabelComponent::class.java,
        TransformComponent::class.java,
        SpriteComponent::class.java).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val pos = entity.get(TransformComponent::class.java).pos
        val labelComponent = entity.get(LabelComponent::class.java)
        val sprite = entity.get(SpriteComponent::class.java)

        val label = labelComponent.label
        label.x = pos.x - label.width / 2f
        label.y = pos.y - label.height - sprite.image.height / 2f
        // work in progress
    }
}