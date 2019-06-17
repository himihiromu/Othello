package ExceptionSummary;

/**
 * 石を裏返すときに、その座標に石がなかった場合に投げられる例外
 * @author 183108
 *
 */
public class ReturnErrException extends Exception {
	private static final long serialVersionUID = 1L;

	public ReturnErrException(String msg) {
		super(msg);
	}
}
