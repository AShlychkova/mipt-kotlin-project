package mipt.kotlin.project


open class Graph <T>{

    private data class Vertex<T>(val vertex: T) {
        val neighbors = mutableSetOf<Vertex<T>>()
    }

    private val vertices = mutableMapOf<T, Vertex<T>>()

    private operator fun get(vertex: T) = vertices[vertex] ?: throw IllegalArgumentException()

    fun addVertex(vertex: T) {
        this.vertices[vertex] = Vertex(vertex)
    }

    private fun connect(first: Vertex<T>, second: Vertex<T>) {
        first.neighbors.add(second)
        second.neighbors.add(first)
    }

    fun addEdge(first: T, second: T) = connect(this[first], this[second])

    fun neighbors(vertex: T) = vertices[vertex]?.neighbors?.map { it.vertex } ?: listOf()

    fun size(): Int = vertices.size

    fun indusedSubGraph(vertexes: List<T>):Graph<T>{
        var indused = Graph <T>()
        for (vertex in vertexes){
            indused.addVertex(vertex)
        }
        for (vertex in vertexes){
            for(neighbor in indused.neighbors(vertex)){
                if ( neighbor in vertexes) indused.addEdge(neighbor, vertex)
            }
        }
        return indused
    }

}



