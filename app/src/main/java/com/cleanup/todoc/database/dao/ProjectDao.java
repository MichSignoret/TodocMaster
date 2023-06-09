package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.database.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    @Query("Select * From Project Where id = :projectId")
    LiveData<Project> getProject(long projectId);

    @Query("Select * From Project")
    LiveData<List<Project>> getAllProject();
}
