package ru.ivanishkin.mtk.calc;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @Test
    void plus() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("+"));
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.PLUS);
        assertEquals("+", lexeme.text);
    }

    @Test
    void minus() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("-"));
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.MINUS);
        assertEquals("-", lexeme.text);
    }

    @Test
    void number() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("123"));
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.NUMBER);
        assertEquals("123", lexeme.text);
    }

    @Test
    void eof() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("345"));
        lexer.getNextLexeme();
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.EOF);
        assertEquals("", lexeme.text);
    }

    @Test
    void complex() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("+-234+"));
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.PLUS);
        assertEquals("+", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.MINUS);
        assertEquals("-", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.NUMBER);
        assertEquals("234", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.PLUS);
        assertEquals("+", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.EOF);
        assertEquals("", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, Lexeme.LexemeType.EOF);
        assertEquals("", lexeme.text);
    }

    @Test
    void shouldFail() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("+#234+"));
        lexer.getNextLexeme();
        assertThrows(LexerException.class, lexer::getNextLexeme);
    }

}