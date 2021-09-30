package com.yavl.netrew.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.yavl.netrew.Main
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.entities.EntityFactory
import com.yavl.netrew.game.entities.createDebugLabel
import com.yavl.netrew.game.entities.createHuman
import com.yavl.netrew.game.entities.createTerrain
import com.yavl.netrew.game.pathfinding.*
import com.yavl.netrew.globals.*

/**
 * World class.
 *
 * This class represents game world, where new entities are created
 */
object World {
    const val TILE_SIZE = 32f
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
        createDebugLabel()
    }

    fun update(deltaTime: Float) {
    }

    fun createTerrain(mapName: String) {
        val terrain = EntityFactory.createTerrain("europe")
        Engine.addEntity(terrain)
    }

    fun createHuman(x: Float, y: Float) {
        val human = EntityFactory.createHuman()
        val transform = human.getComponent(TransformComponent::class.java)
        transform.pos.set(x, y)
        Engine.addEntity(human)
        Player.humans.add(human)
        Main.hud.updateHumanPicker()
    }

    fun createDebugLabel() {
        val debugLabel = EntityFactory.createDebugLabel()
        Engine.addEntity(debugLabel)
    }

    fun saveGame() {
        GameSaver.save("saves/autosave.bin")
    }

    fun loadGame() {
        clearEntities()
        GameSaver.load("saves/autosave.bin")
    }

    /** clear in-game interactive entities */
    fun clearEntities() {
    }
}