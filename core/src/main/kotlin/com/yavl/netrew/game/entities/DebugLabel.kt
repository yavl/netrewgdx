package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.yavl.netrew.Main
import com.yavl.netrew.game.components.LabelComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.debug.DebugComponent
import com.yavl.netrew.globals.Engine
import ktx.actors.txt

fun EntityFactory.createDebugLabel(): Entity {
    val entity = Engine.createEntity()
    val transform = Engine.createComponent(TransformComponent::class.java)
    val labelComponent = Engine.createComponent(LabelComponent::class.java)
    with(labelComponent.label) {
        txt = "text"
        Main.stage.addActor(this)
    }
    val debugComponent = Engine.createComponent(DebugComponent::class.java)
    with(entity) {
        add(transform)
        add(labelComponent)
        add(debugComponent)
    }
    return entity
}