package com.example.rohangoyal2014.dreeco;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rohangoyal2014.dreeco.models.LoginModel;
import com.example.rohangoyal2014.dreeco.presenters.LoginPresenter;
import com.example.rohangoyal2014.dreeco.utils.ServerUtils;
import com.example.rohangoyal2014.dreeco.views.LoginView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstTimeActivity extends AppCompatActivity implements LoginView,View.OnClickListener{

    LoginPresenter loginPresenter;
    Button loginButton;
    TextView forgotPasswordView,notAMemberView;
    EditText emailView,passwordView;


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

        loginButton.setOnClickListener(this);
        forgotPasswordView.setOnClickListener(this);
        notAMemberView.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = ServerUtils.mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void loginSuccess(FirebaseUser user) {
        updateUI(user);
        loginButton.setEnabled(true);
    }

    @Override
    public void loginFailed() {
        loginButton.setEnabled(true);
        Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
        updateUI(null);
    }

    @Override
    public void dataValidationFailed(int code) {
        loginButton.setEnabled(true);
        Toast.makeText(this, getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
    }


    private void updateUI(FirebaseUser user) {
        if(user!=null){
            Log.d(getClass().getSimpleName(),"All OK");
        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.login_button:
                loginButton.setEnabled(false);
                loginPresenter.performLogin(emailView.getText().toString(),passwordView.getText().toString());
                break;
            case R.id.forgot_pass:
                //Dialog Work here
                break;
            case R.id.not_a_member:
                startActivity(new Intent(FirstTimeActivity.this,RegisterActivity.class));
        }
    }
}
