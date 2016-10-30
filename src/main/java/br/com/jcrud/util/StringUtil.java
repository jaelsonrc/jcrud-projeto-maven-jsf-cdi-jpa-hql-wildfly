package br.com.jcrud.util;

/**
 * Created by jaels on 21/10/2016.
 */
public class StringUtil {

    public static String likeLower(String param){
        return  "%"+param.toLowerCase()+"%";
    }
    public static String like(String param){
        return  "%"+param +"%";
    }
}
