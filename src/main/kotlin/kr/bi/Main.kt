package kr.bi

/* class Main_kt_class{
 public static void main(String[] args){
 // 코드가 여기 들어감
 }

 staic fun foo(string: String): Int  {
    return 0
}
}
 */
fun main(args: Array<String>) {
    // 2020년 4대 핫한 언어 kotlin, rust, go, swift

    // 자바와 상호 운영성
    // 자바 to 코틀린도 쉬움

    // Infix
    "I" love "you"

    // 코드 축약
    // System.out.println("Hello World!");
    println("Hello World!")

    /*
        Scanner sc = new Scanner(System.in);
        String value = sc.nextLine();
        int num = Integer.valueOf(value);
        int num = Integer.valueOf(new Scanner(System.in).nextLine())
     * */
    val a = readln().toInt()

    // var val
    // swift : var, let
    // rust : let, let mut
    // 변경가능성 최소화
    var b = 10
    val c = 11

    b = 20
    // c = 21
    var s = "Hello"

    var s2 = "world!"

    // s2 = 10 <- 불가능

    // var o1 = Optional.of(0);
    // var o2 = Optional.ofNullable(null);

    var str1: String? = null
    var str2: String = "Hello"

    // str2 = null <- 불가능
    str1 = "string"

    val fooVar = ::foo

    /*
     * switch (a) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
            default -> System.out.println("default");
        }
     * */

    // Switch case를 대체하는 when문

    // if문과 when문은 statement(문)에서 expression(식)이 되었다
    val value =
        when (a) {
            1 -> 1
            2 -> 2
            else -> "default"
        }
    println(value)

    // 스마트 캐스트
    val p: Parent = FirstChild()

    if (p is FirstChild) {
        println(p.a)
    }
    if (p is SecondChild) {
        println(p.b)
    }

    var nullableString: String? = "hello"

    if (nullableString != null) {
        nullableString.length
    }
    // nullableString.length <- 불가능

    // for each문 지향
    //    Downto
    // for(i=0; i<=10 ; i++)
    for (i in 0..10) {
        println(i)
    }

    // for(i=0; i<10 ; i++)
    for (i in 0 until 10) {
    }
    // for(i=10; i>0 ;i--)
    for (i in 10 downTo 0) {
        println(i)
    }

    // 인덱스가 필요 없으면 repeat를 지향
    repeat(10) {
        println("Hello")
    }

    // Try -catch finally
    var result2: Int? = null
    try {
        val num = readln().toInt()
        result2 = 10 / num
    } catch (e: Exception) {
        if (e is NumberFormatException || e is NullPointerException) {
        }
        result2 = null
    } finally {
        println(result2)
    }

    val result =
        runCatching {
            val num = readln().toInt()
            10 / num
        }
    if (result.isSuccess) {
    }
    if (result.isFailure) {
    }
    println(result.getOrNull())

    val i: Int = 10

    val i2: Int = i + 10

    // String iTos  = Integer.toString(i2)

    val itoS = i2.toString()

    // 빌드 과정이 조금더 오래걸림
    // 그러나 이마저도 K2 컴파일러에 의해 해결
    // 1.9.20
    // 2.0.0 K2

    val list: List<Int> = listOf()
    val mList: MutableList<Int> = mutableListOf()
    mList.add(10)

    // list.add(1) <- 불가능
}

infix fun String.love(str: String) {
    print("$this love $str")
}

fun foo(string: String): Int {
    return 0
}

/*
interface ClickListener {
    void onClick();
}


    static void foo(ClickListener listener) {
        listener.onClick();
    }


        // 함수형 프로그래밍 지원
        // SAM
        foo(() -> {
            System.out.println("Hello World!");
        });
 */

open class Parent {
    open fun foo() {
        println("Parent")
    }
}

class FirstChild : Parent() {
    val a = 10

    override fun foo() {
        println("FirstChild")
    }
}

class SecondChild : Parent() {
    val b = 10

    override fun foo() {
        println("SecondChild")
    }
}
