package c.com.learningrx.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.com.learningrx.R;

/**
 * Created by Ramu on 24-03-2018.
 */

public class HomeBestOffersFragment extends Fragment {

    int color;
    RecyclerView recyclerView;
    HomeBestOffersAdapter homeBestOffersAdapter;
    public HomeBestOffersFragment()
    {

    }
    @SuppressLint("ValidFragment")
    public HomeBestOffersFragment(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_best_offers_fragment, container, false);
        homeBestOffersAdapter    =   new HomeBestOffersAdapter(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.home_best_offers_fragment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeBestOffersAdapter);
        return view;
    }
}
