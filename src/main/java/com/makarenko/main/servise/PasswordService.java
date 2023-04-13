package com.makarenko.main.servise;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import static com.makarenko.main.util.Constants.*;

public class PasswordService {

    public boolean checkPassword(String attemptPassword, byte[] rightPassword, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] hashAttemptPassword = getHashPassword(attemptPassword, salt);
        return Arrays.equals(hashAttemptPassword, rightPassword);
    }

    public byte[] getHashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String algoritm = PASSWORD_ALGORITM;
        int iterations = PASSWORD_ITER;
        int hard = PASSWORD_HARD;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, hard);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(algoritm);
        return factory.generateSecret(spec).getEncoded();
    }

    public byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SIXTEEN];
        random.nextBytes(salt);
        return salt;
    }
}
