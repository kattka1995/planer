package common;

import java.util.Arrays;
import java.util.List;

import static common.TaskStatusesInterface.DONE;
import static common.TaskStatusesInterface.NOT_DONE;


public class TaskService implements CommandInterface {
    public static final String ERROR = "Error";
    String commandName = "";
    private Input input = new Input();
    private List<Task> tasks = Arrays.asList(new Task());

    public TaskService() {
        do {
            System.out.println("Введите название команды : add <описание задачи>; toggle <идентификатор задачи>; print; print all; quit.");
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
                if (parse.length == 2) {
                    this.toggle(command.replaceFirst("^\\S*", "").trim());
                    break;
                } else
                    System.out.println(ERROR);
            }
            case QUIT: {
                break;
            }
            case PRINT: {
                if (parse.length == 2) {
                    if (parse[1].equalsIgnoreCase(ALL)) {
                        this.printAll();
                        break;
                    }
                } else if (parse.length == 1) {
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
        if ((description.isEmpty()) || (description.startsWith("\\n"))) {
            System.out.println(ERROR);
        } else {
            if (description.contains("\\n")) {
                String[] str = description.split("\\\\n");
                description = str[0];
            }
            addNewTask(description);
        }
    }

    private void addNewTask(String description) {
        Task task = new Task(description);
        tasks.set(0, task);
    }


    public void toggle(String id) {
        try {
            Integer taskId = Integer.valueOf(id);
            Integer taskListId = taskId - 1;
            if (taskId > tasks.size() || taskId <= 0)
                System.out.println(ERROR);
            else if (tasks.get(taskListId).getStatus() == DONE) {
                tasks.get(taskListId).setStatus(NOT_DONE);
            } else {
                tasks.get(taskListId).setStatus(DONE);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ERROR);
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




