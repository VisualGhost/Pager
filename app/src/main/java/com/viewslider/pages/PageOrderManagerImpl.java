package com.viewslider.pages;

import com.viewslider.Mode;

import java.util.ArrayList;
import java.util.List;

public class PageOrderManagerImpl implements PageOrderManager {
    private List<PageType> mPageTypeList;

    public PageOrderManagerImpl() {
        mPageTypeList = new ArrayList<>();
    }

    @Override
    public void addPageType(final PageType slide) {
        mPageTypeList.add(slide);
    }

    @Override
    public List<PageType> getPageTypes(final Mode mode) {
        if (mode == Mode.LTR) {
            return getLTRList();
        } else if (mode == Mode.RTL) {
            return getRTLList();
        }
        return null;
    }

    private List<PageType> getLTRList() {
        return mPageTypeList;
    }

    private List<PageType> getRTLList() {
        int size = mPageTypeList.size();
        List<PageType> rtlList = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            rtlList.add(mPageTypeList.get(i));
        }
        return rtlList;
    }

    @Override
    public int getPosition(final PageType slide, final Mode mode) {
        if (mode == Mode.LTR) {
            return getLTRPosition(slide);
        } else if (mode == Mode.RTL) {
            return getRTLPosition(slide);
        }
        return 0;
    }

    private int getLTRPosition(final PageType slide) {
        return mPageTypeList.indexOf(slide);
    }

    private int getRTLPosition(final PageType slide) {
        return getRTLList().indexOf(slide);
    }

    @Override
    public PageType getPageType(final int position, final Mode mode) {
        if (mode == Mode.LTR) {
            return getLTRPageType(position);
        } else if (mode == Mode.RTL) {
            return getRTLPageType(position);
        }
        return null;
    }

    @Override
    public int getPageCount() {
        return mPageTypeList.size();
    }

    private PageType getLTRPageType(int position) {
        return mPageTypeList.get(position);
    }

    private PageType getRTLPageType(int position) {
        return getRTLList().get(position);
    }
}
