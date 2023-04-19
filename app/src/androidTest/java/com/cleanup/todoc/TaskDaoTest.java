package com.cleanup.todoc;

import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocMasterDatabase;
import com.cleanup.todoc.database.model.Project;
import com.cleanup.todoc.database.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.Timestamp;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest
{

    @RunWith(AndroidJUnit4.class)

    public class ItemDaoTest {

        // FOR DATA
        private TodocMasterDatabase database;
        // DATA SET FOR TEST
        private static final long PROJECT_ID = 1;
        private final long tsLong = System.currentTimeMillis()/1000;
        private final Project PROJECT_DEMO = new Project(PROJECT_ID, "Projet Tartampion", 0xFFEADAD1);
        private final Task NEW_TASK_TO_DO = new Task(1, PROJECT_ID, "Premiere tache a faire", tsLong);

        @Rule

        public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

        @Before

        public void initDb() throws Exception {

            this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),

                            TodocMasterDatabase.class)

                    .allowMainThreadQueries()

                    .build();

        }

        @After

        public void closeDb() throws Exception {

            database.close();

        }

        @Test
        public void insertAndGetUser() throws InterruptedException {
            // BEFORE : Adding a new user
            this.database.projectDao().createUser(PROJECT_DEMO);
            // TEST
            Project project = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
            assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
        }


    }
}
