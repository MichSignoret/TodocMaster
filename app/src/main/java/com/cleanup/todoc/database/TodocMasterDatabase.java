package com.cleanup.todoc.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.database.model.Project;
import com.cleanup.todoc.database.model.Task;

import java.util.concurrent.Executor;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TodocMasterDatabase extends RoomDatabase {

    private static volatile TodocMasterDatabase INSTANCE;

    public abstract TaskDao taskDao();

    public abstract ProjectDao projectDao();

    private Executor executor;

    public static TodocMasterDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocMasterDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TodocMasterDatabase.class, "MyDatabase.db")
                        .addCallback(prepopulateDatabase()).build();
            }

        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {

        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

            }
        };
    }
}
