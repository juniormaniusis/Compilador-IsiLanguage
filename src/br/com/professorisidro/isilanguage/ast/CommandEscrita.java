package br.com.professorisidro.isilanguage.ast;

public class CommandEscrita extends AbstractCommand {

	private String id;
	private String text;
	
	public CommandEscrita(String id) {
		this.id = id;
		this.text = null;
	}

	public CommandEscrita(String text, boolean isText) {
		if (isText) {
			this.text = text;
			this.id = null;
		} else {
			this.id = text;
			this.text = null;
		}
	}

	@Override
	public String generateJavaCode() {
		if (id == null) {
			return "System.out.println(\""+text+"\");";
		} else {
			return "System.out.println("+id+");";
		}
		
	}
	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "] [text=" + text + "]";
	}
	

}
