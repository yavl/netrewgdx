package com.yavl.netrew.game.entities

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.yavl.netrew.Main
import com.yavl.netrew.game.GameSaver
import com.yavl.netrew.game.World
import com.yavl.netrew.game.World.worldMap
import com.yavl.netrew.game.components.Mappers
import com.yavl.netrew.game.components.SpriteComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.pathfinding.FlatTiledGraph
import com.yavl.netrew.game.pathfinding.Pathfinder
import com.yavl.netrew.game.pathfinding.TiledNode
import com.yavl.netrew.globals.flipY
import com.yavl.netrew.globals.onRightClick
import ktx.math.plus
import ktx.scene2d.Scene2DSkin

fun EntityFactory.createTerrain(mapName: String): Entity {
    val entity = engine.createEntity()
    val mapData = GameSaver.loadMap(mapName)
    val terrainTexture = mapData.terrainTexture
    val heightmapTexture = mapData.heightmapTexture
    if (!heightmapTexture.textureData.isPrepared) {
        heightmapTexture.textureData.prepare()
    }
    val heightmapPixmap = heightmapTexture.textureData.consumePixmap()
    heightmapPixmap.flipY()

    val transform = engine.createComponent(TransformComponent::class.java)
    transform.pos.set(terrainTexture.width / 2f, terrainTexture.height / 2f)
    transform.scale.set(World.TILE_SIZE, World.TILE_SIZE)
    entity.add(transform)

    val sprite = engine.createComponent(SpriteComponent::class.java)
    sprite.image = Image(terrainTexture)
    with(sprite.image) {
        setScale(transform.scale.x, transform.scale.y)
        onRightClick {
        }
    }
    entity.add(sprite)
    Main.stage.addActor(Mappers.sprite.get(entity).image)

    World.worldMap = FlatTiledGraph()
    World.worldMap.init(heightmapPixmap)
    heightmapPixmap.dispose()
    World.pathfinder = Pathfinder(World.worldMap)

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
            if (World.worldMap[x, y].type == TiledNode.TILE_TREE) {
                //createTree(x, y)
            }
        }
    }

    /// spawn trees according to population map
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
                var pos = World.worldMap[x, y].toWorldPos(World.TILE_SIZE)
                val offsetXY = World.TILE_SIZE / 2f
                pos += offsetXY
                //createCharacter(pos, color)
            }
        }
    }
    populationPixmap.dispose()
    return entity
}