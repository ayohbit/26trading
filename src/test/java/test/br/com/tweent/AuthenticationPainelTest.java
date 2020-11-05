package test.br.com.tweent;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.tweent.AuthenticationException;
import br.com.tweent.AuthenticationPainel;

public class AuthenticationPainelTest {

	private HttpSession session;
	
	@Before
	public void setUp() throws Exception {
		session = Mockito.mock(HttpSession.class);
	}

	@Test(expected=AuthenticationException.class)
	public void testAuthenticateFail() throws AuthenticationException {
		
		AuthenticationPainel userPainel = new AuthenticationPainel(session);
		
		userPainel.authenticate("tweent", "senha");
	}
	
	public void testAuthentication() throws AuthenticationException {

		AuthenticationPainel userPainel = new AuthenticationPainel(session);
		
		userPainel.authenticate("tweent", "tweent");
		
		Mockito.verify(session).setAttribute("login", "tweent");
	}

}
