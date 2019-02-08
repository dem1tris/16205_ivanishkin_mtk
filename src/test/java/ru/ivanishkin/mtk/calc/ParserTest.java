package ru.ivanishkin.mtk.calc;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    @Test
    void number() throws IOException {
        Parser parser = new Parser(new StringReader("123"));
        assertEquals(123, parser.calculate());
    }

    @Test
    void plus() throws IOException {
        Parser parser = new Parser(new StringReader("123 + 2"));
        assertEquals(123 + 2, parser.calculate());
    }

    @Test
    void minus() throws IOException {
        Parser parser = new Parser(new StringReader("123 - 2"));
        assertEquals(123 - 2, parser.calculate());
    }

    @Test
    void plusMinus() throws IOException {
        Parser parser = new Parser(new StringReader("123   - 3 +\n 234\n + 12 - 1"));
        assertEquals(123 - 3 + 234 + 12 - 1, parser.calculate());
    }

    @Test
    void div() throws IOException {
        Parser parser = new Parser(new StringReader("120/3"));
        assertEquals(120 / 3, parser.calculate());
    }

    @Test
    void mul() throws IOException {
        Parser parser = new Parser(new StringReader("120*3"));
        assertEquals(120 * 3, parser.calculate());
    }

    @Test
    void divMul() throws IOException {
        Parser parser = new Parser(new StringReader("120/ 3 * 2 / 3"));
        assertEquals(120 / 3 * 2 / 3, parser.calculate());
    }

    @Test
    void parentheses() throws IOException {
        Parser parser = new Parser(new StringReader("(2 * (2 + 2 *(2))/1)"));
        assertEquals((2 * (2 + 2 * (2)) / 1), parser.calculate());
    }

    @Test
    void badParentheses() throws IOException {
        Parser parser = new Parser(new StringReader("(2 * 2))"));
        assertThrows(RuntimeException.class, parser::calculate);
    }

    @Test
    void divMulPlusMinusParen() throws IOException {
        Parser parser = new Parser(new StringReader("2 + 2 * 2 - (3 + 3 * (3))"));
        assertEquals(2 + 2 * 2 - (3 + 3 * (3)), parser.calculate());
    }

    @Test
    void pow() throws IOException {
        Parser parser = new Parser(new StringReader("2^2^2^2"));
        assertEquals(65536, parser.calculate());
    }

    @Test
    void pow1() throws IOException {
        Parser parser = new Parser(new StringReader("2^2^(2+2)"));
        assertEquals(65536, parser.calculate());
    }

    @Test
    void unaryMinus() throws IOException {
        Parser parser = new Parser(new StringReader("-2^2^-(-2-2)"));
        assertEquals(65536, parser.calculate());
    }

}