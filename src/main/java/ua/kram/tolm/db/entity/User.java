package ua.kram.tolm.db.entity;

public class User extends Entity{
    private static final long serialVersionUID = -7201225328937287368L;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private int roleId;
    private int debt;

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public static User createUser (String login, String password, String firstName, String lastName, String email, String telephone) {
        User user = new User();
        user.login = login;
        user.password = password;
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.telephone = telephone;
        user.debt = 0;
        user.roleId = 2;

        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", roleId=" + roleId +
                ", debt=" + debt +
                '}';
    }
}
