package com.cleanup.todoc.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.database.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task Where projectId = :projectId")
    LiveData<List<Task>> getTask(long projectId);

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllTasks();

    @Insert
    long insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Query("DELETE FROM Task Where Id = :taskId")
    int deleteTask(long taskId);
}
