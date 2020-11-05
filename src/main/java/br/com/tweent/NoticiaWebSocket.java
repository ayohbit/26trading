package br.com.tweent;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/noticia/{codigo}")
public class NoticiaWebSocket implements Runnable {

	private NoticiaFactory factory = new NoticiaFactory();
	private String codigo;
	private Session session;

	@OnOpen
	public void start(@PathParam("codigo") String codigo, Session session) {

		if (codigo == null || codigo.isEmpty()) {
			codigo = "5";
		}

		this.codigo = codigo;
		this.session = session;

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		while (true) {
			if (this.session.isOpen()) {
				try {
					if (this.factory.hasUpdate(codigo)) {
						String message = this.factory.get(codigo);

						this.session.getBasicRemote().sendText(message);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
		}

	}
}
