package com.app.SportsFieldManagement.security;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;
@Slf4j

public class KeyGenerator {
    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get("src/main/resources/certs"));

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        try (FileOutputStream fos = new FileOutputStream("src/main/resources/certs/private.pem")) {
            fos.write("-----BEGIN PRIVATE KEY-----\n".getBytes());
            fos.write(Base64.getMimeEncoder().encode(pair.getPrivate().getEncoded()));
            fos.write("\n-----END PRIVATE KEY-----".getBytes());
        }

        try (FileOutputStream fos = new FileOutputStream("src/main/resources/certs/public.pem")) {
            fos.write("-----BEGIN PUBLIC KEY-----\n".getBytes());
            fos.write(Base64.getMimeEncoder().encode(pair.getPublic().getEncoded()));
            fos.write("\n-----END PUBLIC KEY-----".getBytes());
        }
        log.info("Keys are successfully created.");
    }
}