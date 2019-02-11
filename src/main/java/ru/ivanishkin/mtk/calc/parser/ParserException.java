package ru.ivanishkin.mtk.calc.parser;

public class ParserException extends Exception {

    private static final String MESSAGE = "Syntax error";

    public ParserException() {
        super(MESSAGE);
    }

    public ParserException(final String message) {
        super(MESSAGE + ": " + message);
    }
}
