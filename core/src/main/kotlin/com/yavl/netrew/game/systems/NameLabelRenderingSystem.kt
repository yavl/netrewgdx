package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.yavl.netrew.game.components.Mappers
import com.yavl.netrew.game.components.LabelComponent
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent

class NameLabelRenderingSystem : IteratingSystem(Family.all(
        LabelComponent::class.java,
        TransformComponent::class.java,
        SpriteComponent::class.java).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = Mappers.transform.get(entity)
        val labelComponent = Mappers.label.get(entity)
        val sprite = Mappers.sprite.get(entity)

        val label = labelComponent.label
        label.x = transform.pos.x - label.width / 2f
        label.y = transform.pos.y - label.height - sprite.image.height / 2f
        // work in progress
    }
}