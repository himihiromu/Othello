package mainpack;

import ExceptionSummary.PutErrException;

/**
 *
 * @author himihiromu
 *
 */
public class BordReturn {
	BordInfo bi = null;

	public BordReturn(BordInfo bi) {
		this.bi = bi;
	}

	/**
	 * 石を盤面に置くメソッド
	 * 座標を引数にして盤面に石を置き、同じ色で挟んだ石を裏返す。
	 *
	 * @param s
	 *            横の座標
	 * @param v
	 *            縦の座標
	 */
	void returnStone(int s, int v) {

	}

	/**
	 * 石を裏返せるかを確認するメソッド
	 * 座標を引数にしてその座標に石を置けるかを確認する。
	 *
	 * @param s
	 *            横の座標
	 * @param v
	 *            縦の座標
	 * @return 入れ替えられたかどうか
	 * @throws PutErrException
	 */
	boolean returnSerch(int s, int v) throws PutErrException {

	}

	/**
	 * 指定した座標から指定した方向に石を裏返すことができるかを調べるメソッド
	 * 座標と方向を引数で指定し、その方向に石があればtrueを返す
	 * 
	 * @param s
	 *            横の座標
	 * @param v
	 *            縦の座標
	 * @param sdir
	 *            横の方向
	 * @param vdir
	 *            縦の方向
	 * @return 裏返すことができればtrue、できなければfalse
	 */
	boolean returnSerchOneDirection(int s, int v, int sdir, int vdir) {
		boolean changeFlag = false;
		while(bi.partOut(s, v) == -1) {

		}
	}
}
