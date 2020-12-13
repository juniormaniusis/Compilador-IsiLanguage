package br.com.professorisidro.isilanguage.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
	
	public ArrayList<IsiSymbol> getAll(){
		ArrayList<IsiSymbol> lista = new ArrayList<IsiSymbol>();
		for (IsiSymbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}

	
	
}
