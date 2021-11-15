package common.dao.impl;

import common.dao.TaskDao;
import common.model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskDaoImpl implements TaskDao {

	private static final Map<Long, Task> tasks = new HashMap<>();

	@Override
	public void create(Task task) {
		tasks.put(1412L, task);
	}

	@Override
	public Task getTask(Long id) {
		return tasks.get(id);
	}

	@Override
	public List<Task> getAll() {
		return null;
	}

	@Override
	public Task update(Task task) {
		return null;
	}

	@Override
	public void delete(Task task) {

	}
}
