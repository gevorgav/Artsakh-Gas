package Convertors;

import Core.Models.Year;
import Core.Root;
import Core.Util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by arshak.askaryan on 2/16/2016.
 */
public class IdConvertor implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("")){
            return null;
        }

        Root root = Util.getBean("root", Root.class);
        for (Year year : root.getYearDao().getAll()){
            if(year.getId().toString().equals(value)){
                return year;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null || value.equals("")){
            return null;
        }
        Year year = (Year) value;
        return String.valueOf(year.getId());
    }
}
