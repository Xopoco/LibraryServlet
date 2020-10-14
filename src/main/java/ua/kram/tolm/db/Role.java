package ua.kram.tolm.db;

import ua.kram.tolm.db.entity.User;

public enum Role {
    ADMIN,
    LIBRARIAN,
    READER;

    public static Role getRole(User user) {
        return Role.values()[user.getRoleId()];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
