package mipt.kotlin.project

fun bernoulliRandom(p: Double, accuracy: Double):Int {
    if ((1..accuracy.toInt()).random()/accuracy > p) return 0
    return 1

}

fun bernoulliRandomList(p: Double, accuracy: Double, n: Int): List<Int> {
    var count = n
    return (generateSequence {(bernoulliRandom(p, accuracy)).takeIf { count-- > 0 } }).toList()
}