package mainpack;

import ExceptionSummary.NumErrException;
import ExceptionSummary.PutErrException;
import ExceptionSummary.ReturnErrException;

/**
 *
 * @author himihiromu
 *
 */
public class BordReturn {
	BordInfo bi = null;								//OthelloMainClassで扱うBordInfoインスタンスの参照値を格納するインスタンス変数
	boolean[] returnJudgment = new boolean[9];	//入れ替え可能な方向を記録する配列
	DecideDirection DD = new DecideDirection();		//DecideDirectionのインスタンス変数

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
	 * @throws NumErrException
	 */
	void returnStone(int lateral, int vertical, boolean firstFlag) throws PutErrException, ReturnErrException, NumErrException {
		for(int i = 0; i < 10; i++) {
			returnJudgment[i] = false;
		}

		/* 裏返すことができる座標かどうかを調べる */
		if(returnSerch(lateral, vertical, firstFlag, true)) {

			/* 方向を指定してループする */
			for(int direction = 1; direction < 10; direction++) {

				/* returnJudgement配列がtrueだったらその方向の入れ替え処理をする */
				if(returnJudgment[direction - 1]) {
					returnOneDirection(lateral, vertical, direction, firstFlag);
				}
			}

			/* 裏返せない座標だったので例外を投げる */
		} else {
			throw new ReturnErrException("この座標は石を裏返すことができません");
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
	 * @throws NumErrException
	 */
	boolean returnSerch(int lateral, int vertical, boolean firstFlag, boolean returnFlag) throws PutErrException, NumErrException {
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

	/**
	 * returnSerchメソッドのreturnFlag省略版
	 * 盤面の変更なしの場合省略して記述できるようにするため
	 *
	 * @param lateral
	 *            横の座標
	 * @param vertical
	 *            縦の座標
	 * @param firstFlag
	 *            先攻か後攻かを受け取る
	 * @return 入れ替えられたかどうか
	 * @throws PutErrException
	 * @throws NumErrException
	 */
	boolean returnSerch(int lateral, int vertical, boolean firstFlag) throws PutErrException, NumErrException {
		return returnSerch(lateral, vertical, firstFlag, false);
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

	/**
	 * 受け取った座標から引数の方向に石を裏返していく
	 * 引数で座標と方向、先攻か後攻かのフラグを受け取る
	 *
	 * @param lateral
	 *            横の座標
	 * @param vertical
	 *            縦の座標
	 * @param direction
	 *            方向
	 * @param firstFlag
	 *            先攻か後攻かを受け取る
	 * @throws NumErrException
	 * @throws PutErrException
	 * @throws ReturnErrException
	 */
	void returnOneDirection(int lateral, int vertical, int direction, boolean firstFlag) throws NumErrException, PutErrException, ReturnErrException {

		int stone = bi.stoneColor(firstFlag);	//石の色を格納
		int ldir;								//横の方向
		int vdir;								//縦の方向

		/* 方向を指定 */
		ldir = DD.sideDirection(direction);
		vdir = DD.verticalDirection(direction);

		/* 指定した方向に座標を進める */
		lateral += ldir;
		vertical += vdir;

		/* 石の色が違えば入れ替え、同じならやめる */
		while(bi.stoneColor(firstFlag) != stone) {

			bi.stoneReturn(lateral, vertical);		 //石を裏返す
			lateral += ldir;
			vertical += vdir;						//２つの座標を進める
			stone = bi.partOut(lateral, vertical);	//次の座標の石を取得
		}
	}
}

/**
 * 方向を座標の移動量に変換するクラス
 *
 * @author himihiromu
 *
 */
class DecideDirection {

	/**
	 * 進む方向の横の移動量を返すクラス
	 *
	 * @param direction
	 *            進む方向
	 * @return 横方向の移動量
	 * @throws NumErrException
	 */
	int sideDirection(int direction) throws NumErrException {
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
		default:
			throw new NumErrException("進む方向は1~9です");
		}
		return ldir;
	}

	/**
	 * 進む方向の縦の移動量を返すクラス
	 *
	 * @param direction
	 *            進む方向
	 * @return 縦の移動量
	 * @throws NumErrException
	 */
	int verticalDirection(int direction) throws NumErrException {
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
		default:
			throw new NumErrException("進む方向は1~9です");
		}
		return vdir;

	}
}
