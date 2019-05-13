package users;

import java.io.IOException;

abstract class Player {
	protected String playerName;
	protected boolean firstFlag;

	public Player(String name, boolean firstFlag) {
		this.playerName = name;
		this.firstFlag = firstFlag;
	}

	abstract String input() throws IOException;

	abstract String nameOutput();

	abstract boolean firstFlagOutput();
}
