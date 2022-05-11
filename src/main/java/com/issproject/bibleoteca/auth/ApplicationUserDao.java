package com.issproject.bibleoteca.auth;

import com.issproject.bibleoteca.model.User;

import java.util.Optional;

public interface ApplicationUserDao {

    ApplicationUser selectApplicationUserByUsername(String username);

}
