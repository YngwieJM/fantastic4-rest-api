package com.juaracoding.fantastic4_rest_api.security;

import java.util.function.Function;

public class BcryptImpl {

    private static final BcryptCustom bcrypt = new BcryptCustom(11);

    public static String hash(String password) {
        return bcrypt.hash(password);
    }

    public static boolean verifyAndUpdateHash(String password,
                                              String hash,
                                              Function<String, Boolean> updateFunc) {
        return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
    }

    public static boolean verifyHash(String password , String hash)
    {
        return bcrypt.verifyHash(password,hash);
    }
    
    public static void main(String[] args) {
//        String strUserName = "bagas123Bagas@123";
//        String strUserName = "paul123Bagas@123";
//        System.out.println(hash(strUserName));
//        System.out.println(verifyHash("paul123Bagas@123","$2a$11$RXJm7NfQT1vELjPYSlaP.OhiCmQiU/8eAeJJ0Z9JqPGQPb4r/hPkO"));
//        String password = "Paul@1234";
//        String hasilEncryptPwd = Crypto.performEncrypt(password);
//        String hasilDecryptPwd = Crypto.performDecrypt(hasilEncryptPwd);
//        System.out.println("Password Asli : "+password);//dari login
//        System.out.println("Encrypt : "+hasilEncryptPwd);// data dari DB
//        System.out.println("Decrypt : "+hasilDecryptPwd);// hasil decrypt dari DB
//        System.out.println(password.equals(hasilDecryptPwd));
//          String password = "Paul@1234";
//          String hashKeDB = hash(password);
          //$2a$11$LITfERUVcwfXYiBfsIhcve5nb456IE0wkvINW.KpcaYHid/eV/y2W
          //$2a$11$Q8W1LSyof5hsvYkTZgaGEeeTQwsNcLpJujah7bupUnR1wgHjCCRI.
          //$2a$11$tbFWU77CQP.hnXyDVKh.ZuTP0jHW7x7G2nS0hQj1tDWutdRzENzuu
//        System.out.println("Ini Hash Ke DB: " + hashKeDB);
        System.out.println(hash("JYW33Yngwie@1234"));
        System.out.println("Compare Hash 1 : "+verifyHash("Paul@1234","$2a$11$LITfERUVcwfXYiBfsIhcve5nb456IE0wkvINW.KpcaYHid/eV/y2W"));
        System.out.println("Compare Hash 2 : "+verifyHash("Paul@1234","$2a$11$Q8W1LSyof5hsvYkTZgaGEeeTQwsNcLpJujah7bupUnR1wgHjCCRI."));
        System.out.println("Compare Hash 3 : "+verifyHash("Paul@1234","$2a$11$tbFWU77CQP.hnXyDVKh.ZuTP0jHW7x7G2nS0hQj1tDWutdRzENzuu"));
        System.out.println("Compare Hash 4 : "+verifyHash("luthfan1997Luthfan@1234","$2a$11$6M32rMSJ.70eMvZoW/hooOte9SAoPb7gXtXM.f4OUKhDnphE6eXFq"));
    }
}