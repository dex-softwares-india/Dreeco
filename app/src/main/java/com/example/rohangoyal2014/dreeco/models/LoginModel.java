package com.example.rohangoyal2014.dreeco.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.rohangoyal2014.dreeco.FirstTimeActivity;
import com.example.rohangoyal2014.dreeco.R;
import com.example.rohangoyal2014.dreeco.presenters.LoginPresenter;
import com.example.rohangoyal2014.dreeco.utils.ServerUtils;
import com.example.rohangoyal2014.dreeco.views.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginModel implements LoginPresenter{

    LoginView mLoginView;
    FirstTimeActivity firstTimeActivity;

    public LoginModel(FirstTimeActivity firstTimeActivity,LoginView mLoginView){
        this.mLoginView=mLoginView;
        this.firstTimeActivity=firstTimeActivity;
    }

    @Override
    public void performLogin(String email, String pass) {
        email=email.trim();
        pass=pass.trim();
        if(TextUtils.isEmpty(email)){
            mLoginView.dataValidationFailed();
        }
        else if(TextUtils.isEmpty(pass)){
            mLoginView.dataValidationFailed();
        } else{
            signInUser(email,pass);
        }
    }

    @Override
    public void forgotPassword(String email) {

        email=email.trim();
        if(TextUtils.isEmpty(email)){
            mLoginView.forgotPasswordEmailTextEmpty();
            return;
        }
        ServerUtils.mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    mLoginView.forgotPasswordEmailSendingSuccess();
                } else {
                    mLoginView.forgotPasswordEmailSendingFailed();
                }
            }
        });
    }

    private void signInUser(String email,String pass){
        ServerUtils.mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(firstTimeActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(getClass().getSimpleName(), "signInWithEmail:success");
                            FirebaseUser user = ServerUtils.mAuth.getCurrentUser();
                            mLoginView.loginSuccess(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(getClass().getSimpleName(), "signInWithEmail:failure", task.getException());
                            mLoginView.loginFailed();

                        }
                    }
                });
    }
}
