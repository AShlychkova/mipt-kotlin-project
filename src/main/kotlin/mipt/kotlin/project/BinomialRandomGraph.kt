package mipt.kotlin.project

import java.lang.Math.pow
import  kotlinx.coroutines.*


class BinomialRandomGraph(val p: Double, val n: Int) : Graph<Int>(){

    init {
        for(i in 1..n)
        {
            addVertex(i)
            for(j in 1..(i-1)) if (bernoulliRandom(p, 100.0)==1) addEdge(i, j)
        }
    }

    fun maxDegree(): Int{
        var vertex = size()
        return  (generateSequence {(neighbors(vertex).size).takeIf{
            vertex-- > 0 }}.toList()).max()?: throw IllegalArgumentException()
    }

    fun getSubSet(setnum: Int):List<Int> {
        var listvertex = mutableListOf<Int>()
        var tmp = setnum
        for (i in 1..size()) {
            if (tmp % 2 == 1) {
                listvertex.add(i)
            }
            tmp = (tmp - (tmp % 2)) / 2
        }
        return listvertex.toList()
    }


    fun CommonDegree(listvertex: List<Int>): Int{
        var commoneighbors = neighbors(listvertex[0]).toMutableList()
        for (vertex in listvertex){
            for (commonvertex in commoneighbors){
                if ((commonvertex !in neighbors(vertex))){
                    commoneighbors.remove(commonvertex)
                }
            }
        }
        return commoneighbors.size
    }

    suspend fun maxCommonDegree(k: Int):Int?{
        var tmp: String
        var listvertex = mutableListOf<Int>()
        var jobs = mutableListOf<Job>()
        var job: Job
        var counter = pow(2.0, (size()).toDouble()).toInt()
        var common = generateSequence {(0).takeIf{ counter-- > 0 }}.toMutableList()
        for (i in 1..pow(2.0, (size()).toDouble()).toInt()){
            job = GlobalScope.launch {
                tmp = i.toString(2)
                var num = 0
                for (symb in tmp) {
                    num += 1
                }
                if (num == k){
                    listvertex = mutableListOf<Int>()
                    for (i in 1..tmp.length) {
                        if (tmp[i-1] == '1') {
                            listvertex.add(i)
                        }
                    }
                }
                common[i] = CommonDegree(listvertex)
            }
            jobs.add(job)
        }
        for (j in jobs){
            j.join()
        }
        return common.max()
    }


}

