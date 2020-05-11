package com.hhjt.study.room_orm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hhjt.study.retrofit.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**访问数据库
 * Created by SCY on 2018/8/27 at 9:01.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> load(String userId);

    @Query("select * from user")
    LiveData<List<User>> getAll();

    @Delete
    void delete(List<User> user);

}
