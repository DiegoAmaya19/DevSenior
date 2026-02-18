package com.testbasico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    @DisplayName("Testo si un valor es divisible por 3")
    public void testRetornoDivisiblePorTres() {

        var resultado1 = fizzBuzz.getValue(9);

        assertEquals("Fizz", resultado1);
    }

    @Test
    @DisplayName("Testo si un valor es divisible por 5")
    public void testRetornoDivisiblePorCinco() {

        var resultado1 = fizzBuzz.getValue(20);

        assertEquals("Buzz", resultado1);
    }

    @Test
    @DisplayName("Testo si un valor es divisible por 5")
    public void testRetornoDivisiblePorTres_y_Cinco() {

        var resultado1 = fizzBuzz.getValue(15);

        assertEquals("FizzBuzz", resultado1);
    }
}
