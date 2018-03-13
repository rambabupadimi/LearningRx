package c.com.learningrx.viewproducts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import c.com.learningrx.R;
import c.com.learningrx.addproducts.ProductsActivity;
import c.com.learningrx.source.Product;
import c.com.learningrx.source.RegisterRepository;
import c.com.learningrx.source.local.RegisterDatabase;
import c.com.learningrx.source.local.RegisterLocalDatasource;
import c.com.learningrx.source.remote.RegisterRemoteDatasource;
import c.com.learningrx.util.AppExecutors;

/**
 * Created by PCCS-0007 on 13-Mar-18.
 */

public class ViewProductFragment extends Fragment implements ViewProductContract.View {


    RecyclerView recyclerView;
    ViewProductAdapter viewProductAdapter;
    List<Product> productArrayList=new ArrayList<>();
    ViewProductPresenter viewProductPresenter;
    Button addProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_products_fragment,container,false);
        initialiseIDs(view);
        initialiseAdapter();
        initialisePresenter();
        initialiseClickListeners();
        return view;
    }

    private void initialiseClickListeners(){

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewProductPresenter.redirectToAddNewProduct();
            }
        });
    }

    private void initialisePresenter()
    {
            RegisterDatabase database = RegisterDatabase.getInstance(getContext());
            RegisterRepository registerRepository = RegisterRepository.getInstance(
                    RegisterLocalDatasource.getInstance(new AppExecutors(),
                            database.registerDAO(),
                            database.productDAO()),RegisterRemoteDatasource.getInstance());
            viewProductPresenter   =   new ViewProductPresenter(this,registerRepository);

    }

    private void initialiseAdapter()
    {
        viewProductAdapter = new ViewProductAdapter(getContext(),productArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(viewProductAdapter);
    }
    private void initialiseIDs(View view)
    {
        recyclerView = view.findViewById(R.id.recyclerview);
        addProduct  =   view.findViewById(R.id.add_product);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showProducts(List<Product> productList) {

      if(productList!=null) {
          productArrayList.addAll(productList);
          viewProductAdapter.addProducts(productArrayList);
      }
    }

    @Override
    public void showNoProducts() {

    }


    @Override
    public void showProductActivityPage() {
        Intent intent = new Intent(getContext(), ProductsActivity.class);
        startActivity(intent);
    }


}
