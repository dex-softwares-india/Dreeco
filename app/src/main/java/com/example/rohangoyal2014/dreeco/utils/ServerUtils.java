package com.example.rohangoyal2014.dreeco.utils;

import com.google.firebase.auth.FirebaseAuth;

public class ServerUtils {
    public static final String USERS_ROUTE = "users";
    public static final String USERS_ATTRIBUTE_EMAIL = "email";
    public static final String USERS_ATTRIBUTE_FIRST_NAME = "firstName";
    public static final String USERS_ATTRIBUTE_LAST_NAME = "lastName";
    public static final String USERS_ATTRIBUTE_PHONE = "phone";
    public static FirebaseAuth mAuth;
    public static FirebaseUserDataModel mFirebaseDataModel = null;

}
