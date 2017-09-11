package com.disparter.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PropertiesUtils {
	private static final Logger LOGGER = Logger.getLogger(PropertiesUtils.class.getSimpleName());
	private final static String PATH_ARQUIVO_CONFIGURACAO = "configuracao.path.cliente";
	private String pathArquivoConfiguracoes;
	private String nomeArquivo;
	
	public PropertiesUtils(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		this.pathArquivoConfiguracoes =  PropiedadesDeConfiguracao.getValor(PATH_ARQUIVO_CONFIGURACAO);
	}
	
	public  String recuperarConteudo(String chave) {
		return getPropertiesDoArquivoDeConfiguracao().getString(chave);
	}
	
	public  ResourceBundle getPropertiesDoArquivoDeConfiguracao() {
		return recuperarResorceBundlerExterno(pathArquivoConfiguracoes);
	}
	
	private  ResourceBundle recuperarResorceBundlerExterno(String diretorioCompletoQualificado){
		try {
			File file = new File(diretorioCompletoQualificado);
			URL[] urls = new URL[]{file.toURI().toURL()};
			ClassLoader loader = new URLClassLoader(urls);
			return ResourceBundle.getBundle(nomeArquivo,getBrasil(), loader);
		} catch (MalformedURLException e) {
			LOGGER.log(Level.WARNING, "Arquivo mau formatado.", e);
			return null;
		}
	}
	
	
	public static Locale getBrasil(){
		return  new Locale("pt", "BR");
	}
	
}