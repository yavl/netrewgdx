package com.yavl.netrew.game.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.LabelComponent
import com.yavl.netrew.game.components.debug.DebugComponent
import com.yavl.netrew.globals.get
import com.yavl.netrew.globals.toWorldPos
import ktx.actors.txt

class DebugLabelRenderingSystem : IteratingSystem(Family.all(LabelComponent::class.java, DebugComponent::class.java).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val label = entity.get(LabelComponent::class.java)
        val debug = entity.get(DebugComponent::class.java)
        debug.cursorPos = Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()).toWorldPos()
        with(label.label) {
            txt = "${debug.cursorPos.x}, ${debug.cursorPos.y}"
            World.worldMap.getNodeByPosition(debug.cursorPos, World.TILE_SIZE)?.let { node ->
                txt += "\n${node.x}, ${node.y}"
            }
            x = debug.cursorPos.x - width / 2f
            y = debug.cursorPos.y + 50
        }
        World.worldMap.getNodeByPosition(debug.cursorPos, World.TILE_SIZE)
    }
}