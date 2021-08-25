package com.yavl.netrew.ui.windows

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.yavl.netrew.Globals
import com.yavl.netrew.game.components.Mappers
import com.rafaskoberg.gdx.typinglabel.TypingLabel
import ktx.scene2d.scene2d
import ktx.scene2d.verticalGroup

class CharacterPopupWindow : PopupWindow() {
    init {
        val nameLabel = Label("", labelStyle)
        nameLabel.name = "nameLabel"

        val ageLabel = Label("", labelStyle)
        ageLabel.name = "ageLabel"

        val healthState = Globals.bundle.format("popup.health.wounded")
        val healthString = Globals.bundle.format("popup.health", healthState, "{COLOR=RED}{SHAKE}")
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
        if (Mappers.character.get(entity) == null) // todo fix why is characterComponent null on nameLabel hover
            return
        val characterName = Mappers.character.get(entity).name
        val characterAge = 0

        val nameLabel = findActor<Label>("nameLabel")
        nameLabel.setText(Globals.bundle.format("popup.name", characterName))

        val ageLabel = findActor<Label>("ageLabel")
        ageLabel.setText(Globals.bundle.format("popup.age", characterAge))
    }
}