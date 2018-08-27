package com.hhjt.study.room_orm;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.hhjt.study.retrofit.User;

/**创建数据库
 * Created by SCY on 2018/8/27 at 8:56.
 */
@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class IDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
