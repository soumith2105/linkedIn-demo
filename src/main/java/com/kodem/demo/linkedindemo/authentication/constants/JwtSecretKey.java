package com.kodem.demo.linkedindemo.authentication.constants;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

// Class for storing Static variable such as secret key
public class JwtSecretKey {
    // Add a secret encrypted key to ensure better Security
    private static final String secret = "dXGxn6I5gZlVR6NMFSYezAFTv2XGIVdLmrK3ce3T594AjrdApz8K7Al8peGEfpIxFWEIyHsKGzXBSheo1TtsxmYGoBq5LSEhXqBtPff5iRCTEjiZj9m4akXazbrJ55URs0d_FiRKix-CP64WeA7kJlmzEOSXQZszCv9LqQYCA78";

    public static SecretKey getSecret() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
