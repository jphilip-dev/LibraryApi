package com.training.api.library.entity;
import com.training.api.library.entity.composite.RoleId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
@IdClass(RoleId.class) // Composite primary key class
public class Role {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Id
    @Column(name = "role", nullable = false)
    private String role;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "username", insertable = false, updatable = false) // This defines the FK to User
    private User user;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public String toString() {
		return "Role [username=" + username + ", role=" + role  + "]";
	}
    
}
