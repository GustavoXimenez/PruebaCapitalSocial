package com.androidavanzado.capitalsocial.common;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class Functions {

    public void centerTitle(Activity activity) {
        ArrayList<View> textViews = new ArrayList<>();

        activity.getWindow().getDecorView().findViewsWithText(textViews, activity.getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if(textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if(textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for(View v : textViews) {
                    if(v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if(appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                //appCompatTextView.setTextSize(15);
            }
        }
    }

}
