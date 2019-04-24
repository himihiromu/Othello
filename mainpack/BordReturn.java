package mainpack;

import ExceptionSummary.PutErrException;
import ExceptionSummary.ReturnErrException;

/**
 *
 * @author himihiromu
 *
 */
public class BordReturn {
	BordInfo bi = null;
	boolean[] returnJudgment = new boolean[9];
	DecideDirection DD = new DecideDirection();

	public BordReturn(BordInfo bi) {
		this.bi = bi;
	}

	/**
	 * 石を盤面に置くメソッド
	 * 座標を引数にして盤面に石を置き、同じ色で挟んだ石を裏返す。
	 *
	 * @param lateral
	 *            横の座標
	 * @param Vertical
	 *            縦の座標
	 * @throws PutErrException
	 * @throws ReturnErrException
	 */
	void returnStone(int lateral, int vertical, boolean firstFlag) throws PutErrException, ReturnErrException {
		for(int i = 0; i < 10; i++) {
			returnJudgment[i] = false;
		}

		int stone = bi.stoneColor(firstFlag);
		int ldir;
		int vdir;

		if(returnSerch(lateral, vertical, firstFlag, true)) {
			for(int direction = 1; direction < 10; direction++) {

				if(returnJudgment[direction - 1]) {
					ldir = DD.sideDirection(direction);
					vdir = DD.verticalDirection(direction);

					bi.stonePut(lateral, vertical, firstFlag);
					lateral += ldir;
					vertical += vdir;

					while(bi.stoneColor(firstFlag) != stone) {
						bi.stoneReturn(lateral, vertical);
						lateral += ldir;
						vertical += vdir;
						stone = bi.partOut(lateral, vertical);
					}
				}
			}
		}
	}

	/**
	 * 石を裏返せるかを確認するメソッド
	 * 座標を引数にしてその座標に石を置けるかを確認する。
	 *
	 * @param lateral
	 *            横の座標
	 * @param vertical
	 *            縦の座標
	 * @param firstFlag
	 *            先攻か後攻かを受け取る
	 * @param returnFlag
	 *            裏返し処理をするかしないか。
	 * @return 入れ替えられたかどうか
	 * @throws PutErrException
	 */
	boolean returnSerch(int lateral, int vertical, boolean firstFlag, boolean returnFlag) throws PutErrException {
		boolean canDoFlag = false;
		if(this.bi.partOut(lateral, vertical) == -1) {
			throw new PutErrException("ここは壁です");
		}
		int ldir, vdir;

		for(int direction = 1; direction < 10; direction++) {
			ldir = DD.sideDirection(direction);
			vdir = DD.verticalDirection(direction);

			if((ldir == 0) && (vdir == 0)) {
				continue;
			}
			if(returnSerchOneDirection(lateral, vertical, ldir, vdir, firstFlag)) {
				if(returnFlag) {
					returnJudgment[direction - 1] = true;
					canDoFlag = true;
				} else {
					return true;
				}
			}
		}
		return canDoFlag;
	}

	boolean returnSerch(int s, int vertical, boolean firstFlag) throws PutErrException {
		return returnSerch(s, vertical, firstFlag, false);
	}

	/**
	 * 指定した座標から指定した方向に石を裏返すことができるかを調べるメソッド
	 * 座標と方向を引数で指定し、その方向に石があればtrueを返す
	 *
	 * @param lateral
	 *            横の座標
	 * @param vertical
	 *            縦の座標
	 * @param ldir
	 *            横の方向
	 * @param vdir
	 *            縦の方向
	 * @return 裏返すことができればtrue、できなければfalse
	 */
	boolean returnSerchOneDirection(int lateral, int vertical, int ldir, int vdir, boolean firstFlag) {
		boolean endFlag = false;
		int stone = this.bi.partOut(lateral, vertical);
		int changeable = 0;

		if(stone != 0) {
			return false;
		}

		lateral += ldir;
		vertical += vdir;
		stone = this.bi.partOut(lateral, vertical);

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
					lateral += ldir;
					vertical += vdir;
					stone = this.bi.partOut(lateral, vertical);
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
					lateral += ldir;
					vertical += vdir;
					stone = this.bi.partOut(lateral, vertical);
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

class DecideDirection {
	int sideDirection(int direction) {
		int ldir;
		ldir = direction % 3;
		switch (ldir) {
		case 0:
			ldir = 1;
			break;
		case 1:
			ldir = -1;
			break;
		case 2:
			ldir = 0;
			break;
		}
		return ldir;
	}

	int verticalDirection(int direction) {
		int vdir;
		vdir = direction / 3;
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
		return vdir;

	}
}
