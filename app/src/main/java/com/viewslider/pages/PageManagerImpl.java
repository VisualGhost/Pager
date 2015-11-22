package com.viewslider.pages;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.viewslider.Mode;
import com.viewslider.fragments.PortfolioPage;
import com.viewslider.fragments.PositionsPage;
import com.viewslider.fragments.StreamPage;

public class PageManagerImpl implements PageManager {

    private final PageOrderManager mPageOrderManager;
    private final FragmentStatePagerAdapter mFragmentPagerAdapter;
    private final Context mContext;

    public PageManagerImpl(final Context context, FragmentManager fragmentManager) {
        mContext = context;
        mPageOrderManager = new PageOrderManagerImpl();
        mPageOrderManager.addPageType(PageType.STREAM);
        mPageOrderManager.addPageType(PageType.POSITIONS);
        mPageOrderManager.addPageType(PageType.PORTFOLIO);
        mFragmentPagerAdapter = new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(final int position) {
                PageType pageType = mPageOrderManager.getPageType(position, getMode(context));
                switch (pageType) {
                    case STREAM:
                        return StreamPage.getInstance();
                    case POSITIONS:
                        return PositionsPage.getInstance();
                    case PORTFOLIO:
                        return PortfolioPage.getInstance();
                }
                return null;
            }

            @Override
            public int getCount() {
                return mPageOrderManager.getPageCount();
            }
        };
    }

    private Mode getMode(Context context) {
        Configuration config = context.getResources().getConfiguration();
        final boolean isRTL = config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        return isRTL ? Mode.RTL : Mode.LTR;
    }

    @Override
    public void setPager(ViewPager viewPager) {
        viewPager.setAdapter(mFragmentPagerAdapter);
        selectStreamPage(viewPager, mPageOrderManager, mContext);
    }

    private void selectStreamPage(ViewPager viewPager, PageOrderManager pageOrderManager, Context context) {
        int streamRTLPosition = pageOrderManager.getPosition(PageType.STREAM, getMode(context));
        viewPager.setCurrentItem(streamRTLPosition);
    }
}
