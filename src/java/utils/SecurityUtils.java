/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;

/**
 *
 * @author matte
 */
public class SecurityUtils
{
    public static String getSha256(String value)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    private static String bytesToHex(byte[] bytes)
    {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes)
        {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
