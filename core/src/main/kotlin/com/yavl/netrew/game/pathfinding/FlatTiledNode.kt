/*******************************************************************************
 * Copyright 2014 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yavl.netrew.game.pathfinding

import com.badlogic.gdx.ai.pfa.Connection
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.yavl.netrew.game.World

/** A node for a [FlatTiledGraph].
 *
 * @author davebaol
 */
class FlatTiledNode(x: Int, y: Int, type: Int, connectionCapacity: Int) : TiledNode<FlatTiledNode?>(x, y, type, Array<Connection<FlatTiledNode?>>(connectionCapacity)) {
    override val index: Int
        get() = x * FlatTiledGraph.sizeY + y

    fun toWorldPos(): Vector2 {
        val tileSize = World.TILE_SIZE
        return Vector2(tileSize * x, tileSize * y)
    }
}