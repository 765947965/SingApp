package cn.xxs.horizontalgridview.singapp;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


import android.text.TextUtils;
import android.util.Base64;

//string encryption
public class Algorithm3DES {

  public static final String KEYURL = "~#^&tuan(&^$&^(*&%dai*%#111!";
  public static final String KEYPASSWORD = "~#^&tuandai*%#housebaby#";

  // Encrypts string and encode in Base64
  public static String encryptTextURL(String plainText, String key) throws Exception {
    byte[] key1 = "sadTUANDAIadlnf".getBytes();
    byte[] key2 = "647as96dsdf@#$@#$!@#%fawerfwerf61sfd31sdaf".getBytes();
    byte[] key3 = "afpw3er2j34;k;wekr;wekr;k".getBytes();
    byte[] key4 = "!@#@%#$tuandaiGDSFGDAFGa215".getBytes();
    byte[] key5 = "@#$@#%#@$%#$Tldfjsadlfjla;sdf".getBytes();
    byte[] key6 = "!@#$#%#$^DFGdflgjaldsfjal;d#$%#$%^".getBytes();
    synchronized (Algorithm3DES.class) {
      byte[] values = des3EncodeECB(key.getBytes(), plainText.getBytes("UTF-8"));
      return new String(Base64.encode(values, Base64.DEFAULT), "UTF-8");
    }
  }

  // Encrypts string and encode in Base64
  public static String encryptTextURL(String plainText) throws Exception {
    byte[] key1 = "sadTUANDAIadlnf".getBytes();
    byte[] key2 = "647as96dsdf@#$@#$!@#%fawerfwerf61sfd31sdaf".getBytes();
    byte[] key3 = "afpw3er2j34;k;wekr;wekr;k".getBytes();
    byte[] key4 = "!@#@%#$tuandaiGDSFGDAFGa215".getBytes();
    byte[] key5 = "@#$@#%#@$%#$Tldfjsadlfjla;sdf".getBytes();
    byte[] key6 = "!@#$#%#$^DFGdflgjaldsfjal;d#$%#$%^".getBytes();
    byte[] values = des3EncodeECB(KEYURL.getBytes(), plainText.getBytes("UTF-8"));
    return new String(Base64.encode(values, Base64.DEFAULT), "UTF-8");
  }

  // Encrypts string and encode in Base64
  public static String encryptText(String plainText) throws Exception {
    byte[] key1 = "sad!@#@!@#$@#%#$%#$%".getBytes();
    byte[] key2 = "647as^&(^&(f@#$@#$!@#%fawerfwerf61sfd31sdaf".getBytes();
    byte[] key3 = "afpw3er2tuandaikr;k".getBytes();
    byte[] key4 = "!@#@%#$^%#$!#%$#$%^#$DSFGDAFGa215".getBytes();
    byte[] key5 = "@#$@#%#@tuandai!@#$@#$;sdf".getBytes();
    byte[] key6 = "!@#$#%#$@$?@#%sftuandai%^".getBytes();
    byte[] values = des3EncodeECB(KEYPASSWORD.getBytes(), plainText.getBytes("UTF-8"));
    return new String(Base64.encode(values, Base64.DEFAULT), "UTF-8");
  }

  // 解密
  public static String decodeText(String key, String text) throws Exception {
    synchronized (Algorithm3DES.class) {
      byte[] textbyte = Base64.decode(text.getBytes("UTF-8"), Base64.DEFAULT);
      byte[] values = ees3DecodeECB(key.getBytes("UTF-8"), textbyte);
      return new String(values, "UTF-8");
    }
  }

  /**
   * ECB加密,不要IV
   *
   * @param key 密钥
   * @param data 明文
   * @return Base64编码的密文
   * @throws Exception
   */
  public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {
    DESedeKeySpec spec = new DESedeKeySpec(key);
    SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
    Key deskey = keyfactory.generateSecret(spec);
    Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS7Padding");
    cipher.init(Cipher.ENCRYPT_MODE, deskey);
    byte[] bOut = cipher.doFinal(data);
    return bOut;
  }

  /**
   * ECB解密,不要IV
   *
   * @param key 密钥
   * @param data Base64编码的密文
   * @return 明文
   * @throws Exception
   */
  public static byte[] ees3DecodeECB(byte[] key, byte[] data) throws Exception {
    DESedeKeySpec spec = new DESedeKeySpec(key);
    SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
    Key deskey = keyfactory.generateSecret(spec);
    Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS7Padding");
    cipher.init(Cipher.DECRYPT_MODE, deskey);

    byte[] bOut = cipher.doFinal(data);
    return bOut;
  }



}