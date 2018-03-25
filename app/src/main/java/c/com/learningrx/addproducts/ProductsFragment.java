package c.com.learningrx.addproducts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import c.com.learningrx.R;
import c.com.learningrx.source.RegisterRepository;
import c.com.learningrx.source.local.RegisterDatabase;
import c.com.learningrx.source.local.RegisterLocalDatasource;
import c.com.learningrx.source.remote.RegisterRemoteDatasource;
import c.com.learningrx.util.AppExecutors;
import c.com.learningrx.viewproducts.ViewProductsActivity;

/**
 * Created by PCCS-0007 on 13-Mar-18.
 */

public class ProductsFragment extends Fragment implements ProductsContract.View{


    EditText productName,productDescription,productPrice,productQuantity;
    Button productSave;

    ProductsPresenter productsPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment,container,false);
        initialiseIDs(view);
        initialiseObjects();
        initialiseClickListeners();
        return view;
    }

    private void initialiseObjects()
    {
        RegisterDatabase database = RegisterDatabase.getInstance(getContext());
        RegisterRepository registerRepository = RegisterRepository.getInstance(
                RegisterLocalDatasource.getInstance(new AppExecutors(),
                        database.registerDAO(),
                        database.productDAO()),RegisterRemoteDatasource.getInstance());
        productsPresenter   =   new ProductsPresenter(this,registerRepository);


    }
    private void initialiseIDs(View view)
    {
        productName = (EditText) view.findViewById(R.id.product_name);
        productDescription  = (EditText) view.findViewById(R.id.product_description);
        productPrice        = (EditText) view.findViewById(R.id.product_price);
        productQuantity  = (EditText) view.findViewById(R.id.product_quantity);

        productSave     = (Button) view.findViewById(R.id.product_save);
    }

    private void initialiseClickListeners()
    {
        productSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsPresenter.saveProduct();
            }
        });
    }

    @Override
    public String getProductName() {
        return productName.getText().toString();
    }

    @Override
    public Float getProductPrice() {
        return Float.parseFloat(productPrice.getText().toString());
    }

    @Override
    public String getProductDescription() {
        return productDescription.getText().toString();
    }

    @Override
    public int getProductQuantity() {
        return Integer.parseInt(productQuantity.getText().toString());
    }

    @Override
    public void showEmptyMessageForProductName(int resId) {
        productName.setHint(getString(resId));
    }

    @Override
    public void showEmptyMessageForProductPrice(int resId) {
        productPrice.setHint(getString(resId));
    }

    @Override
    public void showEmptyMessageForProductDescription(int resId) {
        productDescription.setHint(getString(resId));
    }

    @Override
    public void showEmptyMessageForProductQuantity(int resId) {
        productQuantity.setHint(getString(resId));
    }

    @Override
    public void redirectToViewProductDetailsPage() {
        Intent intent = new Intent(getContext(), ViewProductsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
