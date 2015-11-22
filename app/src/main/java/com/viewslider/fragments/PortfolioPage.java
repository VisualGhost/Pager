package com.viewslider.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewslider.R;
import com.viewslider.pages.Page;
import com.viewslider.pages.PageType;

public class PortfolioPage extends Fragment implements Page {

    public static PortfolioPage getInstance() {
        return new PortfolioPage();
    }

    @Override
    public PageType getType() {
        return PageType.PORTFOLIO;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_portfolio, container, false);
    }
}
