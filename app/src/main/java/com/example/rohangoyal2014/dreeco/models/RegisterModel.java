package com.example.rohangoyal2014.dreeco.models;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.rohangoyal2014.dreeco.RegisterActivity;
import com.example.rohangoyal2014.dreeco.presenters.RegisterPresenter;
import com.example.rohangoyal2014.dreeco.utils.FirebaseUserDataModel;
import com.example.rohangoyal2014.dreeco.utils.ServerUtils;
import com.example.rohangoyal2014.dreeco.views.RegisterView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterModel implements RegisterPresenter {

    RegisterView mRegisterView;
    RegisterActivity registerActivity;

    public RegisterModel(RegisterActivity registerActivity,RegisterView mRegisterView){
        this.mRegisterView=mRegisterView;
        this.registerActivity=registerActivity;
    }

    @Override
    public void performRegistration(FirebaseUserDataModel firebaseUserDataModel) {
        firebaseUserDataModel.setFirstName(firebaseUserDataModel.getFirstName().trim());
        firebaseUserDataModel.setLastName(firebaseUserDataModel.getLastName().trim());
        firebaseUserDataModel.setEmail(firebaseUserDataModel.getEmail().trim());
        firebaseUserDataModel.setPhone(firebaseUserDataModel.getPhone().trim());
        if(TextUtils.isEmpty(firebaseUserDataModel.getFirstName())){

            mRegisterView.validationError(RegisterView.FIRST_NAME_EMPTY);

        } else if(TextUtils.isEmpty(firebaseUserDataModel.getLastName())){

            mRegisterView.validationError(RegisterView.LAST_NAME_EMPTY);

        } else if(TextUtils.isEmpty(firebaseUserDataModel.getEmail())){

            mRegisterView.validationError(RegisterView.EMAIL_EMPTY);

        } else if(TextUtils.isEmpty(firebaseUserDataModel.getPhone())){

            mRegisterView.validationError(RegisterView.PHONE_EMPTY);

        } else if(TextUtils.isEmpty(firebaseUserDataModel.getPassword())){

            mRegisterView.validationError(RegisterView.PASSWORD_EMPTY);

        } else if(TextUtils.isEmpty(firebaseUserDataModel.getConfirmedPassword())){

            mRegisterView.validationError(RegisterView.CONFIRM_PASSWORD_EMPTY);

        } else if(firebaseUserDataModel.getPhone().length()!=10 || !checkForDigit(firebaseUserDataModel.getPhone())){

            mRegisterView.validationError(RegisterView.PHONE_NUMBER_INCORRECT);

        } else if(firebaseUserDataModel.getPassword().length()<8){

            mRegisterView.validationError(RegisterView.PASSWORD_LENGTH_LESS);

        } else if(!firebaseUserDataModel.getPassword().equals(firebaseUserDataModel.getConfirmedPassword())){

            mRegisterView.validationError(RegisterView.PASSWORD_NO_MATCH);

        } else {
            registerUser(firebaseUserDataModel);
        }
    }

    private void registerUser(final FirebaseUserDataModel firebaseUserDataModel) {

        ServerUtils.mAuth.createUserWithEmailAndPassword(firebaseUserDataModel.getEmail(), firebaseUserDataModel.getPassword())
                .addOnCompleteListener(registerActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(getClass().getSimpleName(), "createUserWithEmail:success");
                            //mRegisterView.registrationSuccess();
                            saveUserData(firebaseUserDataModel);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(getClass().getSimpleName(), "createUserWithEmail:failure", task.getException());
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                mRegisterView.userExists();
                            } else{
                                mRegisterView.registrationFailed();
                            }
                        }
                    }
                });
    }

    private void saveUserData(final FirebaseUserDataModel firebaseUserDataModel) {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        ref.child(ServerUtils.USERS_ROUTE).push().setValue(firebaseUserDataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(getClass().getSimpleName(),"Write Successful");
                    mRegisterView.registrationSuccess();
                } else {
                    Log.d(getClass().getSimpleName(),"Write Unsuccessful");
                    mRegisterView.registrationFailed();
                }
                ServerUtils.mAuth.signOut();
            }
        });
    }

    private boolean checkForDigit(String phone){
        for(int i=0;i<phone.length();++i){
            if(phone.charAt(i)>='0' && phone.charAt(i)<='9'){

            } else{
                return false;
            }
        }
        return true;
    }
}
