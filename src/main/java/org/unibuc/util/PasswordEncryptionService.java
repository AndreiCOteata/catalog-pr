package org.unibuc.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptionService {

    private static PasswordEncryptionService instance;

    private PasswordEncryptionService() {
    }

    public static synchronized PasswordEncryptionService getInstance() {
        if (instance == null) {
            instance = new PasswordEncryptionService();
        }
        return instance;
    }

    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public boolean checkPass(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}