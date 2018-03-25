package c.com.learningrx.viewproducts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import c.com.learningrx.R;
import c.com.learningrx.source.Product;

/**
 * Created by PCCS-0007 on 13-Mar-18.
 */

public class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.MyViewHolder> {

    Context context;
    List<Product> productList;
    public ViewProductAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;
    }
    @Override
    public ViewProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);

        return new ViewProductAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewProductAdapter.MyViewHolder holder, int position) {
                Product product = productList.get(position);
                holder.productName.setText(product.getProductName());
                holder.productDescription.setText(product.getProductDescription());
                holder.productQuantity.setText(""+product.getProductQuantity());
                holder.productPrice.setText(""+product.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productList!=null?productList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView productName,productPrice,productQuantity,productDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.adapter_product_name);
            productDescription  = (TextView) itemView.findViewById(R.id.adapter_product_description);
            productPrice        = (TextView) itemView.findViewById(R.id.adapter_product_price);
            productQuantity     = (TextView) itemView.findViewById(R.id.adapter_product_quantity);
        }
    }

    public void addProducts( List<Product> prod)
    {
      //  productList.clear();
        productList.addAll(prod);
        this.notifyDataSetChanged();
    }

}
