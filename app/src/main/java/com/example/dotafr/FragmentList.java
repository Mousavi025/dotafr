// FragmentList.java

package com.example.dotafr;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentList extends Fragment {

    ListView listView;
    OnItemClickListener listener;

    // حذف شد: داده‌های تستی از اینجا حذف می‌شوند
    // String[] listItems = {"آیتم اول", "آیتم دوم", "آیتم سوم"};

    public interface OnItemClickListener {
        void onItemClicked(int pos);
    }

    // ++ اضافه شد: یک متد سازنده جدید برای پاس دادن داده‌ها ++
    public static FragmentList newInstance(String[] listItems) {
        FragmentList fragment = new FragmentList();
        Bundle args = new Bundle();
        args.putStringArray("list_items", listItems); // داده‌ها را در Bundle قرار می‌دهیم
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentList() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            listener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listView);

        String[] listItems = null;
        // ++ اضافه شد: داده‌ها را از Bundle که در newInstance ساختیم، می‌خوانیم ++
        if (getArguments() != null) {
            listItems = getArguments().getStringArray("list_items");
        }

        if (listItems != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    requireActivity(), // استفاده از requireActivity() امن‌تر است
                    android.R.layout.simple_list_item_1,
                    listItems
            );
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((adapterView, v, pos, id) -> {
                if (listener != null) {
                    listener.onItemClicked(pos);
                }
            });
        }

        return view;
    }
}