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
		RULE_termo = 13, RULE_fator = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
			"cmdattrib", "cmdpara", "cmdenquanto", "cmdselecao", "expr", "termo", 
			"fator"
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
			setState(30);
			match(T__0);
			setState(31);
			decl();
			setState(32);
			bloco();
			setState(33);
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
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36);
				declaravar();
				}
				}
				setState(39); 
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
			setState(41);
			tipo();
			setState(42);
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
			                    
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(44);
				match(VIR);
				setState(45);
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
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
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
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				match(T__2);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
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
			          
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61);
				cmd();
				}
				}
				setState(64); 
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
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				cmdleitura();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				cmdattrib();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				cmdselecao();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				cmdenquanto();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
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
			setState(74);
			match(T__4);
			setState(75);
			match(AP);
			setState(76);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                     	  _readID = _input.LT(-1).getText();
			                        
			setState(78);
			match(FP);
			setState(79);
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
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
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
			setState(82);
			match(T__5);
			setState(83);
			match(AP);
			setState(84);
			match(ID);
			 verificaID(_input.LT(-1).getText());
				                  _writeID = _input.LT(-1).getText();
			                     
			setState(86);
			match(FP);
			setState(87);
			match(SC);

			               	  CommandEscrita cmd = new CommandEscrita(_writeID);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(ID);
			 verificaID(_input.LT(-1).getText());
			                    _exprID = _input.LT(-1).getText();
			                   
			setState(92);
			match(ATTR);
			 resetExpr(); 
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AP:
			case ID:
			case NUMBER:
				{
				{
				setState(94);
				expr(0);
				setState(95);
				match(SC);

										CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
										stack.peek().add(cmd);
									
				}
				}
				break;
			case STRING:
				{
				{
				setState(98);
				match(STRING);

										System.out.println("Detectei uma string");
										String str = _input.LT(-1).getText();
										System.out.println(str);
										(_exprID);
										CommandAtribuicao cmd = new CommandAtribuicao(_exprID, str);
										stack.peek().add(cmd);
									
				setState(100);
				match(SC);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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
			setState(103);
			match(T__6);
			setState(104);
			match(AP);
			setState(105);
			cmdattrib();
			setState(106);
			expr(0);
			setState(107);
			match(OPREL);
			setState(108);
			expr(0);
			setState(109);
			match(SC);
			setState(110);
			cmd();
			setState(111);
			match(FP);
			setState(112);
			match(ACH);
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113);
				cmd();
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(118);
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
			setState(120);
			match(T__7);
			setState(121);
			match(AP);

										resetExpr();
								
			setState(123);
			expr(0);

										stackExprDecision.push(_exprContent);
								
			setState(125);
			match(OPREL);
			 
										String op = _input.LT(-1).getText();
										String atual = stackExprDecision.pop();
										String novo = atual + op;
										stackExprDecision.push(novo);
										resetExpr();
								
			setState(127);
			expr(0);

										atual = stackExprDecision.pop();
										novo = atual + _exprContent;
										stackExprDecision.push(novo);
								
			setState(129);
			match(FP);
			setState(130);
			match(ACH);
			 
										curThread = new ArrayList<AbstractCommand>(); 
										stack.push(curThread);
			                    
			setState(133); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(132);
				cmd();
				}
				}
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(137);
			match(FCH);

			                       innerCommands = stack.pop();	
								   System.out.println(innerCommands);
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
			setState(140);
			match(T__8);
			setState(141);
			match(AP);

										resetExpr();
								
			setState(143);
			expr(0);

										stackExprDecision.push(_exprContent);
								
			setState(145);
			match(OPREL);
			 
										String op = _input.LT(-1).getText();
										String atual = stackExprDecision.pop();
										String novo = atual + op;
										stackExprDecision.push(novo);
										resetExpr();
								
			setState(147);
			expr(0);

										atual = stackExprDecision.pop();
										novo = atual + _exprContent;
										stackExprDecision.push(novo);
										resetExpr();
								
			setState(149);
			match(FP);
			setState(150);
			match(ACH);
			 
									// cria uma lista de comandos
									curThread = new ArrayList<AbstractCommand>(); 
			                      	stack.push(curThread);
			                    
			setState(153); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(152);
				cmd();
				}
				}
				setState(155); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
			setState(157);
			match(FCH);

									// pega a lista de comandos
			                       listaTrue = stack.pop();	
								   // cria um comando de decisao com essa lista de comandos
								   String expreDecision = stackExprDecision.pop();
								   CommandDecisao cmdSE = new CommandDecisao(expreDecision, listaTrue, EMPTY_COMMAND_LIST);
			                   	   stack.peek().add(cmdSE);
			                    
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(159);
				match(T__9);
				setState(160);
				match(ACH);

				                   	 	curThread = new ArrayList<AbstractCommand>();
				                   	 	stack.push(curThread);
				                   	 
				{
				setState(163); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(162);
					cmd();
					}
					}
					setState(165); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << ID))) != 0) );
				}
				setState(167);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OPSUM() { return getToken(IsiLangParser.OPSUM, 0); }
		public TerminalNode OPSUB() { return getToken(IsiLangParser.OPSUB, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(173);
			termo(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(185);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(183);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(175);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(176);
						match(OPSUM);
						 _exprContent += _input.LT(-1).getText();
						setState(178);
						termo(0);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(179);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(180);
						match(OPSUB);
						 _exprContent += _input.LT(-1).getText();
						setState(182);
						termo(0);
						}
						break;
					}
					} 
				}
				setState(187);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public TerminalNode OPMUL() { return getToken(IsiLangParser.OPMUL, 0); }
		public TerminalNode OPDIV() { return getToken(IsiLangParser.OPDIV, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
	}

	public final TermoContext termo() throws RecognitionException {
		return termo(0);
	}

	private TermoContext termo(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermoContext _localctx = new TermoContext(_ctx, _parentState);
		TermoContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_termo, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(189);
			fator();
			}
			_ctx.stop = _input.LT(-1);
			setState(201);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(199);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new TermoContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_termo);
						setState(191);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(192);
						match(OPMUL);
						 _exprContent += _input.LT(-1).getText();
						setState(194);
						fator();
						}
						break;
					case 2:
						{
						_localctx = new TermoContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_termo);
						setState(195);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(196);
						match(OPDIV);
						 _exprContent += _input.LT(-1).getText();
						setState(198);
						fator();
						}
						break;
					}
					} 
				}
				setState(203);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FatorContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
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
		enterRule(_localctx, 28, RULE_fator);
		try {
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				match(NUMBER);
				 _exprContent += _input.LT(-1).getText();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				match(ID);
				 _exprContent += _input.LT(-1).getText();
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 3);
				{
				setState(208);
				match(AP);
				 _exprContent += _input.LT(-1).getText();
				setState(210);
				expr(0);
				setState(211);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 13:
			return termo_sempred((TermoContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean termo_sempred(TermoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35\u00db\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\6\3(\n\3\r\3\16\3)\3\4\3\4\3\4\3\4\3\4\3\4\7\4\62\n\4\f\4\16"+
		"\4\65\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\6\6A\n\6\r\6\16\6"+
		"B\3\7\3\7\3\7\3\7\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\5\nh\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13"+
		"u\n\13\r\13\16\13v\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\6\f\u0088\n\f\r\f\16\f\u0089\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u009c\n\r\r\r\16\r\u009d\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\6\r\u00a6\n\r\r\r\16\r\u00a7\3\r\3\r\3\r\5\r\u00ad"+
		"\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00ba"+
		"\n\16\f\16\16\16\u00bd\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\7\17\u00ca\n\17\f\17\16\17\u00cd\13\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00d9\n\20\3\20\2\4\32\34\21\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\2\u00e0\2 \3\2\2\2\4\'\3\2\2"+
		"\2\6+\3\2\2\2\b<\3\2\2\2\n>\3\2\2\2\fJ\3\2\2\2\16L\3\2\2\2\20T\3\2\2\2"+
		"\22\\\3\2\2\2\24i\3\2\2\2\26z\3\2\2\2\30\u008e\3\2\2\2\32\u00ae\3\2\2"+
		"\2\34\u00be\3\2\2\2\36\u00d8\3\2\2\2 !\7\3\2\2!\"\5\4\3\2\"#\5\n\6\2#"+
		"$\7\4\2\2$%\b\2\1\2%\3\3\2\2\2&(\5\6\4\2\'&\3\2\2\2()\3\2\2\2)\'\3\2\2"+
		"\2)*\3\2\2\2*\5\3\2\2\2+,\5\b\5\2,-\7\33\2\2-\63\b\4\1\2./\7\27\2\2/\60"+
		"\7\33\2\2\60\62\b\4\1\2\61.\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64"+
		"\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\7\24\2\2\67\7\3\2\2\289\7\5"+
		"\2\29=\b\5\1\2:;\7\6\2\2;=\b\5\1\2<8\3\2\2\2<:\3\2\2\2=\t\3\2\2\2>@\b"+
		"\6\1\2?A\5\f\7\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\13\3\2\2\2D"+
		"K\5\16\b\2EK\5\20\t\2FK\5\22\n\2GK\5\30\r\2HK\5\26\f\2IK\5\24\13\2JD\3"+
		"\2\2\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\r\3\2\2\2LM"+
		"\7\7\2\2MN\7\22\2\2NO\7\33\2\2OP\b\b\1\2PQ\7\23\2\2QR\7\24\2\2RS\b\b\1"+
		"\2S\17\3\2\2\2TU\7\b\2\2UV\7\22\2\2VW\7\33\2\2WX\b\t\1\2XY\7\23\2\2YZ"+
		"\7\24\2\2Z[\b\t\1\2[\21\3\2\2\2\\]\7\33\2\2]^\b\n\1\2^_\7\26\2\2_g\b\n"+
		"\1\2`a\5\32\16\2ab\7\24\2\2bc\b\n\1\2ch\3\2\2\2de\7\r\2\2ef\b\n\1\2fh"+
		"\7\24\2\2g`\3\2\2\2gd\3\2\2\2h\23\3\2\2\2ij\7\t\2\2jk\7\22\2\2kl\5\22"+
		"\n\2lm\5\32\16\2mn\7\32\2\2no\5\32\16\2op\7\24\2\2pq\5\f\7\2qr\7\23\2"+
		"\2rt\7\30\2\2su\5\f\7\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2wx\3\2"+
		"\2\2xy\7\31\2\2y\25\3\2\2\2z{\7\n\2\2{|\7\22\2\2|}\b\f\1\2}~\5\32\16\2"+
		"~\177\b\f\1\2\177\u0080\7\32\2\2\u0080\u0081\b\f\1\2\u0081\u0082\5\32"+
		"\16\2\u0082\u0083\b\f\1\2\u0083\u0084\7\23\2\2\u0084\u0085\7\30\2\2\u0085"+
		"\u0087\b\f\1\2\u0086\u0088\5\f\7\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\7\31\2\2\u008c\u008d\b\f\1\2\u008d\27\3\2\2\2\u008e\u008f\7\13"+
		"\2\2\u008f\u0090\7\22\2\2\u0090\u0091\b\r\1\2\u0091\u0092\5\32\16\2\u0092"+
		"\u0093\b\r\1\2\u0093\u0094\7\32\2\2\u0094\u0095\b\r\1\2\u0095\u0096\5"+
		"\32\16\2\u0096\u0097\b\r\1\2\u0097\u0098\7\23\2\2\u0098\u0099\7\30\2\2"+
		"\u0099\u009b\b\r\1\2\u009a\u009c\5\f\7\2\u009b\u009a\3\2\2\2\u009c\u009d"+
		"\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a0\7\31\2\2\u00a0\u00ac\b\r\1\2\u00a1\u00a2\7\f\2\2\u00a2\u00a3\7"+
		"\30\2\2\u00a3\u00a5\b\r\1\2\u00a4\u00a6\5\f\7\2\u00a5\u00a4\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2"+
		"\2\2\u00a9\u00aa\7\31\2\2\u00aa\u00ab\b\r\1\2\u00ab\u00ad\3\2\2\2\u00ac"+
		"\u00a1\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\31\3\2\2\2\u00ae\u00af\b\16\1"+
		"\2\u00af\u00b0\5\34\17\2\u00b0\u00bb\3\2\2\2\u00b1\u00b2\f\5\2\2\u00b2"+
		"\u00b3\7\20\2\2\u00b3\u00b4\b\16\1\2\u00b4\u00ba\5\34\17\2\u00b5\u00b6"+
		"\f\4\2\2\u00b6\u00b7\7\21\2\2\u00b7\u00b8\b\16\1\2\u00b8\u00ba\5\34\17"+
		"\2\u00b9\u00b1\3\2\2\2\u00b9\u00b5\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9"+
		"\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\33\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be"+
		"\u00bf\b\17\1\2\u00bf\u00c0\5\36\20\2\u00c0\u00cb\3\2\2\2\u00c1\u00c2"+
		"\f\5\2\2\u00c2\u00c3\7\16\2\2\u00c3\u00c4\b\17\1\2\u00c4\u00ca\5\36\20"+
		"\2\u00c5\u00c6\f\4\2\2\u00c6\u00c7\7\17\2\2\u00c7\u00c8\b\17\1\2\u00c8"+
		"\u00ca\5\36\20\2\u00c9\u00c1\3\2\2\2\u00c9\u00c5\3\2\2\2\u00ca\u00cd\3"+
		"\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\35\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00ce\u00cf\7\34\2\2\u00cf\u00d9\b\20\1\2\u00d0\u00d1\7"+
		"\33\2\2\u00d1\u00d9\b\20\1\2\u00d2\u00d3\7\22\2\2\u00d3\u00d4\b\20\1\2"+
		"\u00d4\u00d5\5\32\16\2\u00d5\u00d6\7\23\2\2\u00d6\u00d7\b\20\1\2\u00d7"+
		"\u00d9\3\2\2\2\u00d8\u00ce\3\2\2\2\u00d8\u00d0\3\2\2\2\u00d8\u00d2\3\2"+
		"\2\2\u00d9\37\3\2\2\2\22)\63<BJgv\u0089\u009d\u00a7\u00ac\u00b9\u00bb"+
		"\u00c9\u00cb\u00d8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}