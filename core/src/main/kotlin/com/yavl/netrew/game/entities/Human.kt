package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yavl.netrew.Main
import com.yavl.netrew.globals.NameAssigner
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.*
import com.yavl.netrew.globals.Console
import com.yavl.netrew.globals.Engine
import ktx.actors.onClick
import ktx.actors.txt

fun EntityFactory.buildHuman(position: Vector2 = Vector2.Zero, color: Color = Color.WHITE): Entity {
    val entity = Engine.createEntity()
    val transform = Engine.createComponent(TransformComponent::class.java)
    with(transform) {
        pos.x = position.x
        pos.y = position.y
    }
    val velocity = Engine.createComponent(VelocityComponent::class.java)
    val sprite = Engine.createComponent(SpriteComponent::class.java)
    with(sprite) {
        image = Image(World.characterTexture)
    }
    with(sprite.image) {
        x = transform.pos.x
        y = transform.pos.y
        setColor(color)
        onClick {
            Console.log("${transform.pos.x} : ${transform.pos.y}, ${sprite.image.x} : ${sprite.image.y}")
        }
        Main.stage.addActor(this)
    }
    val humanComponent = Engine.createComponent(HumanComponent::class.java)
    humanComponent.name = NameAssigner.getUnassignedName()
    val labelComponent = Engine.createComponent(LabelComponent::class.java)
    with(labelComponent.label) {
        txt = humanComponent.name
        Main.stage.addActor(this)
    }
    with(entity) {
        add(transform)
        add(velocity)
        add(sprite)
        add(humanComponent)
        add(labelComponent)
    }
    return entity
}