package com.kach.studyhelperback.Domain;
import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name="usr")
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    @ElementCollection (targetClass = Role.class, fetch=FetchType.EAGER)//EAGER because in Role only 3 elements
    @CollectionTable(name="user-role",joinColumns = @JoinColumn(name = "user-id"))//TODO rename column
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
