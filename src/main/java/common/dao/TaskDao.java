package common.dao;

import common.model.Task;

import java.util.List;

public interface TaskDao {
	void create(Task task);
	Task getTask(Long id);
	List<Task> getAll();
	Task update(Task task);
	void delete(Task task);
}
