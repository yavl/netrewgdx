package com.yavl.netrew.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.Align
import com.yavl.netrew.Main
import com.yavl.netrew.game.components.HumanComponent
import com.yavl.netrew.globals.Console
import com.yavl.netrew.globals.Player
import com.yavl.netrew.globals.get
import com.yavl.netrew.globals.onHover
import com.yavl.netrew.ui.windows.CharacterPopupWindow
import com.yavl.netrew.ui.windows.TreePopupWindow
import ktx.actors.onClick
import ktx.scene2d.*

class Hud : Screen {
    private val stage = Main.uiStage
    private val characterPopupWindow = CharacterPopupWindow()
    private val treePopupWindow = TreePopupWindow()
    private val timescaleString = Main.bundle.format("hud.timescale")

    val timeScaleGroup: KHorizontalGroup = scene2d.horizontalGroup {
        textButton("$timescaleString 1x").onClick {
            Console.execCommand("timescale 1")
        }
        textButton("$timescaleString 10x").onClick {
            Console.execCommand("timescale 10")
        }
        textButton("$timescaleString 20x").onClick {
            Console.execCommand("timescale 20")
        }
    }

    private val humanPickerGroup: KHorizontalGroup = scene2d.horizontalGroup()

    init {
        stage.addActor(characterPopupWindow)
        stage.addActor(treePopupWindow)
        stage.addActor(humanPickerGroup)
        updateHumanPicker()
    }

    override fun dispose() {
        characterPopupWindow.dispose()
        treePopupWindow.dispose()
    }

    override fun show() {
        showChangeTimescaleButtons()
    }

    override fun hide() {}

    override fun render(delta: Float) {
        stage.draw()
        stage.act()
    }

    override fun resize(width: Int, height: Int) {
        timeScaleGroup.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    }

    override fun pause() {}

    override fun resume() {}

    private fun showChangeTimescaleButtons() {
        timeScaleGroup.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        timeScaleGroup.align(Align.topRight)
        stage.addActor(timeScaleGroup)
    }

    fun updateHumanPicker() {
        humanPickerGroup.clear()
        for (entity in Player.humans) {
            val textButton = scene2d.textButton(entity.get(HumanComponent::class.java).name)
            humanPickerGroup.addActor(textButton)
        }
        humanPickerGroup.setOrigin(humanPickerGroup.width / 2, humanPickerGroup.height / 2)
        humanPickerGroup.setPosition(Gdx.graphics.width / 2 - (humanPickerGroup.width / 2), 15f)
        humanPickerGroup.align(Align.center)
    }
}
