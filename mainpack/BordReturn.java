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
	boolean returnSerch(int s, int v, boolean firstFlag) throws PutErrException {
		if(this.bi.partOut(s, v) == -1) {
			throw new PutErrException("ここは壁です");
		}
		int sdir, vdir;

		for(int direction = 1; direction < 10; direction++) {
			sdir = direction % 3;
			vdir = direction / 3;
			switch (sdir) {
			case 0:
				sdir = 1;
				break;
			case 1:
				sdir = -1;
				break;
			case 2:
				sdir = 0;
				break;
			}
			switch (vdir) {
			case 0:
				vdir = -1;
				break;
			case 1:
				vdir = 0;
				break;
			case 2:
				vdir = 1;
				break;
			}
			if((sdir == 0) && (vdir == 0)) {
				continue;
			}
			if(returnSerchOneDirection(s, v, sdir, vdir, firstFlag)) {
				return true;
			}
		}
		return false;

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
	boolean returnSerchOneDirection(int s, int v, int sdir, int vdir, boolean firstFlag) {
		boolean endFlag = false;
		int stone = this.bi.partOut(s, v);
		int changeable = 0;

		if(stone != 0) {
			return false;
		}

		s += sdir;
		v += vdir;
		stone = this.bi.partOut(s, v);

		if(firstFlag) {
			while((stone != -1) && (endFlag == false)) {
				switch (stone) {
				case 0:
					changeable = 0;
					endFlag = true;
					break;
				case 2:
					endFlag = true;
					break;
				case 1:
					s += sdir;
					v += vdir;
					stone = this.bi.partOut(s, v);
					changeable++;
					break;
				}
			}
		} else {
			while((stone != -1) && (endFlag == false)) {
				switch (stone) {
				case 0:
					changeable = 0;
					endFlag = true;
					break;
				case 1:
					endFlag = true;
					break;
				case 2:
					s += sdir;
					v += vdir;
					stone = this.bi.partOut(s, v);
					changeable++;
					break;
				}
			}
		}
		if(changeable == 0) {
			return false;
		} else {
			return true;
		}
	}
}
