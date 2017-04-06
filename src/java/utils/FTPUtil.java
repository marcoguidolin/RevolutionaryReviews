package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/*
 * Per eseguire il test delle funzionalità FTP usate il programma zFTPServer
 * (FileZilla Server richiede configurazioni particolari con il NAT e il port
 * forwarding). Create un account e con il nome test e con password 12345 e
 * dategli tutti i permessi.
 */

/**
 *
 * @author matte
 */
public class FTPUtil
{
    public static void upload(String sourceFilePath, String sourceFileName)
    {
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "10.0.1.252";
        String user = "cl_5ib20";
        String pass = "";
        String filePath = sourceFilePath;
        String uploadPath = "/" + sourceFileName;

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);

        try
        {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);

            byte[] buffer = new byte[64];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
