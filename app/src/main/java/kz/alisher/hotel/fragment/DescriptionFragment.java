package kz.alisher.hotel.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kz.alisher.hotel.R;
import kz.alisher.hotel.model.Hotel;

/**
 * Created by Alisher Kozhabay on 5/15/2016.
 */
public class DescriptionFragment extends Fragment {
    private TextView around;
    private TextView amen;
    private TextView shortDesc;


    public static DescriptionFragment newInstance() {
        return new DescriptionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        around = (TextView) view.findViewById(R.id.around);
        amen = (TextView) view.findViewById(R.id.amenities);
        shortDesc = (TextView) view.findViewById(R.id.short_desc);
        Intent i = getActivity().getIntent();
        Hotel item = (Hotel) i.getSerializableExtra("hotel");
        around.setText(item.getAround());
        amen.setText(item.getAmen());
        shortDesc.setText(item.getShortDesc());
    }

}
