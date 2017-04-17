/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matte
 */
public class ParseUtils
{

    public static boolean tryParseInt(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }
    
    public static Date parseDate(String value)
    {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try
        {
            date = format.parse(value);
            String newDateString = format.format(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
}
