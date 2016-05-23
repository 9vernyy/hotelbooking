package kz.alisher.hotel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kz.alisher.hotel.R;
import kz.alisher.hotel.model.Booking;
import kz.alisher.hotel.model.Hotel;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class BookingViewAdapter extends RecyclerView.Adapter<BookingViewAdapter.ViewHolder>{
    List<Booking> contents;
    Context ctx;

    public BookingViewAdapter(List<Booking> contents, Context ctx) {
        this.ctx = ctx;
        this.contents = contents;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Booking item = contents.get(position);
        holder.id.setText("Booking ID: "+item.getId());
        holder.totalPay.setText("Total pay: " +item.getTotalPay());
        holder.date.setText(item.getDate());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView totalPay;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.identificator);
            totalPay = (TextView) itemView.findViewById(R.id.totalPay);
            date = (TextView) itemView.findViewById(R.id.dateBook);
        }
    }
}
