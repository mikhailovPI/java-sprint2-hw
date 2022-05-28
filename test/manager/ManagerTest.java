package manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.StatusTask;
import task.Subtask;
import task.Task;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

abstract class ManagerTest<T extends TaskManager> {

    protected T taskManager;
    Task taskTestOne;
    Task taskTestTwo;
    Epic epicTestOne;
    Epic epicTestTwo;
    Subtask subtaskTestOne;
    Subtask subtaskTestTwo;
    Subtask subtaskTestThree;
    Subtask subtaskTestFour;

    @BeforeEach
    void creatTaskForTest() {
        taskTestOne = new Task("task 1", "Описание Task 1", 0, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));
        taskTestTwo = new Task("task 2", "Описание Task 2", 0, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));

        taskManager.addTask(taskTestOne);
        taskManager.addTask(taskTestTwo);

        epicTestOne = new Epic("Epic 1", "Описание Epic 1", 0, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));
        epicTestTwo = new Epic("Epic 2", "Описание Epic 2", 0, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));


        taskManager.addEpic(epicTestOne);
        taskManager.addEpic(epicTestTwo);

        subtaskTestOne = new Subtask("Subtask 1", "Описание Subtask 1", 0,
                StatusTask.IN_PROGRESS, 40,
                LocalDateTime.of(2022, Month.MAY, 26, 11, 0), epicTestOne.getId());

        subtaskTestTwo = new Subtask("Subtask 2", "Описание Subtask 2", 0,
                StatusTask.NEW, 40,
                LocalDateTime.of(2022, Month.MAY, 26, 11, 0), epicTestTwo.getId());

        subtaskTestThree = new Subtask("Subtask 3", "Описание Subtask 3", 0,
                StatusTask.IN_PROGRESS, 40,
                LocalDateTime.of(2022, Month.MAY, 23, 11, 0), epicTestTwo.getId());

        subtaskTestFour = new Subtask("Subtask 4", "Описание Subtask 4", 0,
                StatusTask.IN_PROGRESS, 40,
                LocalDateTime.of(2022, Month.MAY, 23, 11, 0), epicTestOne.getId());

        taskManager.addSubtask(subtaskTestOne);
        taskManager.addSubtask(subtaskTestTwo);
        taskManager.addSubtask(subtaskTestThree);
        taskManager.addSubtask(subtaskTestFour);
    }

    @Test
    void addTask() {
        assertNotNull(taskManager.listTask());
        assertEquals(2, taskManager.listTask().size(), "Создано две задачи");
    }

    @Test
    void updateTaskTest() {
        Task taskTestUpdate = new Task("task 5", "Описание Task 5", 1, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));
        taskManager.updateTask(taskTestUpdate);

        assertEquals(new Task("task 5", "Описание Task 5", 1, StatusTask.NEW, 40,
                        LocalDateTime.of(2022, Month.MAY, 26, 11, 0)),
                taskManager.listTask().get(0), "Задача не обновилась");
    }

    @Test
    void listTaskTest() {
        assertEquals(2, taskManager.listTask().size(), "Размер списка задач не совпадает");
    }

    @Test
    void getTaskTest() {
        assertNotNull(taskManager.getTask(1));
        assertEquals(taskTestOne, taskManager.getTask(1), "Не удалось получить задачу по данному индексу");
    }

    @Test
    void deleteTasksTest() {
        taskManager.deleteTasks();
        assertEquals(0, taskManager.listTask().size(), "Задачи не удалились");
    }

    @Test
    void removeTaskTest() {
        taskManager.removeTask(1);
        assertEquals(1, taskManager.listTask().size(), "Не удалось удалить задачу с таким индексом");
    }

    @Test
    void addSubtaskTest() {
        assertNotNull(taskManager.listSubtask());
        assertEquals(4, taskManager.listSubtask().size(), "Создано четыре подзадачи");
    }

    @Test
    void updateSubtaskTest() {
        Subtask subtaskTestUpdate = new Subtask("Subtask 10 ", "Описание Subtask 10", 5,
                StatusTask.IN_PROGRESS, 40,
                LocalDateTime.of(2022, Month.MAY, 26, 11, 0), 3);
        taskManager.updateSubtask(subtaskTestUpdate);

        assertEquals(new Subtask("Subtask 10 ", "Описание Subtask 10", 5,
                        StatusTask.IN_PROGRESS, 40,
                        LocalDateTime.of(2022, Month.MAY, 26, 11, 0), 3),
                taskManager.listSubtask().get(0), "Подадача не обновилась");
    }

    @Test
    void listSubtaskTest() {
        assertEquals(4, taskManager.listSubtask().size(), "Размер списка подзадач не совпадает");
    }

    @Test
    void getSubtaskTest() {
        assertNotNull(taskManager.listSubtask());
        assertEquals(subtaskTestOne, taskManager.getSubtask(5), "Не удалось получить подзадачу по данному индексу");
    }

    @Test
    void deleteSubtasksTest() {
        taskManager.deleteSubtasks();
        assertEquals(0, taskManager.listSubtask().size(), "Подзадачи не удалились");
    }

    @Test
    void removeSubtaskTest() {
        taskManager.removeSubtask(8);
        assertEquals(3, taskManager.listSubtask().size(), "Не удалось удалить подзадачу с таким индексом");
    }

    @Test
    void addEpic() {
        assertNotNull(taskManager.listEpic());
        assertEquals(2, taskManager.listEpic().size(), "Создано два эпика");
    }

    @Test
    void updateEpicTest() {
        Epic epicTestUpdate = new Epic("Epic 132", "Описание Epic 132", 4, StatusTask.IN_PROGRESS,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));
        taskManager.updateEpic(epicTestUpdate);

        assertEquals(new Epic("Epic 132", "Описание Epic 132", 4, StatusTask.IN_PROGRESS,
                        40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0)),
                taskManager.listEpic().get(1), "Эпик не обновилсь");
    }

    @Test
    void listEpicTest() {
        assertEquals(2, taskManager.listEpic().size(), "Размер списка эпика не совпадает");

    }

    @Test
    void getEpicTest() {
        assertNotNull(taskManager.listEpic());
        assertEquals(epicTestOne, taskManager.getEpic(3), "Не удалось получить эпик по данному индексу");
    }

    @Test
    void deleteEpicsTest() {
        taskManager.deleteEpics();
        assertEquals(0, taskManager.listEpic().size(), "Эпики не удалились");
    }

    @Test
    void removeEpicTest() {
        taskManager.removeEpic(3);
        assertEquals(1, taskManager.listEpic().size(), "Не удалось удалить эпик с таким индексом");
    }

    @Test
    void getSubtaskByEpicTest() {
        assertFalse(taskManager.getSubtaskByEpic(epicTestOne).isEmpty(), "Список сабтасков пустой");
        assertEquals(2, taskManager.getSubtaskByEpic(epicTestOne).size(), "Размер списка сабтасков не совпадает");
    }

    @Test
    void statusEpicIfEmptyListOfSubtasks() {
        taskManager.deleteSubtasks();
        taskManager.getSubtaskByEpic(epicTestOne);

        assertEquals(StatusTask.NEW, epicTestOne.getStatus(), "Ожидался статус NEW");
    }

    @Test
    void statusEpicIfStatusSubtasksNew() {
        Epic epicTest = new Epic("Epic", "Описание Epic", 0, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));
        taskManager.addEpic(epicTest);
        Subtask subtask1 = new Subtask("Subtask", "Описание Subtask", 0,
                StatusTask.NEW, 40, LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                epicTest.getId());
        Subtask subtask2 = new Subtask("Subtask0", "Описание Subtask0", 0,
                StatusTask.NEW, 40, LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                epicTest.getId());
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.getSubtaskByEpic(epicTest);

        assertEquals(StatusTask.NEW, epicTest.getStatus(), "Ожидался статус NEW");
    }

    @Test
    void statusEpicIfStatusSubtasksDone() {
        Epic epicTest = new Epic("Epic", "Описание Epic", 0, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));
        taskManager.addEpic(epicTest);
        Subtask subtask1 = new Subtask("Subtask", "Описание Subtask", 0,
                StatusTask.DONE, 40, LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                epicTest.getId());
        Subtask subtask2 = new Subtask("Subtask0", "Описание Subtask0", 0,
                StatusTask.DONE, 40, LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                epicTest.getId());
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.getSubtaskByEpic(epicTest);

        assertEquals(StatusTask.DONE, epicTest.getStatus(), "Ожидался статус DONE");
    }

    @Test
    void statusEpicIfStatusSubtasksInProgress() {
        Epic epicTest = new Epic("Epic", "Описание Epic", 0, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));
        taskManager.addEpic(epicTest);
        Subtask subtask1 = new Subtask("Subtask", "Описание Subtask", 0,
                StatusTask.IN_PROGRESS, 40, LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                epicTest.getId());
        Subtask subtask2 = new Subtask("Subtask0", "Описание Subtask0", 0,
                StatusTask.IN_PROGRESS, 40, LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                epicTest.getId());
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.getSubtaskByEpic(epicTest);

        assertEquals(StatusTask.IN_PROGRESS, epicTest.getStatus(), "Ожидался статус IN_PROGRESS");
    }

    @Test
    void statusEpicIfStatusSubtasksNewAndDone() {
        Epic epicTest = new Epic("Epic", "Описание Epic", 0, StatusTask.NEW,
                40, LocalDateTime.of(2022, Month.MAY, 26, 11, 0));
        taskManager.addEpic(epicTest);
        Subtask subtask1 = new Subtask("Subtask", "Описание Subtask", 0,
                StatusTask.NEW, 40, LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                epicTest.getId());
        Subtask subtask2 = new Subtask("Subtask0", "Описание Subtask0", 0,
                StatusTask.DONE, 40, LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                epicTest.getId());
        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.getSubtaskByEpic(epicTest);

        assertEquals(StatusTask.IN_PROGRESS, epicTest.getStatus(), "Ожидался статус IN_PROGRESS");
    }

    @Test
    void checkingForAnEmptyTaskList() {
        taskManager.deleteTasks();
        assertTrue(taskManager.listTask().isEmpty(), "Список не пустой");
    }

    @Test
    void checkingForAnEmptySubtaskList() {
        taskManager.deleteSubtasks();
        assertTrue(taskManager.listSubtask().isEmpty(), "Список не пустой");
    }

    @Test
    void checkingForAnEmptyEpicList() {
        taskManager.deleteEpics();
        assertTrue(taskManager.listEpic().isEmpty(), "Список не пустой");
    }

    @Test
    void getEndTimeTaskTest() {
        assertEquals(LocalDateTime.of(2022, Month.MAY, 26, 11, 0).plusMinutes(40),
                taskManager.getEndTime(taskTestOne),
                "Время окончания задачи должно совпадать. Проверь startTime и duration");
    }

    @Test
    void getEndTimeEpicTest() {
        assertEquals(LocalDateTime.of(2022, Month.MAY, 23, 11, 0).plusMinutes(4320),
                taskManager.getEndTime(epicTestOne),
                "Время окончания задачи должно совпадать. Проверь startTime и duration");
    }

    @Test
    void startTimeEpicsTest () {
        assertEquals(LocalDateTime.of(2022, Month.MAY, 23, 11, 0),
                taskManager.startTimeEpics(epicTestOne),
                "Время начала эпика должно совпадать. Проверь startTime");
    }

    @Test
    void durationEpicsTest () {
        assertEquals(4320L, taskManager.durationEpics(epicTestOne),
                "Продолжительность эпика должно совпадать. Проверь duration");

    }
}