package common.manager;

import common.manager.command.Command;

public interface CommandResolver {
	Command resolve(String command);
}
