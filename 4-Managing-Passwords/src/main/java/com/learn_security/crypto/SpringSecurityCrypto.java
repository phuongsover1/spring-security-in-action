package com.learn_security.crypto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringSecurityCrypto implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // 4.2.1 Using key generators
//         usingKeyGenerators();
//        using16ByteKeyGenerators();
//        returnSameKeyValueForEachCallToGenerateKeyMethod();
//        encryptAndDecryptUsingEncryptorObject();
        encryptAndDecryptUsingEncryptorObjectStronger();
    }



    private void usingKeyGenerators() {
        StringKeyGenerator stringKeyGenerator = KeyGenerators.string();
        String salt = stringKeyGenerator.generateKey();
        log.info("StringKeyGenerator's salt: {}", salt);

        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom();
        byte[] key = keyGenerator.generateKey();
        int keyLength = keyGenerator.getKeyLength();
        log.info("Byte key: {}", key);
        log.info("Byte key's length: {}", keyLength);
    }

    private void using16ByteKeyGenerators() {
        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom(16);
        byte[] key = keyGenerator.generateKey();
        int keyLength = keyGenerator.getKeyLength();
        log.info("Byte key: {}", key);
        log.info("Byte key's length: {}", keyLength);
    }

    private void returnSameKeyValueForEachCallToGenerateKeyMethod() {
        BytesKeyGenerator keyGenerator = KeyGenerators.shared(16);
        byte[] key1 = keyGenerator.generateKey();
        byte[] key2 = keyGenerator.generateKey();
        log.info("Key 1: {}", key1);
        log.info("Key 2: {}", key2);
    }

    private void encryptAndDecryptUsingEncryptorObject() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor e = Encryptors.standard(password, salt);
        byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte[] decrypted = e.decrypt(encrypted);

        log.info("not encrypted value: {}", valueToEncrypt.getBytes());
        log.info("encrypted: {}", encrypted);
        log.info("decrypted: {}", decrypted);
    }

    private void encryptAndDecryptUsingEncryptorObjectStronger() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor e = Encryptors.stronger(password, salt);
        byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte[] decrypted = e.decrypt(encrypted);

        log.info("not encrypted value: {}", valueToEncrypt.getBytes());
        log.info("encrypted: {}", encrypted);
        log.info("decrypted: {}", decrypted);

        String salt1 = KeyGenerators.string().generateKey();
        BytesEncryptor e1 = Encryptors.stronger(password, salt1);
        log.info("not encrypted value: {}", valueToEncrypt.getBytes());
        encrypted = e1.encrypt(valueToEncrypt.getBytes());
        decrypted = e1.decrypt(encrypted);

        log.info("encrypted with salt1: {}", encrypted);
        log.info("decrypted with salt1: {}", decrypted);
    }

}
