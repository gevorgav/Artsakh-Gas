package Beans;

/**
 * Created by ArtStyle on 10.01.2017.
 */
public class AuthenticationForm {

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String login(){
        return "/index.xhtml";
    }

}
