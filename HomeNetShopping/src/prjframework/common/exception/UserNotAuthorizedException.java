package prjframework.common.exception;

public class UserNotAuthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotAuthorizedException(String name) {
		super(name);
	}
	
	public UserNotAuthorizedException() {
	}
}
