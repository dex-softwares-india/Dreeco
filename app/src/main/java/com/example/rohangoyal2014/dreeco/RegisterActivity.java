package com.example.rohangoyal2014.dreeco;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rohangoyal2014.dreeco.models.RegisterModel;
import com.example.rohangoyal2014.dreeco.presenters.RegisterPresenter;
import com.example.rohangoyal2014.dreeco.utils.FirebaseUserDataModel;
import com.example.rohangoyal2014.dreeco.views.LoginView;
import com.example.rohangoyal2014.dreeco.views.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    TextInputEditText firstNameView,lastNameView,emailView,phoneView,passwordView,confirmPasswordView;
    FloatingActionButton registerButton;

    MaterialDialog progressDialog;

    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle(getString(R.string.register));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstNameView=findViewById(R.id.first_name_view);
        lastNameView=findViewById(R.id.last_name_view);
        emailView=findViewById(R.id.email_view);
        phoneView=findViewById(R.id.phone_view);
        passwordView=findViewById(R.id.pass_view);
        confirmPasswordView=findViewById(R.id.conf_pass_view);

        registerButton=findViewById(R.id.register_btn);

        progressDialog=new MaterialDialog.Builder(this)
                .title(R.string.loading)
                .content(R.string.wait)
                .progress(true, 0).build();
        progressDialog.setCancelable(false);

        registerPresenter=new RegisterModel(this,this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                registerButton.setEnabled(false);
                registerPresenter.performRegistration(new FirebaseUserDataModel(
                        firstNameView.getText().toString(),
                        lastNameView.getText().toString(),
                        phoneView.getText().toString(),
                        emailView.getText().toString(),
                        passwordView.getText().toString(),
                        confirmPasswordView.getText().toString()
                ));
            }
        });
    }

    @Override
    public void registrationSuccess() {

        progressDialog.dismiss();
        registerButton.setEnabled(true);
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
        Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registrationFailed() {

        progressDialog.dismiss();
        registerButton.setEnabled(true);
        Toast.makeText(this, getString(R.string.register_failed), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void userExists() {

        progressDialog.dismiss();
        registerButton.setEnabled(true);
        Toast.makeText(this, getString(R.string.user_exists), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validationError(int code) {

        progressDialog.dismiss();
        registerButton.setEnabled(true);
        String msg="";
        //status=true denotes if the message is empty field message
        boolean status=false;

        //The object on which the error is to be set
        TextInputEditText intendedObject;

        switch (code){
            case RegisterView.FIRST_NAME_EMPTY:
                msg="First Name";
                status=true;
                intendedObject=firstNameView;
                break;
            case RegisterView.LAST_NAME_EMPTY:
                msg="Last Name";
                status=true;
                intendedObject=lastNameView;
                break;
            case RegisterView.EMAIL_EMPTY:
                msg="Email";
                intendedObject=emailView;
                status=true;
                break;
            case RegisterView.PHONE_EMPTY:
                msg="Phone";
                intendedObject=phoneView;
                status=true;
                break;
            case RegisterView.PASSWORD_EMPTY:
                msg="Password";
                intendedObject=passwordView;
                status=true;
                break;
            case RegisterView.CONFIRM_PASSWORD_EMPTY:
                msg="Confirm Password";
                intendedObject=confirmPasswordView;
                status=true;
                break;
            case RegisterView.PASSWORD_LENGTH_LESS:
                intendedObject=passwordView;
                msg=getString(R.string.password_length_constraint);
                break;
            case RegisterView.PASSWORD_NO_MATCH:
                intendedObject=confirmPasswordView;
                msg=getString(R.string.password_match_constraint);
                break;
            case RegisterView.PHONE_NUMBER_INCORRECT:
                intendedObject=phoneView;
                msg=getString(R.string.phone_constraint);
                break;
            default:
                msg="Error!";
                intendedObject=emailView;

        }

        if(status) {
            msg += " " + getString(R.string.can_not_be_empty);
        }

        intendedObject.setError(msg);

    }
}
