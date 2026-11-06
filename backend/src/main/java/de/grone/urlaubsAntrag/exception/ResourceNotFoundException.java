package de.grone.urlaubsAntrag.exception;

public class ResourceNotFoundException extends RuntimeException {

        public ResourceNotFoundException(String message) {
            super(message);
        }
}
