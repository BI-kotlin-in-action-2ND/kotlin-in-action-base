package kr.bi

fun main(args: Array<String>) {
    // By lazy 설명
    val lazy = LazyClass()
    print(lazy.name2)

    // componentN는 기본적으로 5개까지
    val (a, b, c, d, e, f) = readln().split(" ").map { it.toInt() } // List<Int>

    /*
    실제론 요런 느낌으로 처리됨
    val temp = readln().split(" ").map { it.toInt() }
    val a = temp.component1()
    val b = temp.component2()
    val c = temp.component3()
    val d = temp.component4()
    val e = temp.component5()
     */
    val data = MyDataClass("name", 10)
    val (a1, a2, a3, a4, a5, a6) = data

    // 인라인은 c/c++에도 있던 내용
    // 장점 : 속도가 빨라진다

    print("foo$a")
    print("foo$a")
    print("foo$a")
    print("foo$a")
    print("foo$a")
    print("foo$a")
    print("foo$a")
    "foo$a"

    // 단점 : 컴파일 속도가 느려진다, 바이너리 크기가 커짐

    val temp = "123".toInt()
}

private operator fun <E> List<E>.component6(): E {
    return this[6]
}

data class MyDataClass(
    val name: String,
    val age: Int,
    val address: String = "address",
    val phone: String = "phone",
    val email: String = "email",
    val job: String = "job",
    val hobby: String = "hobby",
    val etc: String = "etc",
    val etc2: String = "etc2",
    val etc3: String = "etc3",
    val etc4: String = "etc4",
    val etc5: String = "etc5",
    val etc6: String = "etc6",
    val etc7: String = "etc7",
    val etc8: String = "etc8",
    val etc9: String = "etc9",
) {
    // equals, hashCode, toString, copy, componentN
}

class LazyClass {
    val name: String by lazy {
        Thread.sleep(10000)
        "lazy"
    }
    lateinit var name2: String
}

inline fun foo(a: Int): String {
    print("foo$a")
    print("foo$a")
    print("foo$a")
    print("foo$a")
    print("foo$a")
    print("foo$a")
    print("foo$a")
    return "foo$a"
}
