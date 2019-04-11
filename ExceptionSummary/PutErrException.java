package ExceptionSummary;

public class PutErrException extends Exception {
	private static final long serialVersionUID = 1L;

	public PutErrException(String msg) {
		super(msg);
	}
}
