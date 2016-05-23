package kz.alisher.hotel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kz.alisher.hotel.R;
import kz.alisher.hotel.model.Room;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class RoomViewAdapter extends RecyclerView.Adapter<RoomViewAdapter.ViewHolder>{
    List<Room> contents;
    Context ctx;

    public RoomViewAdapter(List<Room> contents, Context ctx) {
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
                .inflate(R.layout.item_room, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Room item = contents.get(position);
        holder.title.setText(item.getTitle());
        holder.price.setText("$"+item.getPrice());
        holder.numberOfPeople.setText(item.getNumberOfPeople());
        Picasso.with(holder.thumbnail.getContext()).load(item.getImage()).into(holder.thumbnail);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView numberOfPeople;
        public TextView title;
        public ImageView thumbnail;
        public Button price;

        public ViewHolder(View itemView) {
            super(itemView);
            numberOfPeople = (TextView) itemView.findViewById(R.id.number_of_people);
            title = (TextView) itemView.findViewById(R.id.title_room);
            price = (Button) itemView.findViewById(R.id.price_room);
            thumbnail = (ImageView) itemView.findViewById(R.id.room_image);
        }
    }
}
