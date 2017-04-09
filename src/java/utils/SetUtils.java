/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author matte
 */
public class SetUtils
{
    public static Set convert(List list)
    {
        return new HashSet(list);
    }
}
