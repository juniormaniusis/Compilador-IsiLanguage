grammar IsiLang;

@header{
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private Stack<String> stackExprDecision = new Stack<String>();
	private String _readID;
	private String _writeID;
	private String _writeText;
	private String _exprID;
	private String _exprContent;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> innerCommands;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> EMPTY_COMMAND_LIST = new ArrayList<AbstractCommand>();
	
	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}
	public void assertStringType(String id) {
		symbolTable.assertStringType(id);
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}

	public ArrayList<String> getWarnings() {
			ArrayList<String> wList = new ArrayList<String>();
			for (IsiSymbol is: symbolTable.getNotUsedSymbols()) {
				wList.add("Simbolo " + is.getName() + " declarado mas nunca utilizado");
			}
			return wList;
		}
	
	public void generateCode(){
		program.generateTarget();
	}
	public void resetExpr() {
		_exprContent = "";
	}
}

prog	: 'programa' decl bloco  'fimprog.'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           	 
           } 
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  tipo ID  {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    } 
              (  VIR 
              	 ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              )* 
               SC
           ;
           
tipo       : 'numero' { _tipo = IsiVariable.NUMBER;  }
           | 'texto'  { _tipo = IsiVariable.TEXT;  }
           ;
        
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
		;
		

cmd		:  cmdleitura  
 		|  cmdescrita 
 		|  cmdattrib
 		|  cmdselecao  
		|  cmdenquanto
		|  cmdpara
		;
		
cmdleitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                        } 
                     FP 
                     SC 
                     
              {
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }   
			;
			
cmdescrita	: 'escreva' 
                 AP {
					  _writeID = "";
					  _writeText = "";
				 }
                 (ID { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
                     }
				 | 
				 STRING {
					  _writeText = _input.LT(-1).getText();
					  System.out.println("escreva a str = "+ _writeText);
				 }
				)
                 FP 
                 SC
               {
					  
				  CommandEscrita cmd;
				  if (!_writeID.isBlank()) {
					cmd = new CommandEscrita(_writeID);
				  } else {
					  System.out.println("criando o comando escrecva" + _writeText);
					  cmd = new CommandEscrita(_writeText, true);
				  }
               	  stack.peek().add(cmd);
               }
			;
			
cmdattrib	:  ID { verificaID(_input.LT(-1).getText());
                    _exprID = _input.LT(-1).getText();
                   } 
               ATTR { resetExpr(); } 
               ((
					expr 
					SC
					{
						CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
						stack.peek().add(cmd);
					}
				
				)
				|
				(
					STRING {
						String str = _input.LT(-1).getText();
						assertStringType(_exprID);
						CommandAtribuicao cmd = new CommandAtribuicao(_exprID, str);
						stack.peek().add(cmd);
					}
					SC					
				))
			;

STRING : '"' ( '\\"' | . )*? '"' ;


cmdpara		 : 'para' AP 
					  cmdattrib 
					  
					  expr
					  OPREL
					  expr
					  SC
					  
					  cmd
					  FP 
					  ACH 
					  (cmd)+
					  FCH
					  ;
					  
cmdenquanto  :  'enquanto' AP {
							resetExpr();
					}
					expr {
							stackExprDecision.push(_exprContent);
					}
					OPREL { 
							String op = _input.LT(-1).getText();
							String atual = stackExprDecision.pop();
							String novo = atual + op;
							stackExprDecision.push(novo);
							resetExpr();
					}
					expr {
							atual = stackExprDecision.pop();
							novo = atual + _exprContent;
							stackExprDecision.push(novo);
					}
                    FP
					ACH { 
							curThread = new ArrayList<AbstractCommand>(); 
							stack.push(curThread);
                    }
                    (cmd)+ 
                    FCH 
                    {
                       innerCommands = stack.pop();	
					   System.out.println(innerCommands);
					   CommandRepita cmdRepita = new CommandRepita(stackExprDecision.pop(), innerCommands);
                   	   stack.peek().add(cmdRepita);
                    } 
					;

cmdselecao  :  'se' AP {
							resetExpr();
					}
					expr {
							stackExprDecision.push(_exprContent);
					}
					OPREL { 
							String op = _input.LT(-1).getText();
							String atual = stackExprDecision.pop();
							String novo = atual + op;
							stackExprDecision.push(novo);
							resetExpr();
					}
					expr {
							atual = stackExprDecision.pop();
							novo = atual + _exprContent;
							stackExprDecision.push(novo);
							resetExpr();
					}
                    FP
					ACH { 
						// cria uma lista de comandos
						curThread = new ArrayList<AbstractCommand>(); 
                      	stack.push(curThread);
                    }
                    (cmd)+ 
                    
                    FCH 
                    {
						// pega a lista de comandos
                       listaTrue = stack.pop();	
					   // cria um comando de decisao com essa lista de comandos
					   String expreDecision = stackExprDecision.pop();
					   CommandDecisao cmdSE = new CommandDecisao(expreDecision, listaTrue, EMPTY_COMMAND_LIST);
                   	   stack.peek().add(cmdSE);
                    } 
                   ('senao' 
                   	 ACH
                   	 {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 } 
                   	(cmd+) 
                   	FCH
                   	{
                   		listaFalse = stack.pop();
						
						// joga fora o comando if sem o listaFalse
						int index = stack.peek().size() - 1; 
						stack.peek().remove(index); 
                   		CommandDecisao cmdSESENAO = new CommandDecisao(expreDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmdSESENAO);
                   	}
                   )?
            ;
			
expr		:	expr 
				OPSUM { _exprContent += _input.LT(-1).getText();}
				termo
			|	expr
			 	OPSUB { _exprContent += _input.LT(-1).getText();}
				termo
			| 	termo
			;

termo		: termo OPMUL { _exprContent += _input.LT(-1).getText();} fator
			| termo OPDIV { _exprContent += _input.LT(-1).getText();} fator
			| fator;

			
OPMUL 		: '*';			
OPDIV 		: '/';
OPSUM		: '+';
OPSUB		: '-';

fator		:	NUMBER { _exprContent += _input.LT(-1).getText();}
			|	ID { _exprContent += _input.LT(-1).getText();}
			|	AP { _exprContent += _input.LT(-1).getText();}
				expr 
				FP { _exprContent += _input.LT(-1).getText();}
			;

AP	: '('
	;
	
FP	: ')'
	;
	

SC	: '.'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
	
ATTR : ':='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
     
FCH  : '}'
     ;
	 
	 
OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;
      
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;
		
WS	: (' ' | '\t' | '\n' | '\r') -> skip;