package com.viewslider.pages;

import com.viewslider.Mode;

import java.util.List;

public interface PageOrderManager {

    void addPageType(PageType page);

    List<PageType> getPageTypes(Mode mode);

    int getPosition(PageType page, Mode mode);

    PageType getPageType(int position, Mode mode);

    // TODO cover under test
    int getPageCount();
    
}
