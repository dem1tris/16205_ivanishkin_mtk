package ru.ivanishkin.mtk.calc;

public class ParserException extends Exception {
    //TODO:

    private static final String message = "Syntax error";

    public ParserException() {
        super(message);
    }
}
