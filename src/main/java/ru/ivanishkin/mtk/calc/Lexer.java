package ru.ivanishkin.mtk.calc;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private int nextSymbol;
    private final Reader reader;

    public Lexer(final Reader reader) throws IOException {
        this.reader = reader;
        do {
            nextSymbol = this.reader.read();
        } while (Character.isWhitespace(nextSymbol));
    }

    public Lexeme getNextLexeme() throws IOException, LexerException {
        int numberBuilder = 0;

        do {
            int currentSymbol = nextSymbol;
            do {
                nextSymbol = this.reader.read();
            } while (Character.isWhitespace(nextSymbol));

            switch (currentSymbol) {
                case -1:
                    return Lexeme.EOF;
                case '+':
                    return Lexeme.PLUS;
                case '-':
                    return Lexeme.MINUS;
                case '/':
                    return Lexeme.DIV;
                case '*':
                    return Lexeme.MUL;
                case '(':
                    return Lexeme.LPAREN;
                case ')':
                    return Lexeme.RPAREN;
                case '^':
                    return Lexeme.POW;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    numberBuilder *= 10;
                    numberBuilder += currentSymbol - '0';
                    break;
                default:
                    throw new LexerException();
            }
        } while (Character.isDigit(nextSymbol));

        return new Lexeme(Lexeme.LexemeType.NUMBER, Integer.toString(numberBuilder));
    }
}
