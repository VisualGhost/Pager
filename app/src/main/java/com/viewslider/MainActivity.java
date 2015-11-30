package com.viewslider;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewslider.pages.PageManager;
import com.viewslider.pages.PageManagerImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        final PageIndicator pageIndicator = (PageIndicator)findViewById(R.id.indicator);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageIndicator.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        PageManager pageManager = new PageManagerImpl(this, getSupportFragmentManager());
        pageManager.setPager(viewPager);
    }
}

