package ua.kram.tolm.db;

public enum Role {
    ADMIN,
    LIBRARIAN,
    READER;

    public String getName() {
        return name().toLowerCase();
    }
}
