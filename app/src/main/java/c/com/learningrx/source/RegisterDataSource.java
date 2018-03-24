package c.com.learningrx.source;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramu on 27-10-2017.
 */

public interface RegisterDataSource {

    void registerUser(Register register);

    void saveProduct(Product product);

    interface LoadTasksCallback {

        void onTasksLoaded(List<Product> tasks);

        void onDataNotAvailable();
    }
    void getTasks(@NonNull LoadTasksCallback callback);

}
