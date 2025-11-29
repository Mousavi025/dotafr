package com.example.dotafr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentList.OnItemClickListener {

    // داده‌های نمونه
    String[] titles = {"ونتی", "ژانگلی", "فلینز"};
    String[] descriptions = {"توضیح اول", "توضیح دوم", "توضیح سوم"};
    int[] images = {R.drawable.venti, R.drawable.zhongli, R.drawable.flins};

    boolean isLandscape = false; // وضعیت افقی یا عمودی

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // بررسی حالت افقی یا عمودی
        isLandscape = findViewById(R.id.fragmentList) != null;

        if (savedInstanceState == null) {
            if (isLandscape) {
                // حالت Landscape → هر دو فرگمنت کنار هم
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentList, new FragmentList())
                        .replace(R.id.fragmentDetail, new FragmentDetail())
                        .commit();
            } else {
                // حالت Portrait → فقط فرگمنت لیست
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new FragmentList())
                        .commit();
            }
        }
    }

    @Override
    public void onItemClicked(int pos) {
        if (isLandscape) {
            // حالت افقی → آپدیت فرگمنت جزئیات
            FragmentDetail detail = (FragmentDetail) getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentDetail);
            if (detail != null) {
                detail.updateData(images[pos], titles[pos], descriptions[pos]);
            }
        } else {
            // حالت عمودی → جایگزین فرگمنت لیست با فرگمنت جزئیات
            FragmentDetail detailFragment = new FragmentDetail();
            Bundle b = new Bundle();
            b.putInt("img", images[pos]);
            b.putString("title", titles[pos]);
            b.putString("desc", descriptions[pos]);
            detailFragment.setArguments(b);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, detailFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
