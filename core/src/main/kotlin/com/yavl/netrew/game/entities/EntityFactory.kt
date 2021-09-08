package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yavl.netrew.Main
import com.yavl.netrew.game.GameSaver
import com.yavl.netrew.game.World
import com.yavl.netrew.game.World.pathfinder
import com.yavl.netrew.game.World.worldMap
import com.yavl.netrew.game.components.Mappers
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent
import com.yavl.netrew.game.pathfinding.FlatTiledGraph
import com.yavl.netrew.game.pathfinding.Pathfinder
import com.yavl.netrew.game.pathfinding.TiledNode
import com.yavl.netrew.globals.flipY
import com.yavl.netrew.globals.onRightClick
import ktx.math.plus

object EntityFactory {
    val engine = World.engine
}