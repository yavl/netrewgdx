package com.yavl.netrew

import kotlin.jvm.JvmStatic
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import org.lwjgl.opengl.Display

/** Launches the desktop (LWJGL3) application.  */
object Lwjgl3Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        createApplication()
    }

    private fun createApplication(): LwjglApplication {
        return LwjglApplication(Main(), defaultConfiguration)
    }

    private val defaultConfiguration: LwjglApplicationConfiguration
        private get() {
            // temporarily use lwjgl2 since lwjgl3 crashes on M1 macs (Rosetta 2)
            val config = LwjglApplicationConfiguration()
            config.title = "Netrew"
            config.width = 800
            config.height = 600
            config.resizable = false
            config.useHDPI = true
            config.forceExit = false
            return config
        }
}