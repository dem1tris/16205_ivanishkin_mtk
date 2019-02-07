package ru.ivanishkin.mtk.calc;

public class Lexeme {
    public static Lexeme PLUS = new Lexeme(LexemeType.PLUS, "+");
    public static Lexeme MINUS = new Lexeme(LexemeType.MINUS, "-");
    public static Lexeme DIV = new Lexeme(LexemeType.DIV, "/");
    public static Lexeme MUL = new Lexeme(LexemeType.MUL, "*");
    public static Lexeme POW = new Lexeme(LexemeType.POW, "^");
    public static Lexeme LPAREN = new Lexeme(LexemeType.LPAREN, "(");
    public static Lexeme RPAREN = new Lexeme(LexemeType.RPAREN, ")");
    public static Lexeme EOF = new Lexeme(LexemeType.EOF, "");

    public enum LexemeType {
        PLUS, MINUS, NUMBER, DIV, MUL, POW, LPAREN, RPAREN, EOF;
    }

    public final LexemeType type;
    public final String text;

    public Lexeme(final LexemeType type, final String text) {
        this.type = type;
        this.text = text;
    }
}
