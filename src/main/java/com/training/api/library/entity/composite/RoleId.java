package com.training.api.library.entity.composite;
import java.io.Serializable;
import java.util.Objects;

public class RoleId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
    private String role;

    public RoleId() {}

    public RoleId(String username, String role) {
        this.username = username;
        this.role = role;
    }

    // Getters, setters, equals, and hashCode
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleId roleId = (RoleId) o;
        return Objects.equals(username, roleId.username) && Objects.equals(role, roleId.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}
