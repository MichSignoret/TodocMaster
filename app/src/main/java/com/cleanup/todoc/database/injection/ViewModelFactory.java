package com.cleanup.todoc.database.injection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.database.TodocMasterDatabase;
import com.cleanup.todoc.database.repositories.ProjectDataRepository;
import com.cleanup.todoc.database.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance(Context context) {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }


    private ViewModelFactory(Context context) {
        TodocMasterDatabase database = TodocMasterDatabase.getInstance(context);
        this.taskDataSource = new TaskDataRepository(database.taskDao());
        this.projectDataSource = new ProjectDataRepository(database.projectDao());
        this.executor = Executors.newSingleThreadExecutor();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(projectDataSource, taskDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
