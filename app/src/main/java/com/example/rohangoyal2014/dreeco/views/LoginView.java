package com.example.rohangoyal2014.dreeco.views;

import com.google.firebase.auth.FirebaseUser;

public interface LoginView {

    void loginSuccess(FirebaseUser user);
    void loginFailed();
    void dataValidationFailed();
    void forgotPasswordEmailSendingFailed();
    void forgotPasswordEmailSendingSuccess();
    void forgotPasswordEmailTextEmpty();
}
