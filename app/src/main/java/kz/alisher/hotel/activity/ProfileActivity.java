package kz.alisher.hotel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseUser;

import kz.alisher.hotel.R;

public class ProfileActivity extends AppCompatActivity {
    private TextView email;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(ParseUser.getCurrentUser().getString("firstName"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = (TextView) findViewById(R.id.emailTxt);
        name = (TextView) findViewById(R.id.fullname);

        email.setText(ParseUser.getCurrentUser().getUsername());
        name.setText(ParseUser.getCurrentUser().get("firstName") + " " + ParseUser.getCurrentUser().get("lastName"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
