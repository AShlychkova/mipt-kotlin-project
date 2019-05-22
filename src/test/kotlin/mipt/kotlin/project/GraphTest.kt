package mipt.kotlin.project

import kotlin.test.assertEquals
import kotlin.test.Test


class GraphTest {
    @Test fun testGraph() {
        val graph = Graph<Int>()

        // add vertexes
        graph.addVertex(1)
        graph.addVertex(3)
        graph.addVertex(2)
        graph.addVertex(5)

        // add edges
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(5, 3)

        assertEquals(graph.neighbors(5), listOf(3), "error in base graph")
        assertEquals(graph.neighbors(1), listOf(2, 3), "error in base graph")

    }
}