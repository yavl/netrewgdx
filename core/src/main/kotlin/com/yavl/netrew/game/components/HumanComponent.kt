package com.yavl.netrew.game.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

class HumanComponent() : Component, Pool.Poolable {
    var name = "defaultName"

    override fun reset() {
        name = "defaultName"
    }
}