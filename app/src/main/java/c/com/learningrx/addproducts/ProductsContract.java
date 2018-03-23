package c.com.learningrx.addproducts;

import android.support.annotation.StringRes;

import c.com.learningrx.source.Product;

/**
 * Created by PCCS-0007 on 13-Mar-18.
 */

public class ProductsContract {
    public interface View
    {
        String getProductName();
        Float getProductPrice();
        String getProductDescription();
        int getProductQuantity();

        void showEmptyMessageForProductName(@StringRes int resId);
        void showEmptyMessageForProductPrice(@StringRes int resId);
        void showEmptyMessageForProductDescription(@StringRes int resId);
        void showEmptyMessageForProductQuantity(@StringRes int resId);

        void redirectToViewProductDetailsPage();
    }

    public interface Presenter
    {
       void saveProduct();
       void saveProductItem(Product product);
       void editProduct();
       void deleteProduct();
    }
}
