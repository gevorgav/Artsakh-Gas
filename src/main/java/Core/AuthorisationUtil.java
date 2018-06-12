package Core;

import login.LoginForm;
import login.User;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Home on 24.05.2017.
 */
public class AuthorisationUtil {

    public boolean isAuthorisedUser(){
        User user = Util.getBean("loginForm", LoginForm.class).getUser();
        return  (user.getId() == null)?false:true;
    }

    public void onLoad(){
        if (!isAuthorisedUser()){
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()+"/login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void signout(){
        Util.getBean("loginForm", LoginForm.class).getUser().setId(null);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()+"/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signIn(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()+"/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
