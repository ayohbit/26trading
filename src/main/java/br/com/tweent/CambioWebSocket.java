package br.com.tweent;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/cambio")
public class CambioWebSocket implements Runnable {

	private Session session;

	private CambioFactory factory = new CambioFactory();

	@OnOpen
	public void start(Session session) {
		this.session = session;

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		
		while(true) {
			if (this.session.isOpen()) {
				if (this.factory.hasUpdate()) {
					try {
						String message = this.factory.get();
						
						this.session.getBasicRemote().sendText(message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				break;
			}
		}
	}

}
