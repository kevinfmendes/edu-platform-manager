package edu.platform.dtos;

public class RegistrationDTO {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer codigoRole;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public Integer getCodigoRole() {
        return codigoRole;
    }

    public void setCodigoRole(Integer codigoRole) {
        this.codigoRole = codigoRole;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
