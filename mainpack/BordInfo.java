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
		/* 端に-1を設定するループ処理 */
		for(int vertical = 0; vertical < 10; vertical++) {

			int lateral = 0;

			/* 行の両端の添え字になっていれば-1を設定 */
			if((vertical == 0) || (vertical == 9)) {

				while(lateral < 10) {

					bord[vertical][lateral] = -1;
					lateral++;
				}
				/* 行の両端ではないので壁と空白を設定 */
			} else {

				/* 壁の-1を設定 */
				bord[vertical][lateral] = -1;
				lateral++;

				/* 壁ではないので空白を設定 */
				while(lateral < 10) {

					bord[vertical][lateral] = 0;
					lateral++;

				}
				/* 壁の-1を設定 */
				bord[vertical][lateral] = -1;
			}
		}
	}

	/**
	 * フラグを引数にその手番の石の色を返すメソッド
	 *
	 * @param firstFlag
	 *            先攻か後攻かのフラグ
	 * @return 石の色
	 */
	int stoneColor(boolean firstFlag) {
		/* 先攻は黒、後攻は白 */
		if(firstFlag) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * オセロの盤面の全体を表示するメソッド
	 *
	 */
	public void output() {
		/* 縦のループ */
		for(int vertical = 0; vertical < 10; vertical++) {
			/* 横のループ */
			for(int lateral = 0; lateral < 10; lateral++) {
				/* 盤面の出力 */
				System.out.print(bc.intToStone(bord[vertical][lateral]));
			}
			/* 改行 */
			System.out.println();
		}

	}

	/**
	 * 指定した座標の情報を返すメソッド
	 * 引数で指定した座標の位置の数字を返す
	 *
	 * @param lateral
	 *            横の座標
	 * @param vertical
	 *            縦の座標
	 * @return -1~3の座標
	 */
	public int partOut(int lateral, int vertical) {
		/* 指定された座標の状態を返り値に設定 */
		return bord[vertical][lateral];
	}

	/**
	 * 指定した座標の石を裏返すメソッド
	 * 引数で指定した座標の位置の石を反転させる
	 *
	 * @param lateral
	 *            横の座標
	 * @param vertical
	 *            縦の座標
	 * @throws ReturnErrException
	 *             石が無いときに投げられる例外
	 */
	public void stoneReturn(int lateral, int v) throws ReturnErrException {

		int work = bord[v][lateral];		//見やすいようにいったん退避
		/* 格納された数字によって処理を変える */
		switch (work) {

		/* 白の場合 */
		case 1:
			bord[v][lateral] = 2;			//黒を代入

			/* 黒の場合 */
		case 2:
			bord[v][lateral] = 1;			//白を代入

		default:
			/* 壁、空白、範囲外の座標などの場合 */
			throw new ReturnErrException("裏返す石が無いです");
		}
	}

	/**
	 * 指定した座標に石を置くメソッド
	 *
	 * @param lateral
	 *            横の座標
	 * @param vertical
	 *            縦の座標
	 * @param firstFlag
	 *            先攻か後攻かのフラグ
	 * @throws PutErrException
	 */
	public void stonePut(int lateral, int vertical, boolean firstFlag) throws PutErrException {
		/**/
		if(!(bord[vertical][lateral] == 0)) {
			throw new PutErrException("もう既にこの場所には石が置かれています");
		}
		if(firstFlag) {
			bord[vertical][lateral] = 2;
		} else {
			bord[vertical][lateral] = 1;
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