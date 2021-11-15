package common.manager.input;

import java.io.IOException;

public interface IOManager {
	void writeLn(String out);

	String readLn() throws IOException;
}
