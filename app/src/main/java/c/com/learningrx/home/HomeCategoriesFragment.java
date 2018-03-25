package c.com.learningrx.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c.com.learningrx.R;

/**
 * Created by Ramu on 24-03-2018.
 */

public class HomeCategoriesFragment extends Fragment {

    int color;
    RecyclerView recyclerView;
    HomeCategoriesAdapter homeCategoriesAdapter;

    public HomeCategoriesFragment()
    {

    }
    @SuppressLint("ValidFragment")
    public HomeCategoriesFragment(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_categories_fragment, container, false);
        homeCategoriesAdapter    =   new HomeCategoriesAdapter(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.home_categories_fragment_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(homeCategoriesAdapter);
        return view;
    }
}
