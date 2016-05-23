package kz.alisher.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import kz.alisher.hotel.R;
import kz.alisher.hotel.Utils.ProgressGenerator;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnLinkToLogin;
    private MaterialEditText inputEmail;
    private MaterialEditText inputPassword;
    private MaterialEditText inputFirstName;
    private MaterialEditText inputLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputFirstName = (MaterialEditText) findViewById(R.id.fname);
        inputLastName = (MaterialEditText) findViewById(R.id.lname);
        inputEmail = (MaterialEditText) findViewById(R.id.email);
        inputPassword = (MaterialEditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String first_name = inputFirstName.getText().toString().trim();
                String last_name = inputLastName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                if (!first_name.isEmpty() && !last_name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    registerUser(first_name, last_name, email, password);
                } else {
                    Toast.makeText(getApplicationContext(), "All fields required!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void registerUser(String firstName, String lastName, String email, String password) {
        final ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(RegisterActivity.this, "User Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Saving user failed.", Toast.LENGTH_SHORT).show();
                    Log.w("", "Error : " + e.getMessage() + ":::" + e.getCode());
                    if (e.getCode() == 202) {
                        Toast.makeText(RegisterActivity.this, "Username already taken. \n Please choose another username.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
