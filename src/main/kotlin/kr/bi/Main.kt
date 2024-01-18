package kr.bi

fun main(args: Array<String>) {
    // val ml = MutableList<Int>()
    val t = Temp()

    // 코틀린은 많은 컬렉션 팩토리 함수를 지원한다
    val list = arrayListOf(1, 2, 3)

    // To 는 pair를 생성해주는 역할이며 point같은거 나타낼때 사용
    val map = hashMapOf("foo" to 1, "bar" to 2)

    val pair = 1 to "one"

    // 이름 붙인 인자
    // 동일한 타입의 인자가 여러개 일때 유용하다

    class People(val name: String, val address: String, val office: String, val age: Int = 20, val optionalValue: String = "default")
    val james = People("james", "seoul", "google")

    val jayden = People(office = "kakao", address = "seoul", name = "jayden", age = 25)
    // default parameter 와도 많이 쓰인다

    val optional = People("jason", "seoul", "kakao", optionalValue = "is not optional")

    fun foo(
        a: Int,
        b: String = "",
    ) {
    }
    foo(1, "temp")
    foo(1)
    /*
    void foo(int a, String b) {
    }

    fun foo(int a) {
        foo(a, "")
    }*/

    // 최상위 함수와 프로퍼티는 유용하지만 객체 지향적 측면에서는 캡슐화를 깨는 특성이 있어서 잘써야 한다
    // 책에서 나온것처럼 실제로는 클래스의 속해있다

    // Main_kt.bar()

    // 확장 함수와 확장 프로퍼티는 진짜 많이 쓴다
    fun String.lastChar(): Char = this[this.length - 1]

    /*
    char lastChar(String str) {
        return str.charAt(str.length() - 1);
        }
     */

    // 커스텀 게터

    class CustomGet() {
        var a = "temp"
            get() {
                return field + "2"
            }
    }
    val customGet = CustomGet()
    println(customGet.a)

    infix fun Any.something(any: Any) {
    }
    /*
     * void something(Object any1, Object any2) {
     * }
     */

    // 중위 호출은 나중에 배울 DSL과 엮어서 많이 쓴다
    1 something 2

    // 코틀린의 모든 클래스와 메소드는 기본적으로 닫혀 있기 때문에 open키워드로 열어줘야 한다
    // 불변성 선호

    open class People2(val name: String, val address: String, val office: String, val age: Int = 20, val optionalValue: String = "default")

    class ChildPeople : People2("name", "address", "office", 1, "optionalValue")

    // 자바에 없던 inner이라는 키워드 추가 됐다
    // 중첩 클래스도 가끔 쓴다

    class People3 {
        inner class Office {
            fun foo() = "Welcome to Kotlin"
        }
    }
    val office = People3().Office()

    // 봉인된 클래스
    // 상속을 한정 지을수 있어서 when문과 궁합이 좋다
    val partner = Partner(AContract())
    val value =
        when (partner.contract) {
            is AContract -> "A"
            is BContract -> "B"
        }

    // lombok
    // @Getter
    // @Setter
    // @HashCodeAndEquals
    // @ToString
    // @Copy
    // @Data
    data class Food(val name: String, val price: Int, val ingredients: List<String> = listOf())

    val food1 = Food("chicken", 10000)
    val food2 = Food("chicken", 10000)
    println(food1 == food2)
    println(food1.toString())
    val food3 = food1.copy(price = 20000)
    println(food3.toString())

    // Companion object는 자바의 static과 비슷하다
    println(CompanionClass.name)
    /*
     * class CompanionClass {
       static String name = "companion"
       static String name2 = "companion2"
     * static CompanionClass_Companion instance = new CompanionClass_Companion()
     * }
     * print(CompanionClass.instance.name)
     *
     * class CompanionClass_Companion{
        val name = "companion"
        val name2 = "companion2"
     * }
     * */

    println(Singleton.name)

    /*
     * class Singleton {
     * static Singleton instance = new Singleton()
        String name = "singleton"
       }
    println(Singleton.instance.name)
     * */

    // 과제 있음
    // 최소 2문제 최대 4문제
    // 4명이 4문제씩 해오면 16문제 리뷰해야함
    // 그리고 다음 수업시간 전까지 머지가 완료되어야 과제를 완료한거라고 인정되기 때문에
    // 최소 화요일까진 올려야 리뷰 -> 리뷰적용 -> 리뷰 -> 리뷰 적용 -> 완료 할 시간이 있다.
    // 만약 리뷰가 많다면 2번 핑퐁으로도 어려울지도
}

object Singleton {
    val name = "singleton"
}

class CompanionClass {
    companion object {
        @JvmStatic
        val name = "companion"

        @JvmStatic
        val name2 = "companion2"
    }
}

class Partner(
    val contract: Contract,
)

sealed interface Contract

class AContract : Contract

class BContract : Contract

// 주 생성자와 부생성자가 있으며 체이닝으로 선언할수도 있다
data class Something( // 주 생성자
    val bar1: String,
    val bar2: String,
    val bar3: String,
) {
    init {
        println("hello I'm 3")
    }

    // 부 생성자
    constructor(a: String, b: String, c: String, d: String) : this(a, b + c, d) {
        println("hello I'm 4")
    }

    // 부 생성자
    constructor(a: String, b: String, c: String, d: String, e: String) : this(a, b, c, d + e) {
        println("hello I'm 5")
    }

    companion object {
        fun factory(
            a: String,
            b: String,
            c: String,
            d: String,
        ) = Something(a, b + c, d)
    }
}

fun bar() {
}

class Temp()

data class ImportantData(
    @JvmField
    val name: String,
    val age: Int,
)
