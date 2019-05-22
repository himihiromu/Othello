package mainpack;

public class OthelloMain {
	boolean firstFlag = false;

	public static void main(String[] args) {
		BordReturn br = BordReturn();
		

	}

	private static int numPeople(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PatternMatch pm = new PatternMatch();
		String pattern = "[0-2]";

		System.out.println(“プレイ人数を入力してください。”);
		String inputStr = br.readline();

		while(true){
			if(pm.PatternMatcher(pattern, inputStr)) {
				break;
			}
			System.out.println(“もう一度入力してください ”);
			inputStr = br.readline();
			
		}
	}ー

}
