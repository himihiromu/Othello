package users;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, boolean firstFlag) {
		super(name, firstFlag);
	}

	@Override
	public String input() {
		BufferedReader br = new BufferedReader(InputStreamReader(System.in));
		
		return null;

	}

	@Override
	String nameOutput() {
		return this.playername;
	}

	@Override
	boolean firstFlagOutput() {
		return firstFlag;
	}

}
