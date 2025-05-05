package com.example.demo.global.encrypt;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class EncryptService {

  private static final String ALGORITHM = "AES";
  private static final String KEY = "a1b2c3d4e5f6g7h8";

  public String encrypt(Long id) throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    byte[] encrypted = cipher.doFinal(id.toString().getBytes());
    String base64Encoded = Base64.getEncoder().encodeToString(encrypted);
    return URLEncoder.encode(base64Encoded, StandardCharsets.UTF_8.toString());
  }

  public Long decrypt(String encrypted) throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, keySpec);
    byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
    return Long.parseLong(new String(original));
  }
}
