package com.demo.nahidul.androiduicomponent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopupActivity extends AppCompatActivity {

    private TextView mLastName;
    private TextView mFirstName;
    private TextView mDOB;

    private Button showPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        showPopup = findViewById(R.id.btn_popup_window);

        getSupportActionBar().setTitle("Popup Window Activity");
    }

    public void showPopupWindow(View view) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        final View popupView = inflater.inflate(R.layout.popup_window, null);

        mLastName = popupView.findViewById(R.id.tv_popup_item_last_name);
        mFirstName = popupView.findViewById(R.id.tv_popup_item_first_name);
        mDOB = popupView.findViewById(R.id.tv_popup_item_dateof_birth);

        final PopupWindow popupWindow = new PopupWindow(popupView, ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                true);
        /*
        * Use Exact class name for accessing Layout params as it is dine above.
        * Do not use it like this :- ViewGroup.LayoutParams.WRAP_CONTENT
        * And of course pass true as 4th parameter indicating focusable true.
        * */

        mLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopup.setText(mLastName.getText());
                popupWindow.dismiss();
            }
        });

        mFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopup.setText(mFirstName.getText());
                popupWindow.dismiss();
            }
        });

        mDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopup.setText(mDOB.getText());
                popupWindow.dismiss();
            }
        });

        int arr[] = new int[2];
        view.getLocationOnScreen(arr);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        /**
         * Dismissing popup window on touching outside of its content area is already defined inside
         * popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE)) method.
         * And It works fine both on marshmellow and lollipop.
         * we don' need to invoke popupWindow.setOutsideTouchable(true);
         */
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,arr[0],arr[1]);

        //https://stackoverflow.com/questions/29165591/popup-window-dismiss-on-outside-touch-not-working

        //Important Link : https://stackoverflow.com/questions/38987442/how-to-make-a-simple-android-popup-window
    }
}
