package br.com.jcrud.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.jcrud.persistence.model.AbstractEntity;

import java.util.Map;

/**
 * Created by jaels on 21/10/2016.
 */


@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        if(value == null || !value.matches("\\d+")) {
            return null;
        }

        return this.getAttributesForm(uiComponent).get(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if(value != null && !value.equals("")){
            AbstractEntity entity = (AbstractEntity) value;
            if(entity.getId() != null){
                this.addAttribute(uiComponent,entity);
                return entity.getId().toString();
            }
            return value.toString();
        }

        return "";
    }

    private void addAttribute(UIComponent component, AbstractEntity abstractEntity){
        this.getAttributesForm(component).put(abstractEntity.getId().toString(),abstractEntity);
    }

    private Map<String, Object> getAttributesForm(UIComponent component){
        return component.getAttributes();
    }
}
