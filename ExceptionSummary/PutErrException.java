package ExceptionSummary;

/**
 * 石を置くときに受け取った座標が盤面の範囲外、または壁の座標だった場合に投げられる例外
 * @author 183108
 *
 */
public class PutErrException extends Exception {
	private static final long serialVersionUID = 1L;

	public PutErrException(String msg) {
		super(msg);
	}
}
