package com.disparter.auth.util;

import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InicializarPropiedadesDeConfiguracaoContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Enumeration<String> enumeracao = servletContextEvent.getServletContext().getInitParameterNames();
		while (enumeracao.hasMoreElements()) {
			String chave = enumeracao.nextElement();
			if (chave.startsWith("configuracao.")) {
				Object object = servletContextEvent.getServletContext().getInitParameter(chave);
				if (object instanceof String) {
					String valor = servletContextEvent.getServletContext().getInitParameter(chave);
					com.disparter.util.PropiedadesDeConfiguracao.adicionar(chave, valor);
				}
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}