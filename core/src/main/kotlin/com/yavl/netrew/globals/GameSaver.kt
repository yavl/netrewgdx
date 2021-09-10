package com.yavl.netrew.game

import com.badlogic.ashley.core.Family
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.Input
import com.esotericsoftware.kryo.io.Output
import com.yavl.netrew.Main
import com.yavl.netrew.game.components.HumanComponent
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent
import com.yavl.netrew.globals.Assets
import com.yavl.netrew.globals.Engine
import com.yavl.netrew.globals.get
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * A class to save and load game progress and settings
 */
object GameSaver {
    val kryo = Kryo()

    init {
        kryo.register(TransformComponent::class.java)
        kryo.register(VelocityComponent::class.java)
        kryo.register(Int::class.java)
    }

    fun save(path: String) {
        val output = Output(FileOutputStream(path))

        // write humans
        run {
            val humanFamily = Family.all(
                TransformComponent::class.java,
                VelocityComponent::class.java,
                HumanComponent::class.java
            )
            val count = Engine.getEntitiesFor(humanFamily.get()).size()
            kryo.writeObject(output, count)
            for (entity in Engine.getEntitiesFor(humanFamily.get())) {
                val transform = entity.get(TransformComponent::class.java)
                val velocity = entity.get(VelocityComponent::class.java)
                val human = entity.get(HumanComponent::class.java)

                kryo.writeObject(output, transform)
                kryo.writeObject(output, velocity)
            }
        }

        // write houses
        run {
            val houseFamily = Family.all(TransformComponent::class.java)
            val count = Engine.getEntitiesFor(houseFamily.get()).size()
            kryo.writeObject(output, count)
            for (entity in Engine.getEntitiesFor(houseFamily.get())) {
                val transform = entity.get(TransformComponent::class.java)

                kryo.writeObject(output, transform)
            }
        }

        // write trees
        run {
            val treeFamily = Family.all(TransformComponent::class.java)
            val count = Engine.getEntitiesFor(treeFamily.get()).size()
            kryo.writeObject(output, count)
            for (entity in Engine.getEntitiesFor(treeFamily.get())) {
                val transform = entity.get(TransformComponent::class.java)

                kryo.writeObject(output, transform)
            }
        }

        output.close()
    }

    fun load(path: String) {
        val input = Input(FileInputStream(path))

        try {
            // read characters
            run {
                val charactersCount = kryo.readObject(input, Int::class.java)
                for (entity in 0 until charactersCount) {
                    val transform = kryo.readObject(input, TransformComponent::class.java)
                    val velocity = kryo.readObject(input, VelocityComponent::class.java)
                }
            }

            // read houses
            run {
                val housesCount = kryo.readObject(input, Int::class.java)
                for (entity in 0 until housesCount) {
                    val transform = kryo.readObject(input, TransformComponent::class.java)

                    val node = World.worldMap.getNodeByPosition(transform.pos, World.TILE_SIZE)
                }
            }

            // read trees
            run {
                val treesCount = kryo.readObject(input, Int::class.java)
                for (entity in 0 until treesCount) {
                    val transform = kryo.readObject(input, TransformComponent::class.java)

                    val node = World.worldMap.getNodeByPosition(transform.pos, World.TILE_SIZE)
                }
            }
        } catch(e: Exception) {
            println(e.message)
        }
    }

    fun saveSettings() {
        val prefs = Gdx.app.getPreferences("NetrewPreferences")
        prefs.putFloat("cameraPosX", Main.cam.position.x)
        prefs.putFloat("cameraPosY", Main.cam.position.y)
        prefs.putFloat("cameraZoom", Main.cam.zoom)
        prefs.flush()
    }

    fun loadSettings() {
        val prefs = Gdx.app.getPreferences("NetrewPreferences")
        val x = prefs.getFloat("cameraPosX", 0f)
        val y = prefs.getFloat("cameraPosY", 0f)
        val zoom = prefs.getFloat("cameraZoom", 1f)
        Main.cam.position.set(x, y, 0f)
        Main.cam.zoom = zoom
    }

    /** Load map assets
     * @param mapName map folder name
     * @return map name and textures */
    fun loadMap(mapName: String): MapData {
        Assets.load("maps/$mapName/heightmap.png", Texture::class.java)
        Assets.load("maps/$mapName/terrain.png", Texture::class.java)
        Assets.load("maps/$mapName/population.png", Texture::class.java)
        Assets.finishLoading()
        val terrainTexture = Assets.get<Texture>("maps/$mapName/terrain.png")
        val heightmapTexture = Assets.get<Texture>("maps/$mapName/heightmap.png")
        val populationTexture = Assets.get<Texture>("maps/$mapName/population.png")

        return MapData(mapName, terrainTexture, heightmapTexture, populationTexture)
    }
}

/** Data class that stores map data */
data class MapData(val mapName: String, val terrainTexture: Texture, val heightmapTexture: Texture, val populationTexture: Texture)