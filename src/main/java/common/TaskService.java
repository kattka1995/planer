package common;

import java.util.ArrayList;
import java.util.List;

import static common.TaskStatuses.DONE;
import static common.TaskStatuses.NOT_DONE;


public class TaskService {
    Integer id = 0;
    String commandName = "";
    private Input input = new Input();
    private List<Task> tasks = new ArrayList<>();

    public TaskService() {
        do {
            System.out.println("Введите название команды : add <описание задачи>; toggle <идентификатор задачи>; print; print[all]; quit.");
            commandName = input.enterCommand();
            readCommand(commandName);
        }
        while (!commandName.equals("quit"));
    }


    public void readCommand(String command) {
        String[] parse = command.split(" ");
        String commandName = parse[0].toLowerCase();
        switch (commandName) {
            case "add": {
                this.add(command.replaceFirst("^\\S*", "").trim());
                break;
            }
            case "toggle": {
                this.toggle(command.replaceFirst("^\\S*", "").trim());
                break;
            }
            case "print": {
                this.print();
                break;
            }
            case "print[all]": {
                this.printAll();
                break;
            }
        }
    }

    public void add(String description) {
        if (description.isEmpty())
            System.out.println("Ошибка: описание команды не может быть пустым");
        else {
            Task task = new Task(description);
            tasks.add(task);
            task.setId(++id);
            System.out.println("Задача успешно добавлена");
        }
    }

    public void toggle(String id) {
        try {
            Integer taskId = Integer.valueOf(id);
            if (taskId > tasks.size() || taskId <= 0)
                System.out.println("Ошибка: веденного id не существует");
            else if (tasks.get(taskId - 1).getStatus() == DONE) {
                tasks.get(taskId - 1).setStatus(NOT_DONE);
                System.out.println("Состояние задачи успешно изменено");
            } else {
                tasks.get(taskId - 1).setStatus(DONE);
                System.out.println("Состояние задачи успешно изменено");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка: id должен быть числом");
        }
    }

    public void print() {
        Long uncheckTasksCount = tasks.stream().filter(s -> s.getStatus().equals(NOT_DONE)).count();
        if (uncheckTasksCount == 0) {
            System.out.println("Список невыполненных задач пуст");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getStatus() == NOT_DONE)
                    System.out.println(String.format("%s. [%s] %s", tasks.get(i).getId(), tasks.get(i).getStatus(), tasks.get(i).getDescription()));
            }
        }
    }

    public void printAll() {
        if (tasks.size() == 0) {
            System.out.println("Список задач пуст");
        } else
            for (int i = 0; i < tasks.size(); i++)
                System.out.println(String.format("%s. [%s] %s", tasks.get(i).getId(), tasks.get(i).getStatus(), tasks.get(i).getDescription()));
    }
}




