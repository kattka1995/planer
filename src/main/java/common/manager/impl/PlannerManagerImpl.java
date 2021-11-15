package common.manager.impl;

import common.manager.input.IOManager;
import common.manager.CommandManager;
import common.manager.CommandResolver;
import common.manager.PlannerManager;
import common.manager.command.Command;

import java.io.IOException;

import static common.manager.command.Command.QUIT;
import static common.manager.command.Command.UNEXPECTED_COMMAND;

public class PlannerManagerImpl implements PlannerManager {

	private final CommandManager manager;
	private final CommandResolver resolver;
	private final IOManager ioManager;

	public PlannerManagerImpl(CommandManager manager, CommandResolver resolver, IOManager ioManager) {
		this.manager = manager;
		this.resolver = resolver;
		this.ioManager = ioManager;
	}

	@Override
	public void run() {
		Command command;
		do {
			ioManager.writeLn("Введите название команды : add <описание задачи>; toggle <идентификатор задачи>; " +
					"delete <идентификатор задачи>; edit <идентификатор задачи> <новое значение>; search <substring>; print; print all; quit.");
			final String input;
			try {
				input = ioManager.readLn();
			} catch (IOException e) {
				throw new RuntimeException();
			}
			command = resolver.resolve(input);
			if (command == UNEXPECTED_COMMAND) {
				ioManager.writeLn("ERROR");
				continue;
			}
			execute(command, input);
		}
		while (QUIT != command);

	}

	void execute(Command command, String input) {
		switch (command) {
			case ADD:
				manager.add(input);
				break;
			case PRINT:
				manager.print();
		}
	}
}
