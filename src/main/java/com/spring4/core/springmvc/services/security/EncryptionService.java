package com.spring4.core.springmvc.services.security;

public interface EncryptionService {
    String encryptionString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
