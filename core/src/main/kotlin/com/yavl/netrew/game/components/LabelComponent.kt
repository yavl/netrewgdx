package com.yavl.netrew.game.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Pool
import com.yavl.netrew.globals.Fonts

class LabelComponent() : Component, Pool.Poolable {
    var label = Label("default", Label.LabelStyle(Fonts.characterFont, Color.WHITE))

    init {
        label.setOrigin(Align.center)
        label.style.font.setUseIntegerPositions(false)
        label.setAlignment(Align.center)
    }

    override fun reset() {
        label.remove()
    }
}