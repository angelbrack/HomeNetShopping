package prjframework.common.exception;

/**
 * <pre>
 * 오류 처리시 사용자에게 Alert 창을 띄우기 위한 Exception 정의
 * @author oops
 * </pre>
 */
public class CommonAlertException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4619341543950566370L;

	public CommonAlertException() {
		super();
	}
	
	public CommonAlertException(String message) {
		super(message);
	}
	
    public CommonAlertException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CommonAlertException(Throwable cause) {
        super(cause);
    }    
}
