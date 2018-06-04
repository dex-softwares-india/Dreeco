package com.example.rohangoyal2014.dreeco.views;

public interface RegisterView {

    int FIRST_NAME_EMPTY=1;
    int LAST_NAME_EMPTY=2;;
    int EMAIL_EMPTY=3;
    int PHONE_EMPTY=4;
    int PASSWORD_EMPTY=5;
    int CONFIRM_PASSWORD_EMPTY=6;
    int PASSWORD_NO_MATCH=7;
    int PASSWORD_LENGTH_LESS=8;
    int PHONE_NUMBER_INCORRECT=9;

    void registrationSuccess();
    void registrationFailed();
    void userExists();
    void validationError(int code);
}
