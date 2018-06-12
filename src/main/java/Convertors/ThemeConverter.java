package Convertors; /**
 * Created by arshak.askaryan on 1/26/2017.
 */

import Core.Models.Year;
import Core.Root;
import Core.Util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("themeConverter")
public class ThemeConverter implements Converter {

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                Root root = Util.getBean("root", Root.class);
                for (Year year : root.getYearDao().getAll()){
                    if(year.getId().toString().equals(value)){
                        return year;
                    }
                }
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
            return null;

    }


    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Year) object).getId());
        }
        else {
            return null;
        }
    }
}
