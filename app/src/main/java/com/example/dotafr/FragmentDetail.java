package com.example.dotafr;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentDetail extends Fragment {

    ImageView img;
    TextView title, desc;

    public FragmentDetail() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        img = view.findViewById(R.id.img);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        // اگر از حالت عمودی آمده‌ایم، مقادیر از باندل می‌گیرند
        Bundle b = getArguments();
        if (b != null) {
            updateData(b.getInt("img"), b.getString("title"), b.getString("desc"));
        }

        return view;
    }

    public void updateData(int imageRes, String titleText, String descText) {
        img.setImageResource(imageRes);
        title.setText(titleText);
        desc.setText(descText);
    }
}
