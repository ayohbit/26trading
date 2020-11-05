package br.com.tweent;

public class AuthenticationException extends Exception {

	private static final long serialVersionUID = 2839201403785378895L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AuthenticationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AuthenticationException(String arg0) {
		super(arg0);
	}

	public AuthenticationException(Throwable arg0) {
		super(arg0);
	}

	
}
