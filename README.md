# Трекер задач

**Данная программа может**

Программа хранит в себе задачи/подзадачи/эпики.
Каждая задача имеет:
1. Название;
2. Краткое описание;
3. Уникальный идентификационный номер;
4. Статус (New, In_progress, Done);

Программа имеет следующие функции:
1. Создать задачу/подзадачу/эпик;
2. Обновить задачу/подзадачу/эпик;
3. Получить список всех задач/подзадач/эпиков;
4. Получить задачу/подзадачу/эпик по идентификатору;
5. Удалить все задачи/подзадачи/эпики;
6. Удалить задачу/подзадачу/эпик по идентификатору;
7. Получить список всех подзадач эпика;
8. Посмотреть историю просмотра задач;


Программа написана на Java. Пример кода:

```java
package manager;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getHistoryDefault() {
        return new InMemoryHistoryManager();
    }
}
....
//Получение задачи по индексу
@Override
public Task getTask(int id) {
    if (userTasks.containsKey(id)) {
        inMemoryHistoryManager.add(userTasks.get(id));
    }
        return userTasks.get(id);
}
```
