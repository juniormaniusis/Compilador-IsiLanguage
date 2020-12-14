// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.professorisidro.isilanguage.parser;

	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, STRING=11, OPMUL=12, OPDIV=13, OPSUM=14, OPSUB=15, AP=16, FP=17, 
		SC=18, OP=19, ATTR=20, VIR=21, ACH=22, FCH=23, OPREL=24, ID=25, NUMBER=26, 
		WS=27;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_cmdattrib = 8, 
		RULE_cmdpara = 9, RULE_cmdenquanto = 10, RULE_cmdselecao = 11, RULE_expr = 12, 
		RULE_termo = 13, RULE_expr_ = 14, RULE_termo_ = 15, RULE_fator = 16;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"cmdattrib", "cmdpara", "cmdenquanto", "cmdselecao", "expr", "termo", 
		"expr_", "termo_", "fator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'numero'", "'texto'", "'leia'", "'escreva'", 
		"'para'", "'enquanto'", "'se'", "'senao'", null, "'*'", "'/'", "'+'", 
		"'-'", "'('", "')'", "'.'", null, "':='", "','", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "STRING", 
		"OPMUL", "OPDIV", "OPSUM", "OPSUB", "AP", "FP", "SC", "OP", "ATTR", "VIR", 
		"ACH", "FCH", "OPREL", "ID", "NUMBER", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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
		private String _leftType;
		private String _rightType; 
		private ArrayList<String> expressionTypeList = new ArrayList<String>();
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

		public void verificaAtribuida(String id) {
			symbolTable.verificaAtribuida(id);
		}

		public void assertStringType(String id) {
			symbolTable.assertStringType(id);
		}
		
		public String getTypeById(String id) {
			return symbolTable.getTypeById(id);
		}

		public void setAtribuida(String id) {
			symbolTable.sethasValue(id);
		}

		public String verifyTypesAndGetTypeIfValid(ArrayList<String> listTypes, String lado, String expressao) {
			String primeiroTipo = listTypes.get(0);
			for (String tipo: listTypes) {
				if (tipo != primeiroTipo) {
					throw new IsiSemanticException("Elementos do lado " + lado + " possuem tipos incompativeis\n\t na expressao " + expressao);
				}
			}
			return primeiroTipo;
		}

		public void checkTypeAttrib(String leftType, String id, String expression) { 
			for (String type : expressionTypeList) {
				if (leftType != type) {
					throw new IsiSemanticException("Tipos incompatíveis entre " + leftType + " e " + type + "\n\t na sentenca " + id+" := " + expression);
				}
			}
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
			expressionTypeList = new ArrayList<String>();
		}

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__3:
				{
				{
				setState(35);
				decl();
				setState(36);
				bloco();
				}
				}
				break;
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case ID:
				{
				setState(38);
				bloco();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(41);
			match(T__1);
			  program.setVarTable(symbolTable);
			           	  program.setComandos(stack.pop());
			           	 
			           
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(44);
				declaravar();
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 || _la==T__3 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			tipo();
			setState(50);
			match(ID);

				                  _varName = _input.LT(-1).getText();
				                  _varValue = null;
				                  symbol = new IsiVariable(_varName, _tipo, _varValue);
				                  if (!symbolTable.exists(_varName)){
				                     symbolTable.add(symbol);	
				                  }
				                  else{
				                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
				                  }
			                    
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(52);
				match(VIR);
				setState(53);
				match(ID);

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
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				match(T__2);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(T__3);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 curThread = new ArrayList<AbstractCommand>(); 
				        stack.push(curThread);  
			          
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(69);
				cmd();
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdenquantoContext cmdenquanto() {
			return getRuleContext(CmdenquantoContext.class,0);
		}
		public CmdparaContext cmdpara() {
			return getRuleContext(CmdparaContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				cmdleitura();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				cmdattrib();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				cmdselecao();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				cmdenquanto();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 6);
				{
				setState(79);
				cmdpara();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__4);
			setState(83);
			match(AP);
			setState(84);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                        
			setState(86);
			match(FP);
			setState(87);
			match(SC);

			              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			              	CommandLeitura cmd = new CommandLeitura(_readID, var);
			              	stack.peek().add(cmd);
							setAtribuida(_readID);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__5);
			setState(91);
			match(AP);

								  _writeID = "";
								  _writeText = "";
							 
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(93);
				match(ID);
				 verificaID(_input.LT(-1).getText());
					                  _writeID = _input.LT(-1).getText();
				                     
				}
				break;
			case STRING:
				{
				setState(95);
				match(STRING);

									  _writeText = _input.LT(-1).getText();
								 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(99);
			match(FP);
			setState(100);
			match(SC);

								  
							  CommandEscrita cmd;
							  if (!_writeID.isBlank()) {
								cmd = new CommandEscrita(_writeID);
							  } else {
								  
								  cmd = new CommandEscrita(_writeText, true);
							  }
			               	  stack.peek().add(cmd);
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public TerminalNode OPSUM() { return getToken(IsiLangParser.OPSUM, 0); }
		public TerminalNode OPSUB() { return getToken(IsiLangParser.OPSUB, 0); }
		public TerminalNode OPDIV() { return getToken(IsiLangParser.OPDIV, 0); }
		public TerminalNode OPMUL() { return getToken(IsiLangParser.OPMUL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(ID);
			 String id = _input.LT(-1).getText();
								verificaID(id);
								_leftType = getTypeById(id);
			                    _exprID = id;
								resetExpr();
			                   
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPMUL) | (1L << OPDIV) | (1L << OPSUM) | (1L << OPSUB))) != 0)) {
				{
				setState(105);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPMUL) | (1L << OPDIV) | (1L << OPSUM) | (1L << OPSUB))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}


							   	String operador = _input.LT(-1).getText();
								if (operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/")) {
									_exprContent += _exprID + operador;
								}
						   	
			setState(109);
			match(ATTR);
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				{
				setState(110);
				expr();
				setState(111);
				match(SC);

										CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
										checkTypeAttrib(_leftType,_exprID,  _exprContent);
										setAtribuida(_exprID);
										stack.peek().add(cmd);
									
				}
				}
				break;
			case 2:
				{
				{
				setState(114);
				match(STRING);

										
										String str = _input.LT(-1).getText();
										assertStringType(_exprID);
										CommandAtribuicao cmd = new CommandAtribuicao(_exprID, str);
										stack.peek().add(cmd);
									
				setState(116);
				match(SC);
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdparaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public CmdparaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdpara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdpara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdpara(this);
		}
	}

	public final CmdparaContext cmdpara() throws RecognitionException {
		CmdparaContext _localctx = new CmdparaContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdpara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__6);
			setState(120);
			match(AP);
			setState(121);
			cmdattrib();
			setState(122);
			expr();
			setState(123);
			match(OPREL);
			setState(124);
			expr();
			setState(125);
			match(SC);
			setState(126);
			cmd();
			setState(127);
			match(FP);
			setState(128);
			match(ACH);
			setState(130); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(129);
				cmd();
				}
				}
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(134);
			match(FCH);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdenquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdenquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdenquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdenquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdenquanto(this);
		}
	}

	public final CmdenquantoContext cmdenquanto() throws RecognitionException {
		CmdenquantoContext _localctx = new CmdenquantoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdenquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__7);
			setState(137);
			match(AP);

										resetExpr();
								
			setState(139);
			expr();

										stackExprDecision.push(_exprContent);
										_leftType = verifyTypesAndGetTypeIfValid(expressionTypeList, "esquerdo", _exprContent);
								
			setState(141);
			match(OPREL);
			 
										String op = _input.LT(-1).getText();
										String atual = stackExprDecision.pop();
										String novo = atual + op;
										stackExprDecision.push(novo);
										resetExpr();
								
			setState(143);
			expr();

										atual = stackExprDecision.pop();
										novo = atual + _exprContent;
										stackExprDecision.push(novo);
										_rightType = verifyTypesAndGetTypeIfValid(expressionTypeList, "direito", novo);
								
			setState(145);
			match(FP);

									if (_rightType != _leftType) { 
										throw new IsiSemanticException("Tipos não comparáveis");
									}
									_rightType = "";
									_leftType ="";
								
			setState(147);
			match(ACH);
			 
										curThread = new ArrayList<AbstractCommand>(); 
										stack.push(curThread);
			                    
			setState(150); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(149);
				cmd();
				}
				}
				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(154);
			match(FCH);

			                       innerCommands = stack.pop();	
								   CommandRepita cmdRepita = new CommandRepita(stackExprDecision.pop(), innerCommands);
			                   	   stack.peek().add(cmdRepita);
			                    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(T__8);
			setState(158);
			match(AP);

										resetExpr();
								
			setState(160);
			expr();

										stackExprDecision.push(_exprContent);
										_leftType = verifyTypesAndGetTypeIfValid(expressionTypeList, "esquerdo", _exprContent);
								
			setState(162);
			match(OPREL);
			 
										String op = _input.LT(-1).getText();
										String atual = stackExprDecision.pop();
										String novo = atual + op;
										stackExprDecision.push(novo);
										resetExpr();
								
			setState(164);
			expr();

										atual = stackExprDecision.pop();
										novo = atual + _exprContent;
										stackExprDecision.push(novo);
										_rightType = verifyTypesAndGetTypeIfValid(expressionTypeList, "direito", novo);
								
			setState(166);
			match(FP);

									if (_rightType != _leftType) { 
										throw new IsiSemanticException("Tipos não comparáveis");
									}
									_rightType = "";
									_leftType ="";
								
			setState(168);
			match(ACH);
			 
									// cria uma lista de comandos
									curThread = new ArrayList<AbstractCommand>(); 
			                      	stack.push(curThread);
			                    
			setState(171); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(170);
				cmd();
				}
				}
				setState(173); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(175);
			match(FCH);

									// pega a lista de comandos
			                       listaTrue = stack.pop();	
								   // cria um comando de decisao com essa lista de comandos
								   String expreDecision = stackExprDecision.pop();
								   CommandDecisao cmdSE = new CommandDecisao(expreDecision, listaTrue, EMPTY_COMMAND_LIST);
			                   	   stack.peek().add(cmdSE);
			                    
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(177);
				match(T__9);
				setState(178);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(181); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(180);
					cmd();
					}
					}
					setState(183); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
				}
				setState(185);
				match(FCH);

				                   		listaFalse = stack.pop();
										
										// joga fora o comando if sem o listaFalse
										int index = stack.peek().size() - 1; 
										stack.peek().remove(index); 
				                   		CommandDecisao cmdSESENAO = new CommandDecisao(expreDecision, listaTrue, listaFalse);
				                   		stack.peek().add(cmdSESENAO);
				                   	
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public Expr_Context expr_() {
			return getRuleContext(Expr_Context.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			termo();
			setState(191);
			expr_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public Termo_Context termo_() {
			return getRuleContext(Termo_Context.class,0);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			fator();
			setState(194);
			termo_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_Context extends ParserRuleContext {
		public TerminalNode OPSUM() { return getToken(IsiLangParser.OPSUM, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public Expr_Context expr_() {
			return getRuleContext(Expr_Context.class,0);
		}
		public TerminalNode OPSUB() { return getToken(IsiLangParser.OPSUB, 0); }
		public Expr_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr_(this);
		}
	}

	public final Expr_Context expr_() throws RecognitionException {
		Expr_Context _localctx = new Expr_Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPSUM:
				{
				setState(196);
				match(OPSUM);

									_exprContent += '+';
									
								  
				setState(198);
				termo();
				setState(199);
				expr_();
				}
				break;
			case OPSUB:
				{
				setState(201);
				match(OPSUB);
				_exprContent += '-';
								  expressionTypeList.add("NUMBER");
								  
				setState(203);
				termo();
				setState(204);
				expr_();
				}
				break;
			case FP:
			case SC:
			case OPREL:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Termo_Context extends ParserRuleContext {
		public TerminalNode OPMUL() { return getToken(IsiLangParser.OPMUL, 0); }
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public Termo_Context termo_() {
			return getRuleContext(Termo_Context.class,0);
		}
		public TerminalNode OPDIV() { return getToken(IsiLangParser.OPDIV, 0); }
		public Termo_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo_(this);
		}
	}

	public final Termo_Context termo_() throws RecognitionException {
		Termo_Context _localctx = new Termo_Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_termo_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPMUL:
				{
				setState(208);
				match(OPMUL);
				_exprContent += '*';
									expressionTypeList.add("NUMBER");
								  
				setState(210);
				fator();
				setState(211);
				termo_();
				}
				break;
			case OPDIV:
				{
				setState(213);
				match(OPDIV);

								_exprContent += '/';
								expressionTypeList.add("NUMBER");
								  
				setState(215);
				fator();
				setState(216);
				termo_();
				}
				break;
			case OPSUM:
			case OPSUB:
			case FP:
			case SC:
			case OPREL:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FatorContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_fator);
		try {
			setState(232);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				match(NUMBER);
				 
									_exprContent += _input.LT(-1).getText();
									expressionTypeList.add("NUMBER");
							
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				match(STRING);

									_exprContent += _input.LT(-1).getText();
									expressionTypeList.add("TEXT");
							
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
				match(ID);
				 	String id = _input.LT(-1).getText();
										verificaID(id);
										verificaAtribuida(id);
										String type = getTypeById(id);
										_exprContent += id; 
										expressionTypeList.add(type);
								   
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(226);
				match(AP);
				 _exprContent += _input.LT(-1).getText();
				setState(228);
				expr();
				setState(229);
				match(FP);
				 _exprContent += _input.LT(-1).getText();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u00ed\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\5\2*\n\2\3\2\3\2\3\2\3\3\6\3\60\n\3\r\3\16\3\61\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\5\5E\n\5\3\6\3\6\6\6I\n\6\r\6\16\6J\3\7\3\7\3\7\3\7\3\7\3\7\5\7S\n\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\td\n\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\5\nm\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\5\nx\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6"+
		"\13\u0085\n\13\r\13\16\13\u0086\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0099\n\f\r\f\16\f\u009a\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00ae\n\r"+
		"\r\r\16\r\u00af\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00b8\n\r\r\r\16\r\u00b9\3"+
		"\r\3\r\3\r\5\r\u00bf\n\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00d1\n\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00dd\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00eb\n\22\3\22\2\2\23\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\3\3\2\16\21\2\u00f4\2$\3\2\2\2"+
		"\4/\3\2\2\2\6\63\3\2\2\2\bD\3\2\2\2\nF\3\2\2\2\fR\3\2\2\2\16T\3\2\2\2"+
		"\20\\\3\2\2\2\22i\3\2\2\2\24y\3\2\2\2\26\u008a\3\2\2\2\30\u009f\3\2\2"+
		"\2\32\u00c0\3\2\2\2\34\u00c3\3\2\2\2\36\u00d0\3\2\2\2 \u00dc\3\2\2\2\""+
		"\u00ea\3\2\2\2$)\7\3\2\2%&\5\4\3\2&\'\5\n\6\2\'*\3\2\2\2(*\5\n\6\2)%\3"+
		"\2\2\2)(\3\2\2\2*+\3\2\2\2+,\7\4\2\2,-\b\2\1\2-\3\3\2\2\2.\60\5\6\4\2"+
		"/.\3\2\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\5\3\2\2\2\63\64"+
		"\5\b\5\2\64\65\7\33\2\2\65;\b\4\1\2\66\67\7\27\2\2\678\7\33\2\28:\b\4"+
		"\1\29\66\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7"+
		"\24\2\2?\7\3\2\2\2@A\7\5\2\2AE\b\5\1\2BC\7\6\2\2CE\b\5\1\2D@\3\2\2\2D"+
		"B\3\2\2\2E\t\3\2\2\2FH\b\6\1\2GI\5\f\7\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2"+
		"JK\3\2\2\2K\13\3\2\2\2LS\5\16\b\2MS\5\20\t\2NS\5\22\n\2OS\5\30\r\2PS\5"+
		"\26\f\2QS\5\24\13\2RL\3\2\2\2RM\3\2\2\2RN\3\2\2\2RO\3\2\2\2RP\3\2\2\2"+
		"RQ\3\2\2\2S\r\3\2\2\2TU\7\7\2\2UV\7\22\2\2VW\7\33\2\2WX\b\b\1\2XY\7\23"+
		"\2\2YZ\7\24\2\2Z[\b\b\1\2[\17\3\2\2\2\\]\7\b\2\2]^\7\22\2\2^c\b\t\1\2"+
		"_`\7\33\2\2`d\b\t\1\2ab\7\r\2\2bd\b\t\1\2c_\3\2\2\2ca\3\2\2\2de\3\2\2"+
		"\2ef\7\23\2\2fg\7\24\2\2gh\b\t\1\2h\21\3\2\2\2ij\7\33\2\2jl\b\n\1\2km"+
		"\t\2\2\2lk\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\b\n\1\2ow\7\26\2\2pq\5\32\16"+
		"\2qr\7\24\2\2rs\b\n\1\2sx\3\2\2\2tu\7\r\2\2uv\b\n\1\2vx\7\24\2\2wp\3\2"+
		"\2\2wt\3\2\2\2x\23\3\2\2\2yz\7\t\2\2z{\7\22\2\2{|\5\22\n\2|}\5\32\16\2"+
		"}~\7\32\2\2~\177\5\32\16\2\177\u0080\7\24\2\2\u0080\u0081\5\f\7\2\u0081"+
		"\u0082\7\23\2\2\u0082\u0084\7\30\2\2\u0083\u0085\5\f\7\2\u0084\u0083\3"+
		"\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u0089\7\31\2\2\u0089\25\3\2\2\2\u008a\u008b\7\n\2"+
		"\2\u008b\u008c\7\22\2\2\u008c\u008d\b\f\1\2\u008d\u008e\5\32\16\2\u008e"+
		"\u008f\b\f\1\2\u008f\u0090\7\32\2\2\u0090\u0091\b\f\1\2\u0091\u0092\5"+
		"\32\16\2\u0092\u0093\b\f\1\2\u0093\u0094\7\23\2\2\u0094\u0095\b\f\1\2"+
		"\u0095\u0096\7\30\2\2\u0096\u0098\b\f\1\2\u0097\u0099\5\f\7\2\u0098\u0097"+
		"\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009d\7\31\2\2\u009d\u009e\b\f\1\2\u009e\27\3\2\2"+
		"\2\u009f\u00a0\7\13\2\2\u00a0\u00a1\7\22\2\2\u00a1\u00a2\b\r\1\2\u00a2"+
		"\u00a3\5\32\16\2\u00a3\u00a4\b\r\1\2\u00a4\u00a5\7\32\2\2\u00a5\u00a6"+
		"\b\r\1\2\u00a6\u00a7\5\32\16\2\u00a7\u00a8\b\r\1\2\u00a8\u00a9\7\23\2"+
		"\2\u00a9\u00aa\b\r\1\2\u00aa\u00ab\7\30\2\2\u00ab\u00ad\b\r\1\2\u00ac"+
		"\u00ae\5\f\7\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3\2"+
		"\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7\31\2\2\u00b2"+
		"\u00be\b\r\1\2\u00b3\u00b4\7\f\2\2\u00b4\u00b5\7\30\2\2\u00b5\u00b7\b"+
		"\r\1\2\u00b6\u00b8\5\f\7\2\u00b7\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\7\31"+
		"\2\2\u00bc\u00bd\b\r\1\2\u00bd\u00bf\3\2\2\2\u00be\u00b3\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\31\3\2\2\2\u00c0\u00c1\5\34\17\2\u00c1\u00c2\5\36"+
		"\20\2\u00c2\33\3\2\2\2\u00c3\u00c4\5\"\22\2\u00c4\u00c5\5 \21\2\u00c5"+
		"\35\3\2\2\2\u00c6\u00c7\7\20\2\2\u00c7\u00c8\b\20\1\2\u00c8\u00c9\5\34"+
		"\17\2\u00c9\u00ca\5\36\20\2\u00ca\u00d1\3\2\2\2\u00cb\u00cc\7\21\2\2\u00cc"+
		"\u00cd\b\20\1\2\u00cd\u00ce\5\34\17\2\u00ce\u00cf\5\36\20\2\u00cf\u00d1"+
		"\3\2\2\2\u00d0\u00c6\3\2\2\2\u00d0\u00cb\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\37\3\2\2\2\u00d2\u00d3\7\16\2\2\u00d3\u00d4\b\21\1\2\u00d4\u00d5\5\""+
		"\22\2\u00d5\u00d6\5 \21\2\u00d6\u00dd\3\2\2\2\u00d7\u00d8\7\17\2\2\u00d8"+
		"\u00d9\b\21\1\2\u00d9\u00da\5\"\22\2\u00da\u00db\5 \21\2\u00db\u00dd\3"+
		"\2\2\2\u00dc\u00d2\3\2\2\2\u00dc\u00d7\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"!\3\2\2\2\u00de\u00df\7\34\2\2\u00df\u00eb\b\22\1\2\u00e0\u00e1\7\r\2"+
		"\2\u00e1\u00eb\b\22\1\2\u00e2\u00e3\7\33\2\2\u00e3\u00eb\b\22\1\2\u00e4"+
		"\u00e5\7\22\2\2\u00e5\u00e6\b\22\1\2\u00e6\u00e7\5\32\16\2\u00e7\u00e8"+
		"\7\23\2\2\u00e8\u00e9\b\22\1\2\u00e9\u00eb\3\2\2\2\u00ea\u00de\3\2\2\2"+
		"\u00ea\u00e0\3\2\2\2\u00ea\u00e2\3\2\2\2\u00ea\u00e4\3\2\2\2\u00eb#\3"+
		"\2\2\2\23)\61;DJRclw\u0086\u009a\u00af\u00b9\u00be\u00d0\u00dc\u00ea";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}