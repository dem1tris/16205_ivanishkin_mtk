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

        do {
            type = currentLexeme.type;
            currentLexeme = lexer.getNextLexeme();

            if (type == Lexeme.LexemeType.PLUS) {
                result += parseTerm();
            } else if (type == Lexeme.LexemeType.MINUS) {
                result -= parseTerm();
            }
        } while (type == Lexeme.LexemeType.PLUS || type == Lexeme.LexemeType.MINUS);
        return result;
    }

    private int parseTerm() throws IOException, LexerException, ParserException {
        if (currentLexeme.type == Lexeme.LexemeType.NUMBER) {
            int number = new Integer(currentLexeme.text);
            currentLexeme = lexer.getNextLexeme();
            return number;
        }
        throw new ParserException();
    }
}
