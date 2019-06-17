package ExceptionSummary;

/**
 * 引数の数字が求められる数値の範囲外だった場合に投げられる例外
 * @author 183108
 *
 */
public class NumErrException extends Exception {
	private static final long serialVersionUID = 1L;

	public NumErrException(String msg) {
		super(msg);
	}
}