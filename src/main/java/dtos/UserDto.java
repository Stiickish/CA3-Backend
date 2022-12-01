package dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.User} entity
 */
public class UserDto implements Serializable {
    private  Integer id;
    @Size(max = 25)
    @NotNull
    private  String userName;
    @Size(max = 255)
    private  String userPass;
    private  Set<RoleDto1> roles;

    public UserDto(Integer id, String userName, String userPass, Set<RoleDto1> roles) {
        this.id = id;
        this.userName = userName;
        this.userPass = userPass;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public Set<RoleDto1> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.userName, entity.userName) &&
                Objects.equals(this.userPass, entity.userPass) &&
                Objects.equals(this.roles, entity.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPass, roles);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "userName = " + userName + ", " +
                "userPass = " + userPass + ", " +
                "roles = " + roles + ")";
    }

    /**
     * A DTO for the {@link entities.Role} entity
     */
    public static class RoleDto1 implements Serializable {
        private  Integer id;
        @Size(max = 20)
        @NotNull
        private  String roleName;

        public RoleDto1(Integer id, String roleName) {
            this.id = id;
            this.roleName = roleName;
        }

        public Integer getId() {
            return id;
        }

        public String getRoleName() {
            return roleName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RoleDto1 entity = (RoleDto1) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.roleName, entity.roleName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, roleName);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "roleName = " + roleName + ")";
        }
    }
}