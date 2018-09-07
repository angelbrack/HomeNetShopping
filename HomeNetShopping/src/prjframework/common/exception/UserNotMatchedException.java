package prjframework.common.exception;

public class UserNotMatchedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotMatchedException(String name) {
		super(name);
	}
	
	public UserNotMatchedException() {
	}
}
