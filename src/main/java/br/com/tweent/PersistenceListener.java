package br.com.tweent;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PersistenceListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String database = sce.getServletContext().getInitParameter("database-path");
		PersistenceFactory.setPath(database);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			PersistenceFactory.takeSnapshotAndClose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
