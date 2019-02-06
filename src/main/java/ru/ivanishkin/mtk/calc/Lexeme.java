package ru.ivanishkin.mtk.calc;

public class Lexeme {
    public static Lexeme PLUS = new Lexeme(LexemeType.PLUS, "+");
    public static Lexeme MINUS = new Lexeme(LexemeType.MINUS, "-");
    public static Lexeme EOF = new Lexeme(LexemeType.EOF, "");

    public enum LexemeType {
        PLUS, MINUS, NUMBER, EOF;
    }

    public final LexemeType type;
    public final String text;

    public Lexeme(final LexemeType type, final String text) {
        this.type = type;
        this.text = text;
    }
}
