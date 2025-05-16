package database_layer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrator")
public class AdministratorEntity {
    @Id
    private String username;
    private String password;

    public AdministratorEntity() {
    }

    public AdministratorEntity(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "AdministratorEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
