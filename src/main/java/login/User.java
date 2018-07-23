package login;

/**
 * Created by Home on 30.04.2017.
 */
public class User {

    public enum Role {
        OPERATOR,
        ADMIN
    }

    private Integer id;
    private String username;
    private String password;
    private Integer regionId;
    private Role role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAdmin(){
        return this.role.equals(Role.ADMIN);
    }
}
