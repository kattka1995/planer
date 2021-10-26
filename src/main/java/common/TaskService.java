package common;

import java.util.HashMap;

import static common.TaskStatusesInterface.DONE;
import static common.TaskStatusesInterface.NOT_DONE;


public class TaskService implements CommandInterface {
    public static final String ERROR = "Error";
    private final Input input = new Input();
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    String commandName = "";
    private Integer taskId = 1;

    public void run() {
        do {
            System.out.println("Введите название команды : add <описание задачи>; toggle <идентификатор задачи>; " +
                    "delete <идентификатор задачи>; edit <идентификатор задачи> <новое значение>; search <substring>; print; print all; quit.");
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
                } else
                    System.out.println(ERROR);
                break;
            }
            case QUIT: {
                break;
            }
            case PRINT: {
                if (parse.length == 2 && parse[1].equalsIgnoreCase(ALL))
                    this.print(ALL);
                else if (parse.length == 1)
                    this.print();
                else System.out.println(ERROR);
                break;
            }
            case SEARCH: {
                if (parse.length == 1) {
                    System.out.println(ERROR);
                } else
                    this.search(command.replaceFirst("^\\S*", "").trim());
                break;

            }
            case EDIT: {
                if (parse.length >= 3) {
                    this.editTask(parse[1], command.replaceFirst("^\\S* \\d+ ", "").trim());
                } else
                    System.out.println(ERROR);
                break;
            }

            case DELETE: {
                if (parse.length == 2) {
                    this.delete(command.replaceFirst("^\\S*", "").trim());
                } else
                    System.out.println(ERROR);
                break;
            }
            default:
                System.out.println("Данной команды не существует");
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
        tasks.put(taskId++, task);
    }

    private void editTask(String id, String newDescription) {
        try {
            Integer taskId = Integer.valueOf(id);
            if (tasks.get(taskId) == null)
                System.out.println(ERROR);
            else if (newDescription.isEmpty()) {
                System.out.println(ERROR);
            } else {
                tasks.get(taskId).setDescription(newDescription);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ERROR);
        }
    }

    public void toggle(String id) {
        try {
            Integer taskId = Integer.valueOf(id);
            if (tasks.get(taskId) == null)
                System.out.println(ERROR);
            else if (tasks.get(taskId).getStatus() == DONE) {
                tasks.get(taskId).setStatus(NOT_DONE);
            } else {
                tasks.get(taskId).setStatus(DONE);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ERROR);
        }
    }

    public void search(String desc) {
        tasks.entrySet().stream().filter(s -> s.getValue().getDescription().toLowerCase().contains(desc.toLowerCase()))
                .forEach(k -> System.out.println(String.format("%s. [%s] %s", k.getKey(), k.getValue().getStatus(),
                        k.getValue().getDescription())));
    }

    public void delete(String id) {
        try {
            Integer taskId = Integer.valueOf(id);
            if (tasks.get(taskId) == null)
                System.out.println(ERROR);
            else {
                tasks.keySet().remove(taskId);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ERROR);
        }
    }

    public void print(String... all) {
        if (all.length == 1) {
            tasks.entrySet().stream().map(task -> String.format("%s. [%s] %s", task.getKey(), task.getValue().getStatus(),
                    task.getValue().getDescription())).forEach(System.out::println);
        } else if (all.length == 0) {
            tasks.entrySet().stream().filter(s -> s.getValue().getStatus().equals(NOT_DONE)).forEach(k ->
                    System.out.println((String.format("%s. [%s] %s", k.getKey(), k.getValue().getStatus(),
                            k.getValue().getDescription()))));
        }
    }

}




