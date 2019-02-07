package ru.ivanishkin.mtk.calc;

public class ParserException extends Exception {
    //TODO:

    private static final String MESSAGE = "Syntax error";

    public ParserException() {
        super(MESSAGE);
    }

    public ParserException(final String message) {
        super(MESSAGE + ": " + message);
    }
}
