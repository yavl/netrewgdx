package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.yavl.netrew.Main
import com.yavl.netrew.game.GameSaver
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.pathfinding.FlatTiledGraph
import com.yavl.netrew.game.pathfinding.FlatTiledNode
import com.yavl.netrew.game.pathfinding.Pathfinder
import com.yavl.netrew.game.pathfinding.TiledNode
import com.yavl.netrew.globals.*
import ktx.math.plus

fun EntityFactory.buildTerrain(mapName: String): Entity {
    val entity = Engine.createEntity()
    val mapData = GameSaver.loadMap(mapName)
    val terrainTexture = mapData.terrainTexture
    val heightmapTexture = mapData.heightmapTexture
    if (!heightmapTexture.textureData.isPrepared) {
        heightmapTexture.textureData.prepare()
    }
    val heightmapPixmap = heightmapTexture.textureData.consumePixmap()
    heightmapPixmap.flipY()

    val transform = Engine.createComponent(TransformComponent::class.java)
    transform.pos.set(terrainTexture.width / 2f, terrainTexture.height / 2f)
    transform.scale.set(World.TILE_SIZE, World.TILE_SIZE)
    entity.add(transform)

    val sprite = Engine.createComponent(SpriteComponent::class.java)
    sprite.image = Image(terrainTexture)
    with(sprite.image) {
        setScale(transform.scale.x, transform.scale.y)
        onRightClick {
            Player.selectedHuman?.let {  human ->
                World.grid.getNodeByPosition(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()).toWorldPos(), World.TILE_SIZE)?.let { endNode ->
                    World.pathfinder.moveEntityTo(human, endNode)
                }
            }
        }
    }
    entity.add(sprite)
    Main.stage.addActor(sprite.image)

    World.grid = FlatTiledGraph()
    World.grid.init(heightmapPixmap)
    heightmapPixmap.dispose()
    World.pathfinder = Pathfinder(World.grid)

    /// create TILE TYPES labels
    /*
    run {
        for (x in 0 until FlatTiledGraph.sizeX) {
            for (y in 0 until FlatTiledGraph.sizeY) {
                val label = Label(worldMap.getNode(x, y).type.toString(), Scene2DSkin.defaultSkin)
                label.setPosition(x * World.TILE_SIZE, y * World.TILE_SIZE)
                if (worldMap.getNode(x, y).type == 1)
                    Main.stage.addActor(label)
            }
        }
    }
     */


    /// spawn trees according to heightmap
    for (x in 0 until FlatTiledGraph.sizeX) {
        for (y in 0 until FlatTiledGraph.sizeY) {
            if (World.grid[x, y]?.type == TiledNode.TILE_TREE) {
                //createTree()
            }
        }
    }

    /// spawn characters according to population map
    val populationTexture = mapData.populationTexture
    if (!populationTexture.textureData.isPrepared) {
        populationTexture.textureData.prepare()
    }
    val populationPixmap = populationTexture.textureData.consumePixmap()
    populationPixmap.flipY()
    for (x in 0 until populationPixmap.width) {
        for (y in 0 until populationPixmap.height) {
            val color = Color(populationPixmap.getPixel(x, y))
            if (color != Color.BLACK) {
                World.grid[x, y]?.let { node ->
                    var pos = node.toWorldPos(World.TILE_SIZE)
                    val offsetXY = World.TILE_SIZE / 2f
                    pos += offsetXY
                    World.createHuman(pos, color)
                }
            }
        }
    }
    populationPixmap.dispose()
    return entity
}