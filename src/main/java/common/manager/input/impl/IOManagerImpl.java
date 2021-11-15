package common.manager.input.impl;

import common.manager.input.IOManager;

import java.io.BufferedReader;
import java.io.IOException;

public class IOManagerImpl implements IOManager {

	private final BufferedReader reader;

	public IOManagerImpl(BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public void writeLn(String out) {
		System.out.println(out);
	}

	@Override
	public String readLn() throws IOException {
		return reader.readLine();
	}

}
