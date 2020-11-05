package br.com.tweent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class AuthenticationPainel {

	private HttpSession session;
	
	private Map<String, String> users = new HashMap<String, String>();
	
	public AuthenticationPainel(HttpSession session) {
		users.put("tweent", "tweent");
		this.session = session;
	}

	public void authenticate(String login, String password) throws AuthenticationException {
		String userPassword = users.get(login);
		
		if (userPassword == null) {
			throw new AuthenticationException("Usuario nao possui acesso");
		}
		
		if (!userPassword.equals(password)) {
			throw new AuthenticationException("Usuario nao possui acesso");
		}
		
		session.setAttribute("login", login);
	}

}
