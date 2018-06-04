package com.example.rohangoyal2014.dreeco.views;

import com.google.firebase.auth.FirebaseUser;

public interface LoginView {

    int EMAIL_EMPTY=1, PASSWORD_EMPTY=2;
    void loginSuccess(FirebaseUser user);
    void loginFailed();
    void dataValidationFailed(int code);
}
