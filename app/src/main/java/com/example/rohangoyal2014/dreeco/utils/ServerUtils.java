package com.example.rohangoyal2014.dreeco.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ServerUtils {
    public static final String USERS_ROUTE = "users";
    public static final String USERS_ATTRIBUTE_EMAIL = "email";
    public static final String USERS_ATTRIBUTE_FIRST_NAME = "firstName";
    public static final String USERS_ATTRIBUTE_LAST_NAME = "lastName";
    public static final String USERS_ATTRIBUTE_PHONE = "phone";
    public static final String OFFERS_HOME_STORAGE_ROUTE="offers_home";
    public static final String OFFER_1="offer1.jpg";
    public static final String OFFER_2="offer2.jpg";
    public static ArrayList<String> offerDownloadUrls=new ArrayList<>();
    public static FirebaseAuth mAuth;
    public static FirebaseUserDataModel mFirebaseDataModel = null;

}
