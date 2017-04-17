/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author matte
 */
public class BlobUtils
{
    public static String createTempFile(MultipartFile file)
    {
        if(file != null)
        {
            File dir = new File(System.getProperty("user.home") + File.separator + "temp");
            if (!dir.exists())
            {
                dir.mkdirs();
            }

            // Create the file on server
            File tempFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename().hashCode());
            
            
            try
            {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
                stream.write(file.getBytes());
                stream.close();
            } catch (IOException ex)
            {
                Logger.getLogger(BlobUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return tempFile.getAbsolutePath();
        }
        return null;
    }
}
