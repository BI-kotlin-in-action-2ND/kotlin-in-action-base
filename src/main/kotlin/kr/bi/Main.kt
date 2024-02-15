package kr.bi

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4, 5)
    val list2 = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
    // 타입 이레이져 때문에 발생하는 문제가 매우 많다

    // 참고 https://github.com/next-step/kotlin-blackjack/blob/konempty/src/test/kotlin/dsl/builder/PersonBuilderTest.kt
    // kotlin jdsl을 쓰면 아래와 같이 예쁜 코드를 만들수 있다

    /*
               val person = PersonBuilder {
                    name("사용자")
                    company("회사명")
                    skills {
                        soft("A passion for problem solving")
                        hard("Kotlin")
                    }
                    languages {
                        "Korean" level 5
                        "English" level 1
                    }
                }.build()
     */

    // 만약 위 코드를 빌더 패턴을 써서 만들면 아래와 같다

    /*
     * val person = PersonBuilder()
                .name("사용자")
                .company("회사명")
                .skills (
                    soft("A passion for problem solving"),
                    hard("Kotlin")
                )
                .languages (
                    "Korean" level 5,
                    "English" level 1
                )
                .build()
     */

    // 코틀린 DSL 가끔 쓰며 스프링 개발자들은 마주할 일이 많을것이다
    // Kotlin jdsl이 예시이며
    // https://kotlin-jdsl.gitbook.io/docs/

    // 코루틴은 쓰레드처럼 병렬 혹은 비동기 연산을 위해 존재한다
    // RxJava, Mono-Flux, Actor 등 의 여러가지 비동기 프레임 워크의 단점인 성능을 크게 높혀준 기능

    // 그러나 쓰레드와는 다르게 컨텍스트 스위치 비용이 값싸며 쓰레드보다 빠르다
    // 프로세스 -> 쓰레드 -> 코루틴

    // 쓰레드는 OS가 관리하는 반면 코루틴은 어플리케이션에서 관리한다

    // JDK 21부터는 virtual thread가 나왔으며 컨셉은 비슷하다
    // 둘다 베이스가 되는 쓰레드가 있고 그걸 공유하는 방식
    // 코루틴은 워커 쓰레드, 버츄얼 쓰레드는 캐리어 쓰레드
    // 코루틴은 비동기에 집중하고 버츄얼 쓰레드는 쓰레드 호환성에 집중한다
    // 버츄얼쓰레드 + 스프링 단점 : overwhelming
}

fun sum(list: List<A>) {
}

fun sum(list: List<B>) {
}

class A()

class B()
