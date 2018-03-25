package c.com.learningrx.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import c.com.learningrx.R;

/**
 * Created by Ramu on 25-03-2018.
 */

public class HomeBestOffersAdapter extends RecyclerView.Adapter<HomeBestOffersAdapter.MyViewHolder> {

    Context context;
    public HomeBestOffersAdapter(Context context){
        this.context = context;
    }
    @Override
    public HomeBestOffersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.best_offers_adapter_layout, parent, false);

        return new HomeBestOffersAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeBestOffersAdapter.MyViewHolder holder, int position) {
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