package com.example.sabita_sant.alarm;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Sabita_Sant on 08/10/16.
 */
public  class OptionMenu extends Fragment{

    Fragment frag;

    public OptionMenu() {
        frag = new Digital();



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.option_menu, container, false);
        getFragmentManager().beginTransaction().replace(R.id.clkContainer, frag).commit();



        return rootview;
    }



}

