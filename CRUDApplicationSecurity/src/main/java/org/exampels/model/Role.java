package org.exampels.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role", nullable = false)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> userSet;



    public Role() {

    }
    public Role(String role) {

        this.role = role;
    }


    @Override
    public String toString() {
        return role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//получаем название роли
    @Override
    public String getAuthority() {
        return role;
    }
}
