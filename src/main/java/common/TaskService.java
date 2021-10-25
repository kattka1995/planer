package common;

import java.util.ArrayList;
import java.util.List;
import static common.TaskStatusesInterface.DONE;
import static common.TaskStatusesInterface.NOT_DONE;


public class TaskService implements CommandInterface {
    public static final String ERROR = "Error";
    String commandName = "";
    private Input input = new Input();
    private List<Task> tasks = new ArrayList<>();
    private int id = 0;

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
                break;
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
            case SEARCH: {
                if (parse.length == 1) {
                    System.out.println(ERROR);
                    break;
                } else {
                    this.search(command.replaceFirst("^\\S*", "").trim());
                    break;
                }
            }
            case EDIT: {
                if (parse.length >= 3) {
                    this.editTask(parse[1], command.replaceFirst("^\\S* \\d+ ", "").trim());
                    break;
                } else
                    System.out.println(ERROR);
                break;
            }

            case DELETE: {
                if (parse.length == 2) {
                    this.delete(command.replaceFirst("^\\S*", "").trim());
                    break;
                } else
                    System.out.println(ERROR);
                break;
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
        tasks.add(task);
        task.setId(++id);

    }

    private void editTask(String id, String newDescription) {
        try {
            Integer taskId = Integer.valueOf(id);
            Integer taskListId = taskId - 1;
            if (taskId > tasks.size() || taskId <= 0)
                System.out.println(ERROR);
            else if (newDescription.isEmpty() || (newDescription.startsWith("\\n"))) {
                System.out.println(ERROR);
            } else {
                if (newDescription.contains("\\n")) {
                    String[] str = newDescription.split("\\\\n");
                    newDescription = str[0];
                }
                tasks.get(taskListId).setDescription(newDescription);

            }
        } catch (NumberFormatException ex) {
            System.out.println(ERROR);
        }
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

    public void search(String desc) {
        tasks.stream().filter(s -> s.getDescription().contains(desc.toLowerCase()))
                .forEach(k -> System.out.println(String.format("%s. [%s] %s", k.getId(), k.getStatus(),
                        k.getDescription())));
    }

    public void delete(String id) {
        try {

            Integer taskId = Integer.valueOf(id);
            Integer taskListId = taskId - 1;
            if (taskId > tasks.size() || taskId <= 0)
                System.out.println(ERROR);
            else {
                tasks.remove(tasks.get(taskListId));
                this.id--;
                for (Task task : tasks) {
                    if (task.getId() >= taskId) {
                        task.setId(taskId++);
                    }
                }
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
            tasks.stream().filter(s -> s.getStatus().equals(NOT_DONE)).forEach(k -> System.out.println((String.format("%s. [%s] %s", k.getId(), k.getStatus(),
                    k.getDescription()))));
        }
    }


    public void printAll() {
        if (tasks.size() == 0) {
            System.out.println("Список задач пуст");
        } else
            tasks.stream().map(task -> String.format("%s. [%s] %s", task.getId(), task.getStatus(),
                    task.getDescription())).forEach(System.out::println);
    }
}




