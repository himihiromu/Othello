package mainpack;

import ExceptionSummary.PutErrException;
import ExceptionSummary.ReturnErrException;

/**
 * オセロの盤面情報を保有したクラス
 *
 * @author himihiromu
 *
 */
class BordInfo {

	private final byte LIMIT = 10;
	private int[][] bord = new int[LIMIT][LIMIT];
	private boolean[][] bordflag = new boolean[LIMIT][LIMIT];
	private BordConversion bc = new BordConversion();

	/**
	 * コンストラクタ
	 * オセロの盤面に壁を設定し、初期化するコンストラクタ
	 */
	BordInfo() {
		for(int v = 0; v < 10; v++) {
			int s = 0;
			if((v == 0) || (v == 9)) {
				while(true) {
					if(s > 10) {
						break;
					}
					bord[v][s] = -1;
					s++;
				}
			} else {
				bord[v][s] = -1;
				s++;
				while(s < 10) {
					bord[v][s] = 0;
					s++;
				}
				bord[v][s] = -1;
			}
		}
	}

	/**
	 * オセロの盤面の全体を表示するメソッド
	 *
	 */
	public void output() {
		for(int v = 0; v < 10; v++) {
			for(int s = 0; s < 10; s++) {
				System.out.print(bc.intToStone(bord[v][s]));
			}
			System.out.println();
		}

	}

	/**
	 * 指定した座標の情報を返すメソッド
	 * 引数で指定した座標の位置の数字を返す
	 *
	 * @param s
	 *            横の座標
	 * @param v
	 *            縦の座標
	 * @return -1~3の座標
	 */
	public int partOut(int s, int v) {
		return bord[v][s];
	}

	/**
	 * 指定した座標の石を裏返すメソッド
	 * 引数で指定した座標の位置の石を反転させる
	 *
	 * @param s
	 *            横の座標
	 * @param v
	 *            縦の座標
	 * @throws ReturnErrException
	 *             石が無いときに投げられる例外
	 */
	public void stoneReturn(int s, int v) throws ReturnErrException {
		int work = bord[v][s];
		switch (work) {
		case 1:
			bord[v][s] = 2;
		case 2:
			bord[v][s] = 1;
		default:
			throw new ReturnErrException("裏返す石が無いです");
		}
	}

	/**
	 * 指定した座標に石を置くメソッド
	 *
	 * @param s
	 *            横の座標
	 * @param v
	 *            縦の座標
	 * @param turn
	 *            先攻か後攻かのフラグ
	 * @throws PutErrException
	 */
	public void stonePut(int s, int v, boolean turn) throws PutErrException {
		if(!(bord[v][s] == 0)) {
			throw new PutErrException("もう既にこの場所には石が置かれています");
		}
		if(turn) {

		} else {

		}
	}
}

/**
 * 数字によって盤面の記号を呼び分けるクラス
 *
 * @author himihiromu
 *
 */
class BordConversion {
	char intToStone(int num) {
		switch (num) {
		case -1:
			return '■';
		case 0:
			return '□';
		case 1:
			return '○';
		case 2:
			return '●';
		case 3:
			return '☆';
		}
		return 0;
	}
}