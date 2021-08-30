package com.yavl.netrew.game.components

import com.badlogic.ashley.core.ComponentMapper

object Mappers {
    val transform = ComponentMapper.getFor(TransformComponent::class.java)
    val velocity = ComponentMapper.getFor(VelocityComponent::class.java)
    val sprite = ComponentMapper.getFor(SpriteComponent::class.java)
    val label = ComponentMapper.getFor(LabelComponent::class.java)
}