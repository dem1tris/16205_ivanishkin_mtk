package ru.ivanishkin.mtk.calc;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;
import static ru.ivanishkin.mtk.calc.Lexeme.LexemeType.*;

class LexerTest {

    @Test
    void plus() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("+"));
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, PLUS);
        assertEquals("+", lexeme.text);
    }

    @Test
    void minus() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("-"));
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, MINUS);
        assertEquals("-", lexeme.text);
    }

    @Test
    void number() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("123"));
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, NUMBER);
        assertEquals("123", lexeme.text);
    }

    @Test
    void eof() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("345"));
        lexer.getNextLexeme();
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, EOF);
        assertEquals("", lexeme.text);
    }

    @Test
    void complex() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("+-234+"));
        Lexeme lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, PLUS);
        assertEquals("+", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, MINUS);
        assertEquals("-", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, NUMBER);
        assertEquals("234", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, PLUS);
        assertEquals("+", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, EOF);
        assertEquals("", lexeme.text);

        lexeme = lexer.getNextLexeme();
        assertSame(lexeme.type, EOF);
        assertEquals("", lexeme.text);
    }

    @Test
    void shouldFail() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("+#234+"));
        lexer.getNextLexeme();
        assertThrows(LexerException.class, lexer::getNextLexeme);
    }

    @Test
    void paren() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader(")("));
        assertSame(Lexeme.RPAREN, lexer.getNextLexeme());
        assertSame(Lexeme.LPAREN, lexer.getNextLexeme());
    }

    @Test
    void divmul() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader(")/(*"));
        lexer.getNextLexeme();
        assertSame(Lexeme.DIV, lexer.getNextLexeme());
        lexer.getNextLexeme();
        assertSame(Lexeme.MUL, lexer.getNextLexeme());
    }

    @Test
    void pow() throws IOException, LexerException {
        Lexer lexer = new Lexer(new StringReader("123^321"));
        lexer.getNextLexeme();
        assertSame(Lexeme.POW, lexer.getNextLexeme());
        assertSame(NUMBER, lexer.getNextLexeme().type);
    }
}