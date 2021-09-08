package com.yavl.netrew.globals

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.kotcrab.vis.ui.widget.*
import com.strongjoshua.console.GUIConsole
import com.yavl.netrew.game.ConsoleCommandExecutor
import ktx.scene2d.Scene2DSkin

object Console: GUIConsole(
    Scene2DSkin.defaultSkin, true, Input.Keys.GRAVE,
    VisWindow::class.java,
    VisTable::class.java, "default-pane",
    TextField::class.java,
    VisTextButton::class.java,
    VisLabel::class.java,
    VisScrollPane::class.java) {

    init {
        Console.setCommandExecutor(ConsoleCommandExecutor())
        Console.setTitle("")
        Console.enableSubmitButton(true)
        Console.window.isMovable = false
        val consoleBgPixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        consoleBgPixmap.setColor(Color(0f, 0f, 0f, 0.6f))
        consoleBgPixmap.fill()
        val consoleBgTexture = Texture(consoleBgPixmap)
        consoleBgPixmap.dispose()
        val consoleBg = TextureRegionDrawable(TextureRegion(consoleBgTexture))
        Console.window.background = consoleBg
        Console.setSizePercent(100f, 50f)
        Console.isVisible = false
    }
}