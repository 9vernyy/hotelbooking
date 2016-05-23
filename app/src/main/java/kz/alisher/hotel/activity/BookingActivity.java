package kz.alisher.hotel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import kz.alisher.hotel.R;
import kz.alisher.hotel.Utils.RecyclerItemClickListener;
import kz.alisher.hotel.adapter.BookingViewAdapter;
import kz.alisher.hotel.adapter.RoomViewAdapter;
import kz.alisher.hotel.model.Booking;
import kz.alisher.hotel.model.Room;

public class BookingActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Booking> bookings;
    private BookingViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.booking_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getBookings();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        }));
    }

    private void getBookings() {
        bookings = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Booking");
        query.whereEqualTo("user_id", ParseUser.getCurrentUser().getObjectId());
        try {
            List<ParseObject> parseObjects = query.find();
            for (ParseObject obj : parseObjects) {
                Booking book = new Booking();
                book.setId(obj.getObjectId());
                book.setDate(obj.getString("date"));
                book.setTotalPay(obj.getDouble("totalPay"));
                bookings.add(book);
                Log.d("BOOKINGS", bookings.size()+"");
            }
            Log.d("BOOKINGS 1", bookings.size()+"");
            mAdapter = new BookingViewAdapter(bookings, BookingActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
