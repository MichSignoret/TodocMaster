package com.cleanup.todoc.database.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.database.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;


    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    /**
     *
     * @param projectID ID du projet dont on veut ramener les taches
     * @return liste de taches
     */
    public LiveData<List<Task>> getTask(long projectID){
        return this.taskDao.getTask(projectID);
    }

    public LiveData<List<Task>> getAllTask (){
        return this.taskDao.getAllTasks();
    }

    /**
     *
     * @param task tache à creer
     */
    public void createTask(Task task){
        taskDao.insertTask(task);
    }

    /**
     *
     * @param taskID ID de la tache a supprimer
     */
    public void deleteTask(long taskID) {
        taskDao.deleteTask(taskID);
    }

    /**
     *
     * @param task tache a mettre à jour
     */
    public void updateTask(Task task){
        taskDao.updateTask(task);
    }

}
