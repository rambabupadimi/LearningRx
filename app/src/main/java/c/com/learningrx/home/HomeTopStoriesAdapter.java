package c.com.learningrx.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import c.com.learningrx.R;
import c.com.learningrx.topstories.TopStoriesDetailsActivity;

/**
 * Created by Ramu on 25-03-2018.
 */

public class HomeTopStoriesAdapter extends RecyclerView.Adapter<HomeTopStoriesAdapter.MyViewHolder> {

    Context context;
    public HomeTopStoriesAdapter(Context context){
        this.context = context;
    }
    @Override
    public HomeTopStoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_stories_adapter_layout, parent, false);
        return new HomeTopStoriesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeTopStoriesAdapter.MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TopStoriesDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 12;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addProducts( )
    {
        //  productList.clear();
        this.notifyDataSetChanged();
    }

}