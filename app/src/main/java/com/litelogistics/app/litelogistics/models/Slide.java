package com.litelogistics.app.litelogistics.models;

/**
 * Created by Lekan Adigun on 7/17/2018.
 */

public class Slide {

    public String title = "";
    public String subTitle = "";
    public int icon = 0;


    public Slide() {}

    public Slide(String title, String subTitle, int icon) {
        this.title = title;
        this.subTitle = subTitle;
        this.icon = icon;
    }
}
