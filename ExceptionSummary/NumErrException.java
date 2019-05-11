package ExceptionSummary;

public class NumErrException extends Exception {
	private static final long serialVersionUID = 1L;

	public NumErrException(String msg) {
		super(msg);
	}
}