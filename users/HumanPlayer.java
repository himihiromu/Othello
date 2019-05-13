package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import characterLibrary.PatternMatch;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, boolean firstFlag) {
		super(name, firstFlag);
	}

	@Override
	public String input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PatternMatch pm = new PatternMatch();
		String pattern = "[a-h][1-8]";
		String inputStr = null;

		while(true) {
			try {
				inputStr = br.readLine();
			} catch (IOException e) {
				System.out.println("もう一度入力してください。");
				continue;
			}
			if(pm.PatternMatcher(pattern, inputStr)) {
				break;
			} else {
				System.out.println("もう一度入力してください。");
			}
		}
		return inputStr;

	}

	@Override
	public String nameOutput() {
		return this.playerName;
	}

	@Override
	public boolean firstFlagOutput() {
		return firstFlag;
	}

}
