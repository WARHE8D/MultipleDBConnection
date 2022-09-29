package org.warhead.multiclient.service;

public interface EncryptionService {

    String encrypt(String freeText);

    String decrypt(String encryptedText);
}