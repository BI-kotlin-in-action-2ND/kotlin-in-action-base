package kr.bi

/*
 * https://www.acmicpc.net/problem/15649
 * N과 M (1)
 * 문제
    자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
    1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열

    입력
    첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

    출력
    한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
    수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 */
fun main(args: Array<String>) {
    val s = readln().split(' ')
    val n = s[0].toInt()
    val m = s[1].toInt()

    println(permutation(n, m))
}

fun permutation(
    n: Int,
    m: Int,
): String {
    val arr = Array(m) { 0 }
    val visited = Array(n) { false }
    val sb = StringBuilder()

    fun dfs(depth: Int) {
        with(sb) {
            if (depth == m) {
                arr.forEach { this.append(it).append(' ') }
                append('\n')
                return
            }
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                visited[i] = true
                arr[depth] = i + 1
                dfs(depth + 1)
                visited[i] = false
            }
        }
    }

    dfs(0)
    return sb.toString()
}
