package mipt.kotlin.project

import kotlin.test.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals
import  kotlinx.coroutines.*

class BinomialRandomGraphTest {
    @Test fun testBinomialRandomGraph() {
        val graph = BinomialRandomGraph(0.9, 200)
        assertTrue((graph.neighbors(3)).size/(200-1) < 0.5)
        assertTrue(graph.maxDegree() > 0)

        val graphsmall = BinomialRandomGraph(0.9, 10)
        assertEquals(graphsmall.getSubSet(9).size, 2)

        println(GlobalScope.launch {graphsmall.maxCommonDegree(3)})

    }

}