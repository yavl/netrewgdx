package com.yavl.netrew.game

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.yavl.netrew.game.entities.EntityFactory
import com.yavl.netrew.game.entities.createTerrain
import com.yavl.netrew.game.pathfinding.*
import com.yavl.netrew.globals.Assets

/**
 * World class.
 *
 * This class represents game world, where new entities are created
 */
object World {
    const val TILE_SIZE = 32f
    val engine = PooledEngine()
    lateinit var characterTexture: Texture
    lateinit var treeTexture: Texture
    lateinit var houseTexture: Texture
    lateinit var worldMap: FlatTiledGraph
    lateinit var pathfinder: Pathfinder
    lateinit var coloredBorders: Array<Array<Color?>>

    fun create() {
        characterTexture = Assets.get<Texture>("gfx/circle.png")
        characterTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        treeTexture = Assets.get<Texture>("gfx/tree.png")
        treeTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        houseTexture = Assets.get<Texture>("gfx/house.png")
        houseTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)

        createTerrain("europe")
        coloredBorders = Array(512) { row ->
            Array(512) { col ->
                null
            }
        }
    }

    fun update(deltaTime: Float) {
    }

    fun createTerrain(mapName: String) {
        val terrain = EntityFactory.createTerrain("europe")
        engine.addEntity(terrain)
    }

    fun saveGame() {
        GameSaver.save("saves/autosave.bin", engine)
    }

    fun loadGame() {
        clearEntities()
        GameSaver.load("saves/autosave.bin")
    }

    /** clear in-game interactive entities */
    fun clearEntities() {
    }
}