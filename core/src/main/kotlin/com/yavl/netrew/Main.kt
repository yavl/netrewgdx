package com.yavl.netrew

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.I18NBundle
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.kotcrab.vis.ui.VisUI
import com.yavl.netrew.game.GameSaver
import com.yavl.netrew.game.World
import com.yavl.netrew.game.systems.*
import com.yavl.netrew.globals.Assets
import com.yavl.netrew.globals.Console
import com.yavl.netrew.globals.Engine
import com.yavl.netrew.globals.Fonts
import com.yavl.netrew.ui.Hud
import ktx.scene2d.Scene2DSkin

class Main : Game() {
    companion object {
        // Version info
        val VERSION = "0.0.1"

        lateinit var uiStage: Stage
        lateinit var stage: Stage
        lateinit var bundle: I18NBundle
        val cam = OrthographicCamera()
        lateinit var hud: Hud

        // Game time
        val DEFAULT_TIMESCALE = 1f
        var timeScale = DEFAULT_TIMESCALE
    }

    private lateinit var batch: SpriteBatch
    private lateinit var inputManager: InputManager
    private val inputs = InputMultiplexer()

    override fun create() {
        initAssets()
        VisUI.load()
        batch = SpriteBatch()
        cam.viewportWidth = Gdx.graphics.width.toFloat()
        cam.viewportHeight = Gdx.graphics.height.toFloat()
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0f)

        uiStage = Stage(ScreenViewport())
        val viewport = ScreenViewport(cam)
        stage = Stage(viewport, batch)

        inputManager = InputManager()
        inputs.addProcessor(inputManager)
        inputs.addProcessor(uiStage)
        inputs.addProcessor(stage)
        Gdx.input.inputProcessor = inputs

        bundle = Assets.get("languages/bundle")
        Scene2DSkin.defaultSkin = VisUI.getSkin()
        Scene2DSkin.defaultSkin.add("default-font", Fonts.defaultFont)

        hud = Hud()
        setScreen(hud)

        with(Engine) {
            addSystem(MovementSystem())
            addSystem(StageRenderingSystem(stage, 0))
            addSystem(SpriteRenderingSystem())
            addSystem(NameLabelRenderingSystem())
            addSystem(TreeCuttingSystem())
            addSystem(DebugLabelRenderingSystem())
        }
        World.create()

        Console.execCommand("exec autoexec.cfg")
        GameSaver.loadSettings()
    }

    override fun render() {
        ScreenUtils.clear(68 / 255f, 121 / 255f, 163f / 255f, 1f)

        cam.update()
        batch.projectionMatrix = cam.combined

        val dt = Gdx.graphics.deltaTime
        inputManager.handleInput(dt)
        Engine.update(dt)
        World.update(dt)
        super.render()

        Console.draw()
    }

    override fun dispose() {
        batch.dispose()
        Assets.dispose()
        uiStage.dispose()
        hud.dispose()
        Console.dispose()
    }

    override fun resize(width: Int, height: Int) {
        cam.viewportWidth = width.toFloat()
        cam.viewportHeight = height.toFloat()
        uiStage.viewport.update(width, height, true)
        stage.viewport.update(width, height)
        Console.window.stage.viewport.update(width, height)
        hud.resize(width, height)
    }

    private fun initAssets() {
        Assets.load("gfx/circle.png", Texture::class.java)
        Assets.load("gfx/tree.png", Texture::class.java)
        Assets.load("gfx/house.png", Texture::class.java)
        Assets.load("skins/uiskin.json", Skin::class.java)
        Assets.load("languages/bundle", I18NBundle::class.java)
        Assets.finishLoading()
    }

    override fun pause() {
        timeScale = 0f
    }

    override fun resume() {
        timeScale = DEFAULT_TIMESCALE
    }
}