package customValidator;

import Core.Root;
import Core.Util;
import hotel.Hotel;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by gev on 05.02.2017.
 */
@FacesValidator("HotelNameUniqueValidator")
public class HotelNameUniqueValidator implements Validator {


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Root root = Util.getBean("root", Root.class);
        Hotel hotel = Util.getBean("hotel", Hotel.class);
        for (Hotel hotelitem : root.getHotelDao().getAll()) {
            if (hotelitem.getName().equals(o) && !hotel.getId().equals(hotelitem.getId())) {
                FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Անվանումը գոյություն ունի");
                throw new ValidatorException(fmsg);
            }
        }
    }
}
