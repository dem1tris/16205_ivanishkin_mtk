package ru.ivanishkin.mtk.calc;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

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


}