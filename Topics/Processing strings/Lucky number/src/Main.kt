fun main() {
    val num = readln()

    val part1 = num.substring(0, num.length/2)
    val part2 = num.substring(num.length/2)

    var sum1 = 0
    var sum2 = 0

    for (ch in part1) {
        sum1 += ch.code
    }

    for (ch in part2) {
        sum2 += ch.code
    }

    if (sum1 == sum2) {
        println("YES")
    } else {
        println("NO")
    }
}