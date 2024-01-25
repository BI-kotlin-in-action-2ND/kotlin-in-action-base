package kr.bi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Platform {
    String name;

    public Platform( String name) {
        this.name = name;
        var varVal = 1; // 자바에는 var라고해서 어느정도 타입추론이 가능하다.

        var foo = foo(); // 하지만 이건 안되지 -> 불가능한 타입이니까!
    }

    void foo(){
        System.out.println("foo");
    }
}
