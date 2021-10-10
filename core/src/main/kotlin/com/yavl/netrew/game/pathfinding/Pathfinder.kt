package com.yavl.netrew.game.pathfinding

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ai.pfa.PathSmoother
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder
import com.badlogic.gdx.math.Vector2
import com.yavl.netrew.game.World
import com.yavl.netrew.game.components.TransformComponent
import com.yavl.netrew.game.components.VelocityComponent
import com.yavl.netrew.globals.Console
import com.yavl.netrew.globals.get
import ktx.math.minus
import ktx.math.plusAssign

/** Wrapper class around gdx-ai pathfinding */
class Pathfinder(worldMap: FlatTiledGraph) {
    val path = TiledSmoothableGraphPath<FlatTiledNode>()
    private val pathfinder = IndexedAStarPathFinder<FlatTiledNode>(worldMap, true)
    private val pathSmoother = PathSmoother<FlatTiledNode, Vector2>(TiledRaycastCollisionDetector<FlatTiledNode?>(worldMap))
    private val heuristic = TiledManhattanDistance<FlatTiledNode>()

    /** Find path between startNode and endNode */
    fun searchPath(startNode: FlatTiledNode, endNode: FlatTiledNode): Boolean {
        path.clear()
        val found = pathfinder.searchNodePath(startNode, endNode, heuristic, path)
        pathSmoother.smoothPath(path)
        return found
    }

    fun moveEntityTo(entity: Entity, endNode: FlatTiledNode) {
        val TILE_SIZE = World.TILE_SIZE
        val velocity = entity.get(VelocityComponent::class.java)
        val pos = entity.get(TransformComponent::class.java).pos
        velocity.targetPositions.clear()
        World.grid.getNodeByPosition(Vector2(pos.x, pos.y))?.let { startNode ->
            if (searchPath(startNode, endNode) && startNode != endNode) {
                val path = this.path
                velocity.hasTargetPosition = true
                velocity.targetPosition = path[1].toWorldPos()
                val offsetXY = TILE_SIZE / 2f
                velocity.targetPosition += offsetXY
                velocity.direction = (velocity.targetPosition - pos).nor()

                for (i in 1 until path.getCount()) {
                    val each = path[i]
                    val targetPos = each.toWorldPos()
                    targetPos.set(targetPos.x + offsetXY, targetPos.y + offsetXY)
                    velocity.targetPositions.add(targetPos)
                    Console.log("${each.toWorldPos().x}, ${each.toWorldPos().y}")
                }
            }
        }
    }
}