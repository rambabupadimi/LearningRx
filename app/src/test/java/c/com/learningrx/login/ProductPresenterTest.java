package c.com.learningrx.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import c.com.learningrx.R;
import c.com.learningrx.addproducts.ProductsContract;
import c.com.learningrx.addproducts.ProductsPresenter;
import c.com.learningrx.source.Product;
import c.com.learningrx.source.RegisterRepository;

/**
 * Created by PCCS-0007 on 16-Mar-18.
 */

public class ProductPresenterTest {

    private ProductsContract.Presenter presenter;
    private ProductsContract.View   view;
    private RegisterRepository registerRepository;

    @Before
    public void setUp() throws Exception
    {
        view        =   Mockito.mock(ProductsContract.View.class);
        registerRepository = Mockito.mock(RegisterRepository.class);
        presenter   =   new ProductsPresenter(view,registerRepository);
    }

    @Test
    public void whenProductNameIsEmptyShowError() throws Exception{
        Mockito.when(view.getProductName()).thenReturn("");
        Mockito.when(view.getProductPrice()).thenReturn(25.4f);
        Mockito.when(view.getProductDescription()).thenReturn("description");
        Mockito.when(view.getProductQuantity()).thenReturn(4);
        presenter.saveProduct();
        Mockito.verify(view).showEmptyMessageForProductName(R.string.product_name_is_mandatory);
    }


    @Test
    public void whenSaveTask() throws Exception{
        Product product = new Product("pName",24.5f,"pDesc",35);
        presenter.saveProductItem(product);

    }

}
