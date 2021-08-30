package com.yavl.netrew.game

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.yavl.netrew.*
import com.yavl.netrew.game.pathfinding.*

/**
 * World class.
 *
 * This class represents game world, where new entities are created
 */
class World(val engine: Engine) {
    companion object {
        const val TILE_SIZE = 32f
        val engine = PooledEngine()
    }
    lateinit var characterTexture: Texture
    lateinit var treeTexture: Texture
    lateinit var houseTexture: Texture
    lateinit var worldMap: FlatTiledGraph
    val gameSaver = GameSaver()
    lateinit var pathfinder: Pathfinder
    lateinit var coloredBorders: Array<Array<Color?>>
    val shapeRenderer = ShapeRenderer()
    var showBorders = true

    fun create() {
        characterTexture = Globals.assets.get<Texture>("gfx/circle.png")
        characterTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        treeTexture = Globals.assets.get<Texture>("gfx/tree.png")
        treeTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        houseTexture = Globals.assets.get<Texture>("gfx/house.png")
        houseTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)

        createTerrain("europe")
        coloredBorders = Array(512) { row ->
            Array(512) { col ->
                null
            }
        }
    }

    fun update(deltaTime: Float) {
        if (!showBorders)
            return
        for (x in 0 until 512) {
            for (y in 0 until 512) {
                if (coloredBorders[x][y] == null)
                    continue
                if (coloredBorders[x][y]!!.a <= 0.1f)
                    continue
                val color = coloredBorders[x][y]
                Gdx.gl.glEnable(GL20.GL_BLEND)
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
                shapeRenderer.projectionMatrix = Globals.cam.combined
                shapeRenderer.setColor(color)
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
                shapeRenderer.rect(worldMap[x, y].toWorldPos(TILE_SIZE).x, worldMap[x, y].toWorldPos(TILE_SIZE).y, World.TILE_SIZE, World.TILE_SIZE)
                shapeRenderer.end()
            }
        }
    }

    fun createTerrain(mapName: String) {
    }

    fun onTerrainRightClick() {
    }

    fun saveGame() {
        gameSaver.save("saves/autosave.bin", engine)
    }

    fun loadGame() {
        clearEntities()
        gameSaver.load("saves/autosave.bin")
        Globals.clickedCharacter = null
    }

    /** clear in-game interactive entities */
    fun clearEntities() {
    }
}