package fr.cszw.td_devwebavance.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DBException extends Exception {

    private String message;
}
