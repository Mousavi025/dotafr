package com.example.dotafr;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentList extends Fragment {

    ListView listView;
    OnItemClickListener listener;

    String[] listItems = {"آیتم اول", "آیتم دوم", "آیتم سوم"};

    public interface OnItemClickListener {
        void onItemClicked(int pos);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnItemClickListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                listItems
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, v, pos, id) -> {
            listener.onItemClicked(pos);
        });

        return view;
    }
}
