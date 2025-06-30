package br.com.anagram.exception;


public class InvalidAnagramInputException extends RuntimeException {

    public InvalidAnagramInputException(String message) {
        super(message);
    }
}

