package mainpack;

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
