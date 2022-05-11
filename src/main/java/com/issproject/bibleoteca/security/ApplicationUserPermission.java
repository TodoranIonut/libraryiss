package com.issproject.bibleoteca.security;

public enum ApplicationUserPermission {

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write"),
    SUBSCRIBER_READ("subscriber:read"),
    SUBSCRIBER_WRITE("subscriber:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
