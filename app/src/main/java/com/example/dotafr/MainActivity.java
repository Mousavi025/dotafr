// MainActivity.java

package com.example.dotafr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentList.OnItemClickListener {

    String[] titles = {"ونتی", "ژانگلی", "فلینز"};
    String[] descriptions = {"توضیح اول", "توضیح دوم", "توضیح سوم"};
    int[] images = {R.drawable.venti, R.drawable.zhongli, R.drawable.flins};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isLandscape = (findViewById(R.id.fragmentDetailContainer) != null);
        FragmentManager fm = getSupportFragmentManager();

        if (isLandscape) {
            // حالت افقی
            // بررسی می‌کنیم آیا فرگمنت لیست از قبل وجود دارد یا نه
            Fragment listFragment = fm.findFragmentById(R.id.fragmentListContainer);
            if (listFragment == null) {
                listFragment = FragmentList.newInstance(titles);
                fm.beginTransaction()
                        .replace(R.id.fragmentListContainer, listFragment)
                        .commit();
            }

            // بررسی می‌کنیم آیا فرگمنت جزئیات از قبل وجود دارد یا نه
            Fragment detailFragment = fm.findFragmentById(R.id.fragmentDetailContainer);
            if (detailFragment == null) {
                detailFragment = new FragmentDetail();
                Bundle b = new Bundle();
                b.putInt("img", images[0]);
                b.putString("title", titles[0]);
                b.putString("desc", descriptions[0]);
                detailFragment.setArguments(b);
                fm.beginTransaction()
                        .replace(R.id.fragmentDetailContainer, detailFragment)
                        .commit();
            }
        } else {
            // حالت عمودی
            // اگر در حالت عمودی هستیم و پشته بازگشت خالی است (یعنی اولین فرگمنت است)، لیست را نشان بده
            if (fm.getBackStackEntryCount() == 0) {
                Fragment listFragment = fm.findFragmentById(R.id.fragmentContainer);
                if (listFragment == null) {
                    listFragment = FragmentList.newInstance(titles);
                    fm.beginTransaction()
                            .replace(R.id.fragmentContainer, listFragment)
                            .commit();
                }
            }
        }
    }

    @Override
    public void onItemClicked(int pos) {
        boolean isLandscape = (findViewById(R.id.fragmentDetailContainer) != null);

        if (isLandscape) {
            // حالت افقی → آپدیت فرگمنت جزئیات
            FragmentDetail detail = (FragmentDetail) getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentDetailContainer);
            if (detail != null) {
                detail.updateData(images[pos], titles[pos], descriptions[pos]);
            }
        } else {
            // حالت عمودی → جایگزینی فرگمنت
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

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}