package com.itheima.openchina.beans;

import android.support.v4.app.Fragment;

/**
 * 作者: DoctorHe
 * 描述:
 **/

public class FragmentInfo {
    public Fragment fragment;
    public CharSequence title;

    public FragmentInfo(CharSequence title, Fragment fragment) {
        this.fragment = fragment;
        this.title = title;
    }
}
