//package com.issproject.bibleoteca.security;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static com.issproject.bibleoteca.security.ApplicationUserPermission.*;
//
//public enum ApplicationUserRole {
//
//    ADMIN(Sets.newHashSet(BOOK_READ, BOOK_WRITE, USER_READ, USER_WRITE,SUBSCRIBER_READ,SUBSCRIBER_WRITE)),
//    LIBRARIAN(Sets.newHashSet(BOOK_READ, BOOK_WRITE, SUBSCRIBER_READ,SUBSCRIBER_WRITE)),
//    SUBSCRIBER(Sets.newHashSet(BOOK_READ, BOOK_WRITE));
//
//    private final Set<ApplicationUserPermission> permissions;
//
//    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<ApplicationUserPermission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
//        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return permissions;
//    }
//}
