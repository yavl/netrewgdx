package com.yavl.netrew.game.components.debug

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Pool

class DebugComponent : Component, Pool.Poolable {
    var cursorPos = Vector2()

    override fun reset() {
        cursorPos.set(0f, 0f)
    }
}