package com.cleanup.todoc.ui;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.database.model.Project;
import com.cleanup.todoc.database.model.Task;
import com.cleanup.todoc.database.repositories.ProjectDataRepository;
import com.cleanup.todoc.database.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {
        private final ProjectDataRepository projectDataSource;
        private final TaskDataRepository taskDataSource;
        private final Executor executor;


    public TaskViewModel(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, Executor executor) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    public void init() {
        if (projectDataSource.getAllProject().getValue() != null){
            return;
        }
        else {
            for (Project elmt:Project.getAllProjects()
                 ) {
                executor.execute(() -> projectDataSource.createProject(elmt));
            }

        }

    }

    public LiveData<List<Project>> getAllProjects() {
        this.init();
        return this.projectDataSource.getAllProject();
    }

    public LiveData<List<Task>> getAllTasks(){
        return this.taskDataSource.getAllTask();
    }

    public void deleteItem(long taskId) {
        executor.execute(() -> taskDataSource.deleteTask(taskId));
    }

    public void createItem(long id, long projectId, String name, long creationTimestamp) {
        executor.execute(() -> {
            taskDataSource.createTask(new Task(id, projectId, name, creationTimestamp));
        });


    }
}
