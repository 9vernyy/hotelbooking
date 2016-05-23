package kz.alisher.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

import kz.alisher.hotel.R;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnLinkToRegister;
    private MaterialEditText inputEmail;
    private MaterialEditText inputPassword;
    private ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (currentUser != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Log.d("USERNAME", currentUser.getUsername());
        }

        setContentView(R.layout.activity_login);
        inputEmail = (MaterialEditText) findViewById(R.id.email_login);
        inputPassword = (MaterialEditText) findViewById(R.id.password_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputEmail.getText().toString().isEmpty() && !inputPassword.getText().toString().isEmpty()) {
                    checkLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void checkLogin(final String email, String password) {
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect login or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
