package kz.alisher.hotel.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kz.alisher.hotel.R;
import kz.alisher.hotel.Utils.RecyclerItemClickListener;
import kz.alisher.hotel.activity.MainActivity;
import kz.alisher.hotel.adapter.HotelViewAdapter;
import kz.alisher.hotel.adapter.RoomViewAdapter;
import kz.alisher.hotel.model.Hotel;
import kz.alisher.hotel.model.Room;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class RoomFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private RecyclerView mRecyclerView;
    private RoomViewAdapter mAdapter;
    private List<Room> rooms;
    private View orderView;
    private MaterialStyledDialog dialogOrder;
    private TextView dateTextViewFrom;
    private TextView dateTextViewTo;
    private TextView totalPrice;
    private MaterialEditText fn;
    private MaterialEditText ln;
    private Hotel item;
    private Room roomItem;
    private String date;
    private double totalPay;

    public static RoomFragment newInstance() {
        return new RoomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        orderView = inflater.inflate(R.layout.order_view, null);
        openDialog();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.room_rv);
        getHotels();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                roomItem = rooms.get(position);
                dialogOrder.show();
            }
        }));
        return view;
    }

    private void getHotels(){
        Intent i = getActivity().getIntent();
        item = (Hotel) i.getSerializableExtra("hotel");
        rooms = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Room");
        query.whereEqualTo("hotel_id", item.getId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    for (ParseObject obj : objects) {
                        Room room = new Room();
                        room.setId(obj.getObjectId());
                        room.setImage(obj.getString("image"));
                        room.setPrice(obj.getDouble("price"));
                        room.setTitle(obj.getString("title"));
                        room.setNumberOfPeople(obj.getString("numberOfPeople"));
                        rooms.add(room);
                    }
                    mAdapter = new RoomViewAdapter(rooms, getActivity());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Log.d("ERROR", e.getMessage());
                }
            }
        });
    }

    private void openDialog() {
        dialogOrder = new MaterialStyledDialog(getActivity())
                .setIcon(R.drawable.order)
                .withDialogAnimation(true)
                .setHeaderColor(R.color.orderColor)
                .setCustomView(orderView, 20, 20, 20, 0)
                .setPositive("Book", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        if (!dateTextViewTo.getText().toString().isEmpty() && !dateTextViewFrom.getText().toString().isEmpty()){
                            saveBooking();
                            Toast.makeText(getActivity(), "Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Please pick date to continue", Toast.LENGTH_SHORT).show();
                            dialog.show();
                        }

                    }
                })
                .setNegative("Cancel", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();

        Button dateButton = (Button) orderView.findViewById(R.id.date_button);
        fn = (MaterialEditText) orderView.findViewById(R.id.fn_order);
        ln = (MaterialEditText) orderView.findViewById(R.id.ln_order);
        fn.setText(ParseUser.getCurrentUser().getString("firstName"));
        ln.setText(ParseUser.getCurrentUser().getString("lastName"));
        dateTextViewFrom = (TextView)orderView.findViewById(R.id.date_textview_from);
        totalPrice = (TextView)orderView.findViewById(R.id.total);
        dateTextViewTo = (TextView)orderView.findViewById(R.id.date_textview_to);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                        RoomFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");
        if(dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        date = "From: "+dayOfMonth+"/"+(++monthOfYear)+"/"+year+" To: "+dayOfMonthEnd+"/"+(++monthOfYearEnd)+"/"+yearEnd;
        dateTextViewFrom.setText(dayOfMonth+"/"+(++monthOfYear)+"/"+year);
        dateTextViewTo.setText(dayOfMonthEnd+"/"+(++monthOfYearEnd)+"/"+yearEnd);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        DateTime from = formatter.parseDateTime(dayOfMonth+"-"+(++monthOfYear)+"-"+year);
        DateTime to = formatter.parseDateTime(dayOfMonthEnd+"-"+(++monthOfYearEnd)+"-"+yearEnd);
        long days = Days.daysBetween(from.toDateMidnight() , to.toDateMidnight() ).getDays();
        totalPay = days * roomItem.getPrice();
        totalPrice.setText(""+ totalPay);
    }

    private void saveBooking(){
        ParseObject obj = new ParseObject("Booking");
        obj.put("user_id", ParseUser.getCurrentUser().getObjectId());
        obj.put("hotel_id", item.getId());
        obj.put("room_id", roomItem.getId());
        obj.put("date", date);
        obj.put("totalPay", totalPay);
        obj.saveInBackground();
    }
}
