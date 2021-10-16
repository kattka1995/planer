package common;

import java.util.ArrayList;
import java.util.List;

import static common.TaskStatusesInterface.DONE;
import static common.TaskStatusesInterface.NOT_DONE;


public class TaskService implements CommandInterface {
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
        while (!commandName.equalsIgnoreCase(QUIT));
    }

    public void readCommand(String command) {
        String[] parse = command.split(" ");
        String commandName = parse[0].toLowerCase();


        switch (commandName) {
            case ADD: {
                this.add(command.replaceFirst("^\\S*", "").trim());
                break;
            }
            case TOGGLE: {
                this.toggle(command.replaceFirst("^\\S*", "").trim());
                break;
            }
            case PRINT: {
                try {
                    if ((parse[1].equalsIgnoreCase("all"))) {
                        this.printAll();
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.print();
                    break;
                }
            }
            default:
                System.out.println("Данной команды не существует");
                break;
        }
    }

    public void add(String description) {
        if (description.isEmpty())
            System.out.println("Ошибка: описание команды не может быть пустым");
        else if (tasks.isEmpty()) {
            if (description.contains("\\n")) {
                if (description.startsWith("\\n")) {
                    System.out.println("Ошибка добавления задачи.Символ переноса в начале строки.");
                } else {
                    String[] str = description.split("\\\\n");
                    String desc = str[0];

                    Task task = new Task(desc);
                    tasks.add(task);
                    task.setId(1);
                    System.out.println("Задача успешно добавлена");
                }
            } else {
                Task task = new Task(description);
                tasks.add(task);
                task.setId(1);
                System.out.println("Задача успешно добавлена");
            }
        } else if (description.contains("\\n")) {
            if (description.startsWith("\\n")) {
                System.out.println("Ошибка добавления задачи.Символ переноса в начале строки.");
            } else {
                String[] str = description.split("\\\\n");
                String desc = str[0];
                tasks.get(0).setId(1);
                tasks.get(0).setDescription(desc);
                System.out.println("Задача успешно добавлена");
            }
        } else {
            tasks.get(0).setId(1);
            tasks.get(0).setDescription(description);
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
                    System.out.println(String.format("%s. [%s] %s", tasks.get(i).getId(), tasks.get(i).getStatus(),
                            tasks.get(i).getDescription()));
            }
        }
    }

    public void printAll() {
        if (tasks.size() == 0) {
            System.out.println("Список задач пуст");
        } else
            for (int i = 0; i < tasks.size(); i++)
                System.out.println(String.format("%s. [%s] %s", tasks.get(i).getId(), tasks.get(i).getStatus(),
                        tasks.get(i).getDescription()));
    }
}




