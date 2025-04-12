package org.kfokam48.gestiondesutilisateurs.exception;

public class UserEmailAlreadyExistException extends RuntimeException {

    public UserEmailAlreadyExistException(String message) {
        super(message);
    }
}
