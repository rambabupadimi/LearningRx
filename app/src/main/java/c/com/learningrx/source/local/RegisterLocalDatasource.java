package c.com.learningrx.source.local;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import c.com.learningrx.source.Product;
import c.com.learningrx.source.Register;
import c.com.learningrx.source.RegisterDataSource;
import c.com.learningrx.util.AppExecutors;

/**
 * Created by Ramu on 25-11-2017.
 */

public class RegisterLocalDatasource implements RegisterDataSource{

    private static volatile RegisterLocalDatasource INSTANCE;

    private RegisterDAO registerDAO;
    private ProductDAO productDAO;

    AppExecutors appExecutors;

    // Prevent direct instantiation.
    private RegisterLocalDatasource(@NonNull AppExecutors appExecutors,
                                 @NonNull RegisterDAO registerDAO,
                                    @NonNull ProductDAO productDAO) {
        this.appExecutors = appExecutors;
        this.registerDAO = registerDAO;
        this.productDAO = productDAO;
    }

    public static RegisterLocalDatasource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull RegisterDAO registerDAO,
                                                      @NonNull ProductDAO productDAO) {
        if (INSTANCE == null) {
            synchronized (RegisterLocalDatasource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RegisterLocalDatasource(appExecutors, registerDAO,productDAO);
                }
            }
        }
        return INSTANCE;
    }



    @Override
    public void registerUser(final Register register) {

        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                registerDAO.insertUser(register);
            }
        };
        appExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void saveProduct(final Product product) {
        final Runnable saveProduct = new Runnable() {
            @Override
            public void run() {
                productDAO.insertProduct(product);
            }
        };
        appExecutors.diskIO().execute(saveProduct);
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Product> tasks = productDAO.getAllProducts();
                Log.i("tag","tasks are"+tasks);
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (tasks.isEmpty()) {
                            // This will be called if the table is new or just empty.
                            callback.onDataNotAvailable();
                        } else {
                            callback.onTasksLoaded(tasks);
                        }
                    }
                });
            }
        };

        appExecutors.diskIO().execute(runnable);
    }


}
