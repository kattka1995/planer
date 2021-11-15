package common.manager.impl;

import common.dao.TaskDao;
import common.manager.input.IOManager;
import common.manager.CommandManager;
import common.model.Task;

public class CommandManagerImpl implements CommandManager {

	private final IOManager manager;
	private final TaskDao taskDao;

	public CommandManagerImpl(IOManager manager, TaskDao taskDao) {
		this.manager = manager;
		this.taskDao = taskDao;
	}

	@Override
	public void add(String description) {
		//some logic
		taskDao.create(new Task(description));
	}

	@Override
	public void editTask(String id, String newDescription) {

	}

	@Override
	public void toggle(String id) {

	}

	@Override
	public void search(String desc) {

	}

	@Override
	public void delete(String id) {

	}

	@Override
	public void print() {

	}

	@Override
	public void printAll() {

	}
}
