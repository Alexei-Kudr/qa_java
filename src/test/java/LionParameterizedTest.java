package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class LionParameterizedTest {

    // Вспомогательный метод, возвращающий параметры для теста
    private static Stream<Arguments> provideSexAndMane() {
        return Stream.of(
                Arguments.of("Самец", true),
                Arguments.of("Самка", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSexAndMane")
    void testLionHasManeBasedOnSex(String sex, boolean expectedHasMane) throws Exception {
        Feline mockFeline = new Feline();

        Lion lion = new Lion(sex, mockFeline);

        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidSex")
    void testLionThrowsExceptionForInvalidSex(String invalidSex) {
        Feline mockFeline = new Feline();

        Exception exception = assertThrows(Exception.class, () -> {
            new Lion(invalidSex, mockFeline);
        });

        assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidSex() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("Другое"),
                Arguments.of("самец")
        );
    }
}

