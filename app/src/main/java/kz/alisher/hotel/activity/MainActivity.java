package kz.alisher.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import kz.alisher.hotel.R;
import kz.alisher.hotel.Utils.RecyclerItemClickListener;
import kz.alisher.hotel.adapter.HotelViewAdapter;
import kz.alisher.hotel.model.Hotel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private HotelViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<Hotel> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initRecyclerView();
        initDrawerAndNavView(toolbar);
        initProfile();
    }

    private void getHotels(){
        hotels = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("hotel");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    for (ParseObject obj : objects) {
                        Hotel hotel = new Hotel();
                        hotel.setId(obj.getObjectId());
                        hotel.setTitle(obj.getString("title"));
                        hotel.setPrice(obj.getString("price"));
                        hotel.setImage(obj.getString("image"));
                        hotel.setRating((float)obj.getDouble("rating"));
                        hotel.setAddress(obj.getString("address"));
                        hotel.setAround(obj.getString("around"));
                        hotel.setAmen(obj.getString("amen"));
                        hotel.setShortDesc(obj.getString("shortDesc"));
                        hotels.add(hotel);
                    }
                    mAdapter = new HotelViewAdapter(hotels, MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Log.d("ERROR", e.getMessage());
                }
            }
        });
    }

    private void initProfile(){
        View header = navigationView.getHeaderView(0);
        TextView fio = (TextView) header.findViewById(R.id.fio);
        TextView email = (TextView) header.findViewById(R.id.emailProfile);
        email.setText(ParseUser.getCurrentUser().getUsername());
        fio.setText(ParseUser.getCurrentUser().get("firstName") + " " + ParseUser.getCurrentUser().get("lastName"));
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.hotel_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getHotels();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Hotel hotel = hotels.get(position);
                Intent i = new Intent(MainActivity.this, DetailHotelActivity.class);
                i.putExtra("hotel", hotel);
                startActivity(i);
            }
        }));
    }

    private void initDrawerAndNavView(Toolbar toolbar) {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.booking) {
            startActivity(new Intent(this, BookingActivity.class));
        } else if (id == R.id.profile) {
            startActivity(new Intent(this, ProfileActivity.class));

        } else if (id == R.id.logout) {
            ParseUser.logOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
