package c.com.learningrx.viewproducts;

import android.util.Log;

import java.util.List;

import c.com.learningrx.addproducts.ProductsContract;
import c.com.learningrx.source.Product;
import c.com.learningrx.source.RegisterDataSource;
import c.com.learningrx.source.RegisterRepository;

/**
 * Created by PCCS-0007 on 13-Mar-18.
 */

public class ViewProductPresenter implements ViewProductContract.Presenter {


    ViewProductContract.View view;
    RegisterRepository registerRepository;
    public ViewProductPresenter(ViewProductContract.View view,RegisterRepository registerRepository)
    {
        this.view = view;
        this.registerRepository = registerRepository;
        loadProducts();

    }

    @Override
    public void loadProducts() {

        registerRepository.getTasks(new RegisterDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Product> tasks) {
                if(tasks!=null)
                {
                    view.showProducts(tasks);
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void redirectToAddNewProduct() {
        view.showProductActivityPage();
    }
}
