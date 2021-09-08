package com.yavl.netrew.ui.windows

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.rafaskoberg.gdx.typinglabel.TypingLabel
import com.yavl.netrew.Main
import ktx.scene2d.scene2d
import ktx.scene2d.verticalGroup

class TreePopupWindow : PopupWindow() {
    init {
        val nameLabel = Label("", labelStyle)
        nameLabel.name = "nameLabel"

        val ageLabel = Label("", labelStyle)
        ageLabel.name = "ageLabel"

        val healthState = Main.bundle.format("popup.health.wounded")
        val healthString = Main.bundle.format("popup.health", healthState, "{COLOR=RED}{SHAKE}")
        val woundedLabel = TypingLabel(healthString, labelStyle)
        woundedLabel.name = "woundedLabel"
        val vGroup = scene2d.verticalGroup {
            space(verticalSpace)
            addActor(nameLabel)
            addActor(ageLabel)
            addActor(woundedLabel)
        }
        add(vGroup)
    }

    /** update label texts */
    override fun update(entity: Entity) {
        val treeAge = 0

        val ageLabel = findActor<Label>("ageLabel")
        ageLabel.setText(Main.bundle.format("popup.age", treeAge))
    }
}