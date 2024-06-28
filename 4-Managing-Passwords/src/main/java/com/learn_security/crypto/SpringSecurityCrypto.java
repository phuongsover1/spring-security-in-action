package com.learn_security.crypto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
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
        returnSameKeyValueForEachCallToGenerateKeyMethod();
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

}
