package com.example.pratamajambipelayangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class Intro extends AppCompatActivity {

    private static final int MAX_STEP = 3;
    private Button btn_got_it;
    private Button btn_no_npwp;
    private Button btn_lanjut;
    private TextView txtDeskripsi;
    private String[] title_array = {
            "Selamat Datang!", "Jadi Cerdas!",
            "Login dengan NPWP"
    };
    private String[] description_array = {
            "", "Jadi cerdas dengan menggunakan aplikasi KPP Pratama Jambi Pelayangan yang mengintegrasikan berbagai layanan perpajakan",
            "Jelajahi fitur penuh dengan login menggunakan Nomor Pokok Wajib Pajak (NPWP). Masuk dengan login atau tanpa login"
    };

    private String[] title_bawah_text = {
            "Wajib Pajak KPP Pratama Jambi Pelayangan", "",
            ""
    };

    private int[] about_images_array = {
            R.drawable.laki, R.drawable.perempuan,
            R.drawable.bincang
    };
    private int[] color_array = {
            R.color.colorWhite, R.color.colorWhite,
            R.color.colorWhite
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initComponent();

    }

    private void initComponent() {
        final ViewPager viewPager = findViewById(R.id.view_pager);
        btn_got_it = findViewById(R.id.btn_got_it);
        btn_no_npwp = findViewById(R.id.btn_nonpwp);
        btn_lanjut = findViewById(R.id.btn_lanjut);

        bottomProgressDots(0);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btn_got_it.setVisibility(View.GONE);
        btn_no_npwp.setVisibility(View.GONE);
        btn_lanjut.setVisibility(View.VISIBLE);

        btn_lanjut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int current = getItem(+1);
                if (current < title_array.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                }
            }
        });

        btn_got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro.this, Utama.class);
                startActivity(intent);
            }
        });

    }

    private int getItem(int i) {
        ViewPager viewPager = findViewById(R.id.view_pager);
        return viewPager.getCurrentItem() + i;
    }

    private void bottomProgressDots(int index) {
        LinearLayout dotsLayout = findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 18;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(5, 0, 5, 0);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.dot);
            dots[i].setColorFilter(getResources().getColor(R.color.colorGray), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        dots[index].setImageResource(R.drawable.dot);
        dots[index].setColorFilter(getResources().getColor(R.color.colorYellow), PorterDuff.Mode.SRC_IN);
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);
            if (position == title_array.length - 1) {
                btn_got_it.setVisibility(View.VISIBLE);
                btn_no_npwp.setVisibility(View.VISIBLE);
                btn_lanjut.setVisibility(View.GONE);
            } else {
                btn_got_it.setVisibility(View.GONE);
                btn_no_npwp.setVisibility(View.GONE);
                btn_lanjut.setVisibility(View.VISIBLE);
        }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

    };


    public class MyViewPagerAdapter extends PagerAdapter {

        MyViewPagerAdapter() {
        }

        @NotNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_intro, container, false);
            ((TextView) view.findViewById(R.id.title)).setText(title_array[position]);
            ((TextView) view.findViewById(R.id.description)).setText(description_array[position]);
            ((TextView) view.findViewById(R.id.title_bawah)).setText(title_bawah_text[position]);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);
            view.findViewById(R.id.lyt_parent).setBackgroundColor(getResources().getColor(color_array[position]));
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return title_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
