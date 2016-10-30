package br.com.jcrud.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by jaels on 21/10/2016.
 */
public class FacesUtil {

    public static void AddErrorMessege(String key){
        addMessege(FacesMessage.SEVERITY_ERROR,getBundleValue(key));
    }

    public static void AddWarningMessege(String key){
        addMessege(FacesMessage.SEVERITY_WARN,getBundleValue(key));
    }

    public static void AddSuccessMessege(String key){
        addMessege(FacesMessage.SEVERITY_INFO,getBundleValue(key));
    }


    private static void addMessege(FacesMessage.Severity severity, String msg){
        final FacesMessage facesMessage = new FacesMessage(severity,msg,"");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null,facesMessage);

    }

    private static String getBundleValue(String key){
        return FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(),"m").getString(key);
    }


}
