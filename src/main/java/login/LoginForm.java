package login;

import Core.Root;
import Core.Util;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import portfolio.PortfolioForm;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by Home on 30.04.2017.
 */
public class LoginForm {
    private Root root;
    private User user;
    private List<User> users;
    @Autowired
    private UserDao userDao;
    private PortfolioForm portfolioForm;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<User> getUsers() {
        if (this.users == null) {
            this.users = userDao.loadAll();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void login() {
        if (this.getUsers()!= null) {
            for (User us : this.getUsers()) {
                if (Objects.equals(us.getUsername(), user.getUsername()) && Objects.equals(us.getPassword(), user.getPassword())) {
                    this.setUser(us);
                    Util.getBean("portfolioForm", PortfolioForm.class).resetAllData();
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/portfolio.xhtml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Սխալ", "Տվյալները սխալ են մուտքագրված"));

    }

    public void toHomePage(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/portfolio.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPortfolioForm(PortfolioForm portfolioForm) {
        this.portfolioForm = portfolioForm;
    }

    public PortfolioForm getPortfolioForm() {
        return portfolioForm;
    }
}
