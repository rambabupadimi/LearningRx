package c.com.learningrx.viewproducts;

import java.util.List;

import c.com.learningrx.source.Product;

/**
 * Created by PCCS-0007 on 13-Mar-18.
 */

public class ViewProductContract {

    public interface View{

        void setLoadingIndicator(boolean active);
        void showProducts(List<Product> productList);
        void showNoProducts();
        void showProductActivityPage();
    }

    public interface Presenter{
        void loadProducts();
        void redirectToAddNewProduct();
    }
}
