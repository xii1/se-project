package com.miu.se.common.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private static String hash = "asdakjkownfiownoinoansihasidhasild";

    public static String encode(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes =  md.digest();
        return DatatypeConverter.printHexBinary(bytes);
    }
}
