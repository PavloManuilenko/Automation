package com.google.translate.exceptions;

import java.io.IOException;

public class DictionaryLineException extends IOException{

    public DictionaryLineException(String message) {
        super(message);
    }
}