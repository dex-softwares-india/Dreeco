package com.example.rohangoyal2014.dreeco;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rohangoyal2014.dreeco.models.LoginModel;
import com.example.rohangoyal2014.dreeco.presenters.LoginPresenter;
import com.example.rohangoyal2014.dreeco.utils.FirebaseUserDataModel;
import com.example.rohangoyal2014.dreeco.utils.ServerUtils;
import com.example.rohangoyal2014.dreeco.views.LoginView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FirstTimeActivity extends AppCompatActivity implements LoginView,View.OnClickListener{

    LoginPresenter loginPresenter;

    Button loginButton;
    TextView forgotPasswordView,notAMemberView;
    EditText emailView,passwordView;

    MaterialDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        loginPresenter=new LoginModel(this,this);

        ServerUtils.mAuth = FirebaseAuth.getInstance();

        loginButton=findViewById(R.id.login_button);
        forgotPasswordView=findViewById(R.id.forgot_pass);
        notAMemberView=findViewById(R.id.not_a_member);
        emailView=findViewById(R.id.mail_view);
        passwordView=findViewById(R.id.password_view);

        progressDialog=new MaterialDialog.Builder(this)
                .title(R.string.loading)
                .content(R.string.wait)
                .progress(true, 0).build();
        progressDialog.setCancelable(false);

        loginButton.setOnClickListener(this);
        forgotPasswordView.setOnClickListener(this);
        notAMemberView.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = ServerUtils.mAuth.getCurrentUser();
        proceedFurther(currentUser);
    }

    @Override
    public void loginSuccess(FirebaseUser user) {
        progressDialog.dismiss();
        proceedFurther(user);
        loginButton.setEnabled(true);
    }

    @Override
    public void loginFailed() {
        progressDialog.dismiss();
        loginButton.setEnabled(true);
        Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
        proceedFurther(null);
    }

    @Override
    public void dataValidationFailed() {
        progressDialog.dismiss();
        loginButton.setEnabled(true);
        Toast.makeText(this, getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void forgotPasswordEmailSendingFailed() {
        Toast.makeText(this, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
        forgotPasswordView.performClick();
        progressDialog.dismiss();
    }

    @Override
    public void forgotPasswordEmailSendingSuccess() {

        progressDialog.dismiss();
        final Snackbar snackbar=Snackbar.make(findViewById(R.id.coordinator_layout),getString(R.string.check_mail),Snackbar.LENGTH_SHORT);
        snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.show();
    }

    @Override
    public void forgotPasswordEmailTextEmpty() {
        Toast.makeText(this, getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        forgotPasswordView.performClick();
        progressDialog.dismiss();
    }


    private void proceedFurther(FirebaseUser user) {
        if(user!=null){
            startActivity(new Intent(this,MallActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.login_button:
                progressDialog.show();
                loginButton.setEnabled(false);
                loginPresenter.performLogin(emailView.getText().toString(),passwordView.getText().toString());
                break;
            case R.id.forgot_pass:
                //Dialog Work here
                new MaterialDialog.Builder(this)
                        .title("Forgot Password")
                        .content("We shall send a password reset e-mail to your ID.")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("Email","", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                progressDialog.show();
                                loginPresenter.forgotPassword(input.toString());
                            }
                        }).show();
                break;
            case R.id.not_a_member:
                startActivity(new Intent(FirstTimeActivity.this,RegisterActivity.class));
        }
    }
}
