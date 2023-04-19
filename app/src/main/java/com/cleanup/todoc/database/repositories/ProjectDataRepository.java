package com.cleanup.todoc.database.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.model.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectDataRepository {
        private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public LiveData<Project> getProject(long projectId){
        return this.projectDao.getProject(projectId);
    }

    public LiveData<List<Project>> getAllProject(){

        return this.projectDao.getAllProject();
    }

    public void createProject(Project project){
        this.projectDao.createProject(project);
    }
}
