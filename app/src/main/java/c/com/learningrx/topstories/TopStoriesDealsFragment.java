package c.com.learningrx.topstories;

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

public class TopStoriesDealsFragment extends Fragment{
    int color;
RecyclerView recyclerView;
TopStoriesDealsAdapter topStoriesCouponsAdapter;

    public TopStoriesDealsFragment()
    {

    }
    @SuppressLint("ValidFragment")
    public TopStoriesDealsFragment(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_stories_deals, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.top_stories_deals_fragment_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        topStoriesCouponsAdapter = new TopStoriesDealsAdapter(getContext());
        recyclerView.setAdapter(topStoriesCouponsAdapter);

        return view;
    }
}
