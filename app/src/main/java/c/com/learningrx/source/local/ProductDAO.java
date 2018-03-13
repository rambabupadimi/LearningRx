package c.com.learningrx.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import c.com.learningrx.source.Product;

/**
 * Created by PCCS-0007 on 13-Mar-18.
 */
@Dao
public interface ProductDAO {

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

}
