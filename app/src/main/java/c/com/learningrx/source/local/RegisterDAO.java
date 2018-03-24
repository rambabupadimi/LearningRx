package c.com.learningrx.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import c.com.learningrx.source.Register;

/**
 * Created by Ramu on 25-11-2017.
 */

@Dao
public interface RegisterDAO {

    @Query("SELECT * FROM register")
    List<Register> getRegisterUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(Register register);


}
