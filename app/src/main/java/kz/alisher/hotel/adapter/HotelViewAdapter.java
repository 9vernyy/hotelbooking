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
import kz.alisher.hotel.model.Hotel;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class HotelViewAdapter extends RecyclerView.Adapter<HotelViewAdapter.ViewHolder> {
    List<Hotel> contents;
    Context ctx;

    public HotelViewAdapter(List<Hotel> contents, Context ctx) {
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
                .inflate(R.layout.item_hotel, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hotel item = contents.get(position);
        holder.title.setText(item.getTitle());
        holder.price.setText(item.getPrice());
        holder.rating.setRating(item.getRating());
        holder.address.setText(item.getAddress());
        Picasso.with(holder.thumbnail.getContext()).load(item.getImage()).into(holder.thumbnail);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView address;
        public RatingBar rating;
        public ImageView thumbnail;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_hotel);
            price = (TextView) itemView.findViewById(R.id.price_hotel);
            address = (TextView) itemView.findViewById(R.id.address_hotel);
            rating = (RatingBar) itemView.findViewById(R.id.rating_hotel);
            thumbnail = (ImageView) itemView.findViewById(R.id.image_hotel);
        }
    }
}
