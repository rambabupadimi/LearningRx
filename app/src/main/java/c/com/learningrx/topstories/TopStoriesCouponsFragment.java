package c.com.learningrx.topstories;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.com.learningrx.R;

/**
 * Created by Ramu on 24-03-2018.
 */

public class TopStoriesCouponsFragment extends Fragment{


    int color;

    public TopStoriesCouponsFragment()
    {

    }
    @SuppressLint("ValidFragment")
    public TopStoriesCouponsFragment(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_stories_coupons, container, false);
        return view;
    }
}
