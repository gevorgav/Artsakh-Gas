package Core;

import login.LoginForm;
import login.User;
import portfolio.PortfolioForm;

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
        }else if (((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI().equals("/Gas/settings.xhtml") && !Util.getBean("loginForm", LoginForm.class).getUser().isAdmin()){
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath()+"/portfolio.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void signout(){
        Util.getBean("loginForm", LoginForm.class).setUser(new User());
        PortfolioForm portfolioForm = Util.getBean("portfolioForm", PortfolioForm.class);
        portfolioForm.setClients(null);
        portfolioForm.setVisitPlans(null);
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
