package br.com.jcrud.util;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by jaels on 21/10/2016.
 */
public class Text extends ResourceBundle {
    private static final String BUNDLE_NAME ="messege";
    private static final String BUNDLE_EXTENSION ="properties";
    private static final Control UTF8_CONTROL = new UTF8Control();


    public Text(){
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, FacesContext.getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL )   );
    }


    protected static class UTF8Control extends  Control{
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reLoad){
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);
            ResourceBundle bundle=null;
            InputStream stream=null;
            if(reLoad){
                try {
                URL url = loader.getResource(bundleName);
                if(url != null){

                    URLConnection  connection = url.openConnection();
                    if(connection != null){
                        connection.setUseCaches(false);
                        stream=connection.getInputStream();
                    }
                }
                }catch (IOException e){
                    stream=null;
                }
            }else{
                stream=loader.getResourceAsStream(resourceName);
            }
            if(stream != null){
                try {
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                }catch (IOException e){
                    bundle=null;
                }
                finally {
                    try {
                        if (stream != null) {
                            stream.close();
                        }
                    } catch (IOException e){
                            e.printStackTrace();
                    }
                }
            }
            return bundle;
        }



    }


    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }
}
