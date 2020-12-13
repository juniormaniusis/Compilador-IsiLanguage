package br.com.professorisidro.isilanguage.datastructures;

public abstract class IsiSymbol {
	
	protected String name;
	protected boolean used;
	
	public abstract String generateJavaCode();

	public IsiSymbol(String name) {
		this.name = name;
		this.used = false;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	/*
		torna o símbolo utilizado. note que ele inicia como false;
		este método só deve ser chamado quando o símbolo for utilizado;
	*/
	public void setUsed() {
		this.used = true;
	}

	@Override
	public String toString() {
		return "IsiSymbol [name=" + name + "]";
	}
	
	
	

}
