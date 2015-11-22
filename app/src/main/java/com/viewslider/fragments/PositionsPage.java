package com.viewslider.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewslider.R;
import com.viewslider.pages.Page;
import com.viewslider.pages.PageType;

public class PositionsPage extends Fragment implements Page {

    public static PositionsPage getInstance() {
        return new PositionsPage();
    }

    @Override
    public PageType getType() {
        return PageType.POSITIONS;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_positions, container, false);
    }
}
