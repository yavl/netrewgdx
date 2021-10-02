package com.yavl.netrew.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.strongjoshua.console.CommandExecutor
import com.strongjoshua.console.annotation.ConsoleDoc
import com.yavl.netrew.Main
import com.yavl.netrew.globals.toWorldPos
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ConsoleCommandExecutor() : CommandExecutor() {
    @ConsoleDoc(description = "Shows time in system time zone.")
    fun time() {
        console.log("Current time: " + DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault()).format(Instant.now()).toString())
    }

    @ConsoleDoc(description = "Show game timescale.")
    fun timescale() {
        console.log("Timescale is set to ${Main.timeScale}x")
    }

    @ConsoleDoc(description = "Set game timescale.")
    fun timescale(scale: Float) {
        Main.timeScale = scale
        console.log("Timescale is set to ${scale}x")
    }

    @ConsoleDoc(description = "Set camera position (x, y).")
    fun cam(x: Float, y: Float) {
        Main.cam.position.set(x, y, 0f)
    }

    @ConsoleDoc(description = "Set camera zoom.")
    fun zoom(zoom: Float) {
        Main.cam.zoom = zoom
    }

    @ConsoleDoc(description = "Show debug info.")
    fun debug(enabled: Boolean) {
        Main.stage.isDebugAll = enabled
        Main.uiStage.isDebugAll = enabled
    }

    @ConsoleDoc(description = "Show version info.")
    fun version() {
        console.log(Main.VERSION)
    }

    @ConsoleDoc(description = "Show cursor world pos (x, y).")
    fun cursor() {
        console.log("${Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()).toWorldPos()}")
    }

    @ConsoleDoc(description = "Spawn human at cursor pos.")
    fun spawn() {
        val pos = Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()).toWorldPos()
        World.createHuman(pos)
    }

    @ConsoleDoc(description = "Spawn tree at cursor pos.")
    fun tree() {
        val node = World.grid.getNodeByPosition(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()).toWorldPos(), World.TILE_SIZE)
    }

    @ConsoleDoc(description = "Spawn house at cursor pos.")
    fun house() {
        val node = World.grid.getNodeByPosition(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()).toWorldPos(), World.TILE_SIZE)
    }

    @ConsoleDoc(description = "Save game.")
    fun save() {
        World.saveGame()
    }

    @ConsoleDoc(description = "Load game.")
    fun load() {
        World.loadGame()
    }

    @ConsoleDoc(description = "Remove all characters.")
    fun clear() {
        World.clearEntities()
    }

    @ConsoleDoc(description = "Execute commands from file.")
    fun exec(path: String) {
        val file = Gdx.files.internal(path)
        val contents = file.readString()
        val commands = contents.split("\\r?\\n")
        for (command in commands) {
            console.execCommand(command)
        }
        console.log("All commands from <$path> were executed.")
    }

    @ConsoleDoc(description = "Set fullscreen.")
    fun fullscreen() {
        val displayMode = Gdx.graphics.displayMode
        Gdx.graphics.setFullscreenMode(displayMode)
    }
}