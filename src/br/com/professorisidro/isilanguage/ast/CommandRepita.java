package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandRepita extends AbstractCommand {
 
	private String condition;
	private ArrayList<AbstractCommand> innerCommands;
	
	
	public CommandRepita(String condition, ArrayList<AbstractCommand> innerCommands) {
		this.condition = condition;
		this.innerCommands = innerCommands;
	}
	@Override
	public String generateJavaCode() {
		
		StringBuilder str = new StringBuilder();
		str.append("while ("+condition+") {\n");
		for (AbstractCommand cmd: innerCommands) {
			str.append("\t").append(cmd.generateJavaCode());
		}
		str.append("\n}\n");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandRepita [condition=" + condition + ", innerCommands=" + innerCommands + "]";
	}
	
	

}
