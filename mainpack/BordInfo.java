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

	/**
	 * コンストラクタ
	 * オセロの盤面に壁を設定し、初期化するコンストラクタ
	 */
	BordInfo() {

		final int CENTER1 = 4;
		final int CENTER2 = 5;

		/* 端に壁を設定するループ処理 */
		for(int vertical = 0; vertical < 10; vertical++) {

			int lateral = 0;

			/* 行の両端の添え字になっていれば壁を設定 */
			if((vertical == 0) || (vertical == 9)) {

				while(lateral < 10) {

					this.bord[vertical][lateral] = -1;
					lateral++;
				}
				/* 行の両端ではないので両サイドに壁、それ以外には空白を設定 */
			} else {

				/* 壁を設定 */
				this.bord[vertical][lateral] = -1;
				lateral++;

				/* 空白を設定 */
				while(lateral < 9) {

					this.bord[vertical][lateral] = 0;
					lateral++;

				}
				/* 壁を設定 */
				this.bord[vertical][lateral] = -1;
			}
		}

		this.bord[CENTER1][CENTER2] = 2;
		this.bord[CENTER2][CENTER1] = 2;
		this.bord[CENTER1][CENTER1] = 1;
		this.bord[CENTER2][CENTER2] = 1;
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
	public void stoneReturn(int lateral, int vertical) throws ReturnErrException {

		int work = bord[vertical][lateral];		//見やすいようにいったん退避

		/* 格納された数字によって処理を変える */
		switch (work) {

		/* 白の場合 */
		case 1:
			bord[vertical][lateral] = 2;			//黒を代入

			/* 黒の場合 */
		case 2:
			bord[vertical][lateral] = 1;			//白を代入

		default:
			/* 壁、空白、範囲外の座標などの場合 */
			throw new ReturnErrException("裏返す石が無いです");
		}
	}

	/**
	 * 指定した座標に石を置くメソッド
	 * 縦と横の座標を指定し、その場所に先攻なら黒を、後攻なら白を置く
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

		/* 空白かどうかの確認 */
		if((bord[vertical][lateral] == 0) || (bord[vertical][lateral] == 3)) {

			/* 先攻か後攻かの判定 */
			if(firstFlag) {

				/* 先攻なので黒を置く */
				bord[vertical][lateral] = 2;
			} else {

				/* 後攻なので白を置く */
				bord[vertical][lateral] = 1;
			}
		} else {
			/* 空白じゃなかった */
			throw new PutErrException("この場所には石を置くことはできません");
		}
	}
}