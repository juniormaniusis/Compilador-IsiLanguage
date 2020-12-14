// Generated from /home/loweer/git/IsiLanguageEmbriao/IsiLang.g4 by ANTLR 4.8

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
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

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
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
			"cmdattrib", "cmdpara", "cmdenquanto", "cmdselecao", "expr", "termo", 
			"expr_", "termo_", "fator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'para'", "'enquanto'", "'se'", "'senao'", null, "'*'", "'/'", "'+'", 
			"'-'", "'('", "')'", "'.'", null, "':='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "STRING", 
			"OPMUL", "OPDIV", "OPSUM", "OPSUB", "AP", "FP", "SC", "OP", "ATTR", "VIR", 
			"ACH", "FCH", "OPREL", "ID", "NUMBER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
		public void assertStringType(String id) {
			symbolTable.assertStringType(id);
		}
		
		public String getTypeById(String id) {
			return symbolTable.getTypeById(id);
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
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			decl();
			setState(36);
			bloco();
			setState(37);
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
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40);
				declaravar();
				}
				}
				setState(43); 
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
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			tipo();
			setState(46);
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
			                    
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(48);
				match(VIR);
				setState(49);
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
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
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
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				match(T__2);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
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
			          
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				cmd();
				}
				}
				setState(68); 
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
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				cmdleitura();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(72);
				cmdattrib();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(73);
				cmdselecao();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(74);
				cmdenquanto();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 6);
				{
				setState(75);
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
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(T__4);
			setState(79);
			match(AP);
			setState(80);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                        
			setState(82);
			match(FP);
			setState(83);
			match(SC);

			              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			              	CommandLeitura cmd = new CommandLeitura(_readID, var);
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
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__5);
			setState(87);
			match(AP);

								  _writeID = "";
								  _writeText = "";
							 
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(89);
				match(ID);
				 verificaID(_input.LT(-1).getText());
					                  _writeID = _input.LT(-1).getText();
				                     
				}
				break;
			case STRING:
				{
				setState(91);
				match(STRING);

									  _writeText = _input.LT(-1).getText();
								 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(95);
			match(FP);
			setState(96);
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
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdattrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(ID);
			 String id = _input.LT(-1).getText();
								verificaID(id);
								_leftType = getTypeById(id);
			                    _exprID = id;
								resetExpr();
			                   
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPMUL) | (1L << OPDIV) | (1L << OPSUM) | (1L << OPSUB))) != 0)) {
				{
				setState(101);
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
						   	
			setState(105);
			match(ATTR);
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				{
				setState(106);
				expr();
				setState(107);
				match(SC);

										CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
										checkTypeAttrib(_leftType,_exprID,  _exprContent);
										stack.peek().add(cmd);
									
				}
				}
				break;
			case 2:
				{
				{
				setState(110);
				match(STRING);

										
										String str = _input.LT(-1).getText();
										assertStringType(_exprID);
										CommandAtribuicao cmd = new CommandAtribuicao(_exprID, str);
										stack.peek().add(cmd);
									
				setState(112);
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
	}

	public final CmdparaContext cmdpara() throws RecognitionException {
		CmdparaContext _localctx = new CmdparaContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdpara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__6);
			setState(116);
			match(AP);
			setState(117);
			cmdattrib();
			setState(118);
			expr();
			setState(119);
			match(OPREL);
			setState(120);
			expr();
			setState(121);
			match(SC);
			setState(122);
			cmd();
			setState(123);
			match(FP);
			setState(124);
			match(ACH);
			setState(126); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(125);
				cmd();
				}
				}
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(130);
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
	}

	public final CmdenquantoContext cmdenquanto() throws RecognitionException {
		CmdenquantoContext _localctx = new CmdenquantoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdenquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__7);
			setState(133);
			match(AP);

										resetExpr();
								
			setState(135);
			expr();

										stackExprDecision.push(_exprContent);
										_leftType = verifyTypesAndGetTypeIfValid(expressionTypeList, "esquerdo", _exprContent);
								
			setState(137);
			match(OPREL);
			 
										String op = _input.LT(-1).getText();
										String atual = stackExprDecision.pop();
										String novo = atual + op;
										stackExprDecision.push(novo);
										resetExpr();
								
			setState(139);
			expr();

										atual = stackExprDecision.pop();
										novo = atual + _exprContent;
										stackExprDecision.push(novo);
										_rightType = verifyTypesAndGetTypeIfValid(expressionTypeList, "direito", novo);
								
			setState(141);
			match(FP);

									if (_rightType != _leftType) { 
										throw new IsiSemanticException("Tipos não comparáveis");
									}
									_rightType = "";
									_leftType ="";
								
			setState(143);
			match(ACH);
			 
										curThread = new ArrayList<AbstractCommand>(); 
										stack.push(curThread);
			                    
			setState(146); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(145);
				cmd();
				}
				}
				setState(148); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(150);
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
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__8);
			setState(154);
			match(AP);

										resetExpr();
								
			setState(156);
			expr();

										stackExprDecision.push(_exprContent);
										_leftType = verifyTypesAndGetTypeIfValid(expressionTypeList, "esquerdo", _exprContent);
								
			setState(158);
			match(OPREL);
			 
										String op = _input.LT(-1).getText();
										String atual = stackExprDecision.pop();
										String novo = atual + op;
										stackExprDecision.push(novo);
										resetExpr();
								
			setState(160);
			expr();

										atual = stackExprDecision.pop();
										novo = atual + _exprContent;
										stackExprDecision.push(novo);
										_rightType = verifyTypesAndGetTypeIfValid(expressionTypeList, "direito", novo);
								
			setState(162);
			match(FP);

									if (_rightType != _leftType) { 
										throw new IsiSemanticException("Tipos não comparáveis");
									}
									_rightType = "";
									_leftType ="";
								
			setState(164);
			match(ACH);
			 
									// cria uma lista de comandos
									curThread = new ArrayList<AbstractCommand>(); 
			                      	stack.push(curThread);
			                    
			setState(167); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166);
				cmd();
				}
				}
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(171);
			match(FCH);

									// pega a lista de comandos
			                       listaTrue = stack.pop();	
								   // cria um comando de decisao com essa lista de comandos
								   String expreDecision = stackExprDecision.pop();
								   CommandDecisao cmdSE = new CommandDecisao(expreDecision, listaTrue, EMPTY_COMMAND_LIST);
			                   	   stack.peek().add(cmdSE);
			                    
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(173);
				match(T__9);
				setState(174);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(176);
					cmd();
					}
					}
					setState(179); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
				}
				setState(181);
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
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			termo();
			setState(187);
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
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			fator();
			setState(190);
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
	}

	public final Expr_Context expr_() throws RecognitionException {
		Expr_Context _localctx = new Expr_Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPSUM:
				{
				setState(192);
				match(OPSUM);
				_exprContent += '+';
				setState(194);
				termo();
				setState(195);
				expr_();
				}
				break;
			case OPSUB:
				{
				setState(197);
				match(OPSUB);
				_exprContent += '-';
				setState(199);
				termo();
				setState(200);
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
	}

	public final Termo_Context termo_() throws RecognitionException {
		Termo_Context _localctx = new Termo_Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_termo_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPMUL:
				{
				setState(204);
				match(OPMUL);
				_exprContent += '*';
				setState(206);
				fator();
				setState(207);
				termo_();
				}
				break;
			case OPDIV:
				{
				setState(209);
				match(OPDIV);
				_exprContent += '/';
				setState(211);
				fator();
				setState(212);
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
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_fator);
		try {
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				match(NUMBER);
				 
									_exprContent += _input.LT(-1).getText();
									expressionTypeList.add("NUMBER");
							
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(STRING);

									_exprContent += _input.LT(-1).getText();
									expressionTypeList.add("TEXT");
							
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				match(ID);
				 	String id = _input.LT(-1).getText();
										verificaID(id);
										String type = getTypeById(id);
										_exprContent += id; 
										expressionTypeList.add(type);
								   
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(222);
				match(AP);
				 _exprContent += _input.LT(-1).getText();
				setState(224);
				expr();
				setState(225);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u00e9\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\66\n\4\f\4\16\49\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5A\n\5\3\6\3\6"+
		"\6\6E\n\6\r\6\16\6F\3\7\3\7\3\7\3\7\3\7\3\7\5\7O\n\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t`\n\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\5\ni\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nt\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u0081\n\13\r\13"+
		"\16\13\u0082\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\6\f\u0095\n\f\r\f\16\f\u0096\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00aa\n\r\r\r\16\r\u00ab\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\6\r\u00b4\n\r\r\r\16\r\u00b5\3\r\3\r\3\r\5\r\u00bb"+
		"\n\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\5\20\u00cd\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u00d9\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u00e7\n\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"\2\3\3\2\16\21\2\u00ef\2$\3\2\2\2\4+\3\2\2\2\6/\3\2\2\2"+
		"\b@\3\2\2\2\nB\3\2\2\2\fN\3\2\2\2\16P\3\2\2\2\20X\3\2\2\2\22e\3\2\2\2"+
		"\24u\3\2\2\2\26\u0086\3\2\2\2\30\u009b\3\2\2\2\32\u00bc\3\2\2\2\34\u00bf"+
		"\3\2\2\2\36\u00cc\3\2\2\2 \u00d8\3\2\2\2\"\u00e6\3\2\2\2$%\7\3\2\2%&\5"+
		"\4\3\2&\'\5\n\6\2\'(\7\4\2\2()\b\2\1\2)\3\3\2\2\2*,\5\6\4\2+*\3\2\2\2"+
		",-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\5\3\2\2\2/\60\5\b\5\2\60\61\7\33\2\2\61"+
		"\67\b\4\1\2\62\63\7\27\2\2\63\64\7\33\2\2\64\66\b\4\1\2\65\62\3\2\2\2"+
		"\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:;\7\24\2"+
		"\2;\7\3\2\2\2<=\7\5\2\2=A\b\5\1\2>?\7\6\2\2?A\b\5\1\2@<\3\2\2\2@>\3\2"+
		"\2\2A\t\3\2\2\2BD\b\6\1\2CE\5\f\7\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3"+
		"\2\2\2G\13\3\2\2\2HO\5\16\b\2IO\5\20\t\2JO\5\22\n\2KO\5\30\r\2LO\5\26"+
		"\f\2MO\5\24\13\2NH\3\2\2\2NI\3\2\2\2NJ\3\2\2\2NK\3\2\2\2NL\3\2\2\2NM\3"+
		"\2\2\2O\r\3\2\2\2PQ\7\7\2\2QR\7\22\2\2RS\7\33\2\2ST\b\b\1\2TU\7\23\2\2"+
		"UV\7\24\2\2VW\b\b\1\2W\17\3\2\2\2XY\7\b\2\2YZ\7\22\2\2Z_\b\t\1\2[\\\7"+
		"\33\2\2\\`\b\t\1\2]^\7\r\2\2^`\b\t\1\2_[\3\2\2\2_]\3\2\2\2`a\3\2\2\2a"+
		"b\7\23\2\2bc\7\24\2\2cd\b\t\1\2d\21\3\2\2\2ef\7\33\2\2fh\b\n\1\2gi\t\2"+
		"\2\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\b\n\1\2ks\7\26\2\2lm\5\32\16\2mn"+
		"\7\24\2\2no\b\n\1\2ot\3\2\2\2pq\7\r\2\2qr\b\n\1\2rt\7\24\2\2sl\3\2\2\2"+
		"sp\3\2\2\2t\23\3\2\2\2uv\7\t\2\2vw\7\22\2\2wx\5\22\n\2xy\5\32\16\2yz\7"+
		"\32\2\2z{\5\32\16\2{|\7\24\2\2|}\5\f\7\2}~\7\23\2\2~\u0080\7\30\2\2\177"+
		"\u0081\5\f\7\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2"+
		"\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\7\31\2\2\u0085"+
		"\25\3\2\2\2\u0086\u0087\7\n\2\2\u0087\u0088\7\22\2\2\u0088\u0089\b\f\1"+
		"\2\u0089\u008a\5\32\16\2\u008a\u008b\b\f\1\2\u008b\u008c\7\32\2\2\u008c"+
		"\u008d\b\f\1\2\u008d\u008e\5\32\16\2\u008e\u008f\b\f\1\2\u008f\u0090\7"+
		"\23\2\2\u0090\u0091\b\f\1\2\u0091\u0092\7\30\2\2\u0092\u0094\b\f\1\2\u0093"+
		"\u0095\5\f\7\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\7\31\2\2\u0099"+
		"\u009a\b\f\1\2\u009a\27\3\2\2\2\u009b\u009c\7\13\2\2\u009c\u009d\7\22"+
		"\2\2\u009d\u009e\b\r\1\2\u009e\u009f\5\32\16\2\u009f\u00a0\b\r\1\2\u00a0"+
		"\u00a1\7\32\2\2\u00a1\u00a2\b\r\1\2\u00a2\u00a3\5\32\16\2\u00a3\u00a4"+
		"\b\r\1\2\u00a4\u00a5\7\23\2\2\u00a5\u00a6\b\r\1\2\u00a6\u00a7\7\30\2\2"+
		"\u00a7\u00a9\b\r\1\2\u00a8\u00aa\5\f\7\2\u00a9\u00a8\3\2\2\2\u00aa\u00ab"+
		"\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad"+
		"\u00ae\7\31\2\2\u00ae\u00ba\b\r\1\2\u00af\u00b0\7\f\2\2\u00b0\u00b1\7"+
		"\30\2\2\u00b1\u00b3\b\r\1\2\u00b2\u00b4\5\f\7\2\u00b3\u00b2\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00b8\7\31\2\2\u00b8\u00b9\b\r\1\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00af\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\31\3\2\2\2\u00bc\u00bd\5\34\17"+
		"\2\u00bd\u00be\5\36\20\2\u00be\33\3\2\2\2\u00bf\u00c0\5\"\22\2\u00c0\u00c1"+
		"\5 \21\2\u00c1\35\3\2\2\2\u00c2\u00c3\7\20\2\2\u00c3\u00c4\b\20\1\2\u00c4"+
		"\u00c5\5\34\17\2\u00c5\u00c6\5\36\20\2\u00c6\u00cd\3\2\2\2\u00c7\u00c8"+
		"\7\21\2\2\u00c8\u00c9\b\20\1\2\u00c9\u00ca\5\34\17\2\u00ca\u00cb\5\36"+
		"\20\2\u00cb\u00cd\3\2\2\2\u00cc\u00c2\3\2\2\2\u00cc\u00c7\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\37\3\2\2\2\u00ce\u00cf\7\16\2\2\u00cf\u00d0\b\21"+
		"\1\2\u00d0\u00d1\5\"\22\2\u00d1\u00d2\5 \21\2\u00d2\u00d9\3\2\2\2\u00d3"+
		"\u00d4\7\17\2\2\u00d4\u00d5\b\21\1\2\u00d5\u00d6\5\"\22\2\u00d6\u00d7"+
		"\5 \21\2\u00d7\u00d9\3\2\2\2\u00d8\u00ce\3\2\2\2\u00d8\u00d3\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9!\3\2\2\2\u00da\u00db\7\34\2\2\u00db\u00e7\b\22\1"+
		"\2\u00dc\u00dd\7\r\2\2\u00dd\u00e7\b\22\1\2\u00de\u00df\7\33\2\2\u00df"+
		"\u00e7\b\22\1\2\u00e0\u00e1\7\22\2\2\u00e1\u00e2\b\22\1\2\u00e2\u00e3"+
		"\5\32\16\2\u00e3\u00e4\7\23\2\2\u00e4\u00e5\b\22\1\2\u00e5\u00e7\3\2\2"+
		"\2\u00e6\u00da\3\2\2\2\u00e6\u00dc\3\2\2\2\u00e6\u00de\3\2\2\2\u00e6\u00e0"+
		"\3\2\2\2\u00e7#\3\2\2\2\22-\67@FN_hs\u0082\u0096\u00ab\u00b5\u00ba\u00cc"+
		"\u00d8\u00e6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}