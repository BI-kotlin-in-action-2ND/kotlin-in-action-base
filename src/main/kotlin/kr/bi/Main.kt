package kr.bi

fun main(args: Array<String>) {
    println("What's your name?")
    val name = readln()
    println("Hello $name!")

    val str: String? = null

    if (str != null) {
        str.length
    }
    // println(str.length)

    /*val obj :Object = "1234"

    if(obj is String) {
        // String str = (String)obj;
        // str.length()
        obj.length
    }*/
    // obj.length

    // 1. 리뷰관련 공지사항
    // ~~~하세요, 해주세요 ~~ 이게 국룰입

    // ~~하면 좋아요, ~~ 해보면 어떨까요

    // ~~ 이건 갠췬데~ , 참고용으로 알려드립니다

    // ~~~ 요건 어떤 내용인가요

    // int n = 10;
    // Integer n = 10;
    val n: Int = 10

    // List<Integer> list = new ArrayList<>();
    val list: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // int[] array = new int[10];
    val array: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // 람다는 자바에도 있었으며 코틀린에서 새로 생긴건 아니다
    // 그런데 코틀린이 쓰기 쉽게 만들어 주긴 했다.

    // map{}, countBy{}, sortBy{}, maxBy{}
    // 모두 람다를 사용하며 코틀린은 람다의 사용을 좀더 적극적으로 유도하고 있다

    listOf(1, 2, 3).binarySearch(3)

    // 책에서 나온것 처럼 파라메터에는 이름을 붙히는게 좋다
    // 왜냐면 nested lambda 때문에 가독성이 떨어지기 때문이다
    val list2 = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
    list2.map { innerList ->
        innerList
            .map { element -> element * 2 }
    }.forEach { elemList -> println(elemList) }

    // 시퀀스 정말 중요하다
    // 자바의 stream에 대응하는 개념으로
    // 지연 연산이라는 개념이 도입되었다
    // 병렬연산이 불가능하다

    val list3 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    var result =
        list3.stream().parallel() // list2
            .map { it * 2 } // list2 * 2  내용을 가지는 배열 하나더 추가
            .map { it * 2 } // list2 * 2 * 2  내용을 가지는 배열 하나더 추가
            .map { it * 2 } // list2 * 2 * 2 내용을 가지는 배열 하나더 추가
            // 16, 24 ...
            .filter { it > 10 }
            .findFirst().get()

    // 위 코드를 sequence로 바꾸면
    result =
        list3.asSequence() // list2
            .map { it * 2 } // 2 * 2 = 4
            .map { it * 2 } // 4 * 2 = 8
            .map { it * 2 } // 8 * 2 = 16
            .filter { it > 10 }
            .first()

    print(result)

    // SAM도 좋은 사례이다
    val impl = SAM_IMPL()

    class A(val sam: SAM)
    val a = A { println("hello") }
    a.sam.foo()

    // 비영어권 개발자 들이 많이 고통받는게
    // let run apply also with
    // 요거다
    // https://t1.daumcdn.net/cfile/tistory/9925163E5DEBE18E01
    a.run {}
    a.let { }
    a.apply { }
    a.also { }
    with(a) {
    }

    // 널 체이닝도 최근 언어가 지원하는 기능이며 트렌드 이다
    // swift, kotlin, rust, go는 없음
    var b: Int? = 10

    // 엘비스 연산자를 정말 많이 쓰게 될것이다
    // https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.globalnerdy.com%2Fwordpress%2Fwp-content%2Fuploads%2F2017%2F11%2Fwhy-its-called-the-elvis-operator.jpg&tbnid=1xNx4cAHCyds0M&vet=12ahUKEwjltpz4sPiDAxXkevUHHZ4sBSgQMygBegQIARBX..i&imgrefurl=https%3A%2F%2Fwww.globalnerdy.com%2F2017%2F11%2F13%2Fcalled-kotlins-elvis-operator%2F&docid=rXxOaIYXLBRIFM&w=600&h=800&q=elvis%20operator&ved=2ahUKEwjltpz4sPiDAxXkevUHHZ4sBSgQMygBegQIARBX
    // 이걸 통해 자바의 optional을 많이 대체하게 되었다
    val c = b ?: 10

    // as? as

    val obj2: Object? = null
    // 자바에서는 String d = (String) obj2;
    val d = obj2 as String
    val e = obj2 as? String

    // Null-assertion은 안쓰는걸 추천한다
    // 이 기호의 의도는 일부로 못생기게 만들어서 쓰는걸 자제하고자 만들었다는게 신기하다
    // !!
    e!!
    // val a : String!

    val platform = Platform(null)
    platform.name

    // 플랫폼 타입 때문에 프로그램이 은근히 많이 터진다
    name.length
    // NPE
    platform.name?.length

    // 라이브러리 제공하는곳의 문서를 잘 읽거나
    // 잘모르겠으면 ?쓰자

    // Any와 Object의 차이도 잘 알아두자
    // Nothing도 잘 알아두자

    // void와 Unit의 차이도 잘 알아두어야 하며
    val fooVal = foo()

    // Array<Int> 대신 IntArray로 잘쓰자
    // int[] array2 = new int[10];
    val array2 = IntArray(10) { 1 }

    // Integer[] array3 = new Integer[10];
    val array3 = Array<Int>(10) { 0 } // 이거 쓰지 말것!

    // Int,Long,Byte,Short,Char,Boolean,Float,Double 0 혹은 0.0으로 초기화
    val intArray2 = IntArray(10)
    // LongArray, ByteArray, ShortArray, CharArray, BooleanArray, FloatArray, DoubleArray
    val longArray = LongArray(10)

    val charArray = CharArray(10)

    // int <-> Integer
}

fun foo() {
    print("foo")
}

fun interface SAM {
    fun foo()
}

class SAM_IMPL : SAM {
    override fun foo() {
        println("hello")
    }
}

// field 키워드가 왜있나요
// @field:NotBlank(message = "이름은 필수입니다.")
class Person {
    var name: String = "temp"
        get() {
            return field
        }
}
