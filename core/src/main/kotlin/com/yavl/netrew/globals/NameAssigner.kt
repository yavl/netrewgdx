package com.yavl.netrew.globals

import com.badlogic.gdx.Gdx

object NameAssigner {
    private val path = "languages/names.txt"
    var names = mutableSetOf<String>()
    var unassignedNames = mutableSetOf<String>()
    var assignedNames = mutableSetOf<String>()

    init {
        val file = Gdx.files.internal(path)
        val contents = file.readString()
        names = contents.split('\n').toMutableSet()
    }

    fun getUnassignedName(): String {
        val randomName: String
        if (unassignedNames.size == 0)
            randomName = names.random()
        else
            randomName = unassignedNames.random()
        unassignedNames.remove(randomName)
        assignedNames.add(randomName)
        return randomName
    }

    fun retrieveName(name: String) {
        unassignedNames.add(name)
        assignedNames.remove(name)
    }
}