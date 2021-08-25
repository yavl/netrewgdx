package com.yavl.netrew.net

import com.esotericsoftware.kryonet.Client
import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Listener
import com.yavl.netrew.net.requests.ChatMessage

class GameClient(ip: String, tcpPort: Int, udpPort: Int) : Listener {
    val client = Client()
    val timeout = 5000

    init {
        client.kryo.register(ChatMessage::class.java)
        client.start()
        client.connect(timeout, ip, tcpPort, udpPort)
        client.addListener(this)
    }

    override fun received(connection: Connection, obj: Any) {
        if (obj is ChatMessage) {

        }
    }

    fun disconnect() {
        client.stop()
    }
}