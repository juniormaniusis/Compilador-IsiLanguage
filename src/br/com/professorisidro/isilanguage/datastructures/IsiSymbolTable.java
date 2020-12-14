package br.com.professorisidro.isilanguage.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;

import java.util.*;
public class IsiSymbolTable {
	
	private HashMap<String, IsiSymbol> map;
	
	public IsiSymbolTable() {
		map = new HashMap<String, IsiSymbol>();
	}
	
	public void add(IsiSymbol symbol) {
		map.put(symbol.getName(), symbol);
	}
	
	public boolean exists(String symbolName) {
		return map.get(symbolName) != null;
	}
	
	public IsiSymbol get(String symbolName) {
		IsiSymbol symbol = map.get(symbolName);
		symbol.setUsed();
		return symbol;
	}

	public List<IsiSymbol> getNotUsedSymbols() {
		ArrayList<IsiSymbol> symbols = this.getAll();
		List<IsiSymbol> symbolsNotUset  = symbols.stream()
												 .filter(symbol -> !symbol.used)
												 .collect(Collectors.toList());
		return symbolsNotUset;
	}	

	public void assertStringType(String id) throws IsiSemanticException {
		IsiVariable variable = (IsiVariable) this.get(id);
		if (variable.getType() == IsiVariable.TEXT) {
			return;
		}
		throw new IsiSemanticException("Variável " + variable.getName() + " é do tipo " + variable.getTypeText() + " não pode ser receber um texto");
	}

	public void sethasValue(String id) {
		IsiVariable variable = (IsiVariable) this.get(id);
		variable.sethasValue();
	}

	public String getTypeById(String id) {
		IsiVariable variable = (IsiVariable) this.get(id);
		if (variable.getType() == IsiVariable.TEXT) {
			return "TEXT";
		} else return "NUMBER";
	}

	public void verificaAtribuida(String id) {
		IsiSymbol  is = this.get(id);
		if (is.hasValue) {
			return;
		} throw new IsiSemanticException("A variavel " + is.getName() + " é utilizada antes de ser atribuida");
	}
	
	public ArrayList<IsiSymbol> getAll(){
		ArrayList<IsiSymbol> lista = new ArrayList<IsiSymbol>();
		for (IsiSymbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}

	
	
}
