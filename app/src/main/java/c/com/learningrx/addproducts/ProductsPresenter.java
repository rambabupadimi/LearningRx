package c.com.learningrx.addproducts;
import c.com.learningrx.R;
import c.com.learningrx.source.Product;
import c.com.learningrx.source.RegisterRepository;

/**
 * Created by PCCS-0007 on 13-Mar-18.
 */

public class ProductsPresenter implements ProductsContract.Presenter{


    ProductsContract.View view;
    RegisterRepository registerRepository;
    public ProductsPresenter(ProductsContract.View view,RegisterRepository registerRepository)
    {
        this.view = view;
        this.registerRepository = registerRepository;
    }

    @Override
    public void saveProduct() {
            String productName = view.getProductName().toString();
            if(productName.isEmpty())
            {
                view.showEmptyMessageForProductName(R.string.product_name_is_mandatory);
                return;
            }

            String productDescription = view.getProductDescription().toString();
            if(productDescription.isEmpty())
            {
                view.showEmptyMessageForProductDescription(R.string.product_description_is_mandatory);
                return;
            }

            String productPrice =   view.getProductPrice().toString();
            if(productPrice.isEmpty())
            {
                view.showEmptyMessageForProductPrice(R.string.product_price_is_mandatory);
                return;
            }

            int productQuantity = view.getProductQuantity();
            if(productQuantity==0)
            {
                view.showEmptyMessageForProductQuantity(R.string.product_quantity_is_mandatory);
                return;
            }

            saveProductRepository(new Product(productName,Float.parseFloat(productPrice),productDescription,productQuantity));

    }

    @Override
    public void saveProductItem(Product product) {
        saveProductRepository(product);
    }

    private void saveProductRepository(Product product)
    {
            registerRepository.saveProduct(product);
            view.redirectToViewProductDetailsPage();
    }

    @Override
    public void editProduct() {

    }

    @Override
    public void deleteProduct() {

    }
}
