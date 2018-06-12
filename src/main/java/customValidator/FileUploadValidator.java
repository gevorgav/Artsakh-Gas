package customValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 * Created by gev on 18.02.2017.
 */
@FacesValidator("FileUploadValidator")
public class FileUploadValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        Part file = (Part) value;

        FacesMessage message = null;

        try {

            if (!file.getContentType().endsWith("jpg") && !file.getContentType().endsWith("jpeg"))
                message = new FacesMessage("Թույլատրելի է՝ jpg,jpeg");
            else if (file.getSize() > 2000000)
                message = new FacesMessage("Նկարը չպիտի գերազանցի 2MB -ը");

            if (message != null && !message.getDetail().isEmpty()) {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }

        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }
    }
}
