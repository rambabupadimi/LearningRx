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

public class HomeTopStoriesFragment  extends Fragment{
    int color;
    RecyclerView recyclerView;
    HomeTopStoriesAdapter homeTopStoriesAdapter;

    public HomeTopStoriesFragment()
    {

    }
    @SuppressLint("ValidFragment")
    public HomeTopStoriesFragment(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_top_stories_fragment, container, false);

        homeTopStoriesAdapter    =   new HomeTopStoriesAdapter(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.home_top_stories_fragment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeTopStoriesAdapter);

        return view;
    }
}
