package org.systemspecs.interns.dto;

import org.systemspecs.interns.domain.Role;

public class CreateUserDTO {
    private String fullname;
    private String password;
    private String email;
    private String uniqueId;
    private String role;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname =  fullname ;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public org.systemspecs.interns.domain.Role getRole() {
        return Role.valueOf(role);
    }

    public void setRole(Role role) {
        this.role = String.valueOf(role);
    }


    @Override
    public String toString() {
        return "CreateUserDTO{" +
                "Full_name='" + fullname + '\'' +
                ", Password='" + password + '\'' +
                ", Email='" + email + '\'' +
                ", Matriculation Number ='" + uniqueId + '\'' +
                ", Role='" + role + '\'' +


                '}';
    }
}
