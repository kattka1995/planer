package common.manager;

public interface CommandManager {

	void add(String description);
	void editTask(String id, String newDescription);
	void toggle(String id);
	void search(String desc);
	void delete(String id);
	void print();
	void printAll();

}
