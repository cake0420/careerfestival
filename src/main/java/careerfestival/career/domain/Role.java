package careerfestival.career.domain;

import lombok.Builder;
public enum Role {
    ROLE_USER, ROLE_ADMIN;

    public static Role fromString(String roleStr) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleStr)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No constant with text " + roleStr + " found");
    }
}
