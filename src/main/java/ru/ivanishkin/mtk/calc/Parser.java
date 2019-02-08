package ru.ivanishkin.mtk.calc;

import java.io.IOException;
import java.io.Reader;

public class Parser {

    private Lexer lexer;
    private Lexeme currentLexeme;

    public Parser(final Reader reader) throws IOException {
        this.lexer = new Lexer(reader);
    }

    public int calculate() throws IOException {
        try {
            currentLexeme = lexer.getNextLexeme();
            int result = parseExpr();
            if (currentLexeme.type != Lexeme.LexemeType.EOF) {
                throw new ParserException();
            }
            return result;
        } catch (ParserException | LexerException e) {
            throw new RuntimeException(e);
        }
    }

    private int parseExpr() throws ParserException, IOException, LexerException {
        int result = parseTerm();
        Lexeme.LexemeType type;

        type = currentLexeme.type;
        while (type == Lexeme.LexemeType.PLUS || type == Lexeme.LexemeType.MINUS) {
            currentLexeme = lexer.getNextLexeme();
            if (type == Lexeme.LexemeType.PLUS) {
                result += parseTerm();
            } else {
                result -= parseTerm();
            }
            type = currentLexeme.type;
        }
        return result;
    }

    private int parseTerm() throws IOException, LexerException, ParserException {
        int result = parseFactor();
        Lexeme.LexemeType type = currentLexeme.type;

        while (type == Lexeme.LexemeType.MUL || type == Lexeme.LexemeType.DIV) {
            currentLexeme = lexer.getNextLexeme();
            if (type == Lexeme.LexemeType.MUL) {
                result *= parseFactor();
            } else {
                result /= parseFactor();
            }
            type = currentLexeme.type;
        }
        return result;
    }

    private int parseFactor() throws IOException, LexerException, ParserException {
        if (currentLexeme.type == Lexeme.LexemeType.NUMBER) {
            int number = new Integer(currentLexeme.text);
            currentLexeme = lexer.getNextLexeme();
            return number;
        } else if (currentLexeme.type == Lexeme.LexemeType.LPAREN) {
            currentLexeme = lexer.getNextLexeme();
            int result = parseExpr();
            if (currentLexeme.type == Lexeme.LexemeType.RPAREN) {
                currentLexeme = lexer.getNextLexeme();
                return result;
            }
        }
        throw new ParserException(currentLexeme.type.toString());
    }
}
