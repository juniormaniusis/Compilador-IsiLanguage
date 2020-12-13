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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, STRING=11, OPMUL=12, OPDIV=13, OPSUM=14, OPSUB=15, AP=16, FP=17, 
		SC=18, OP=19, ATTR=20, VIR=21, ACH=22, FCH=23, OPREL=24, ID=25, NUMBER=26, 
		WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "STRING", "OPMUL", "OPDIV", "OPSUM", "OPSUB", "AP", "FP", "SC", 
			"OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", "WS"
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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00c3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7\f\u0081\n\f\f\f\16\f"+
		"\u0084\13\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3"+
		"\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00aa\n\31\3\32"+
		"\3\32\7\32\u00ae\n\32\f\32\16\32\u00b1\13\32\3\33\6\33\u00b4\n\33\r\33"+
		"\16\33\u00b5\3\33\3\33\6\33\u00ba\n\33\r\33\16\33\u00bb\5\33\u00be\n\33"+
		"\3\34\3\34\3\34\3\34\3\u0082\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\35\3\2\b\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\"+
		"c|\3\2\62;\5\2\13\f\17\17\"\"\2\u00cc\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\39\3\2\2\2\5B\3\2\2\2\7K\3\2\2\2\tR\3\2\2\2\13X"+
		"\3\2\2\2\r]\3\2\2\2\17e\3\2\2\2\21j\3\2\2\2\23s\3\2\2\2\25v\3\2\2\2\27"+
		"|\3\2\2\2\31\u0087\3\2\2\2\33\u0089\3\2\2\2\35\u008b\3\2\2\2\37\u008d"+
		"\3\2\2\2!\u008f\3\2\2\2#\u0091\3\2\2\2%\u0093\3\2\2\2\'\u0095\3\2\2\2"+
		")\u0097\3\2\2\2+\u009a\3\2\2\2-\u009c\3\2\2\2/\u009e\3\2\2\2\61\u00a9"+
		"\3\2\2\2\63\u00ab\3\2\2\2\65\u00b3\3\2\2\2\67\u00bf\3\2\2\29:\7r\2\2:"+
		";\7t\2\2;<\7q\2\2<=\7i\2\2=>\7t\2\2>?\7c\2\2?@\7o\2\2@A\7c\2\2A\4\3\2"+
		"\2\2BC\7h\2\2CD\7k\2\2DE\7o\2\2EF\7r\2\2FG\7t\2\2GH\7q\2\2HI\7i\2\2IJ"+
		"\7\60\2\2J\6\3\2\2\2KL\7p\2\2LM\7w\2\2MN\7o\2\2NO\7g\2\2OP\7t\2\2PQ\7"+
		"q\2\2Q\b\3\2\2\2RS\7v\2\2ST\7g\2\2TU\7z\2\2UV\7v\2\2VW\7q\2\2W\n\3\2\2"+
		"\2XY\7n\2\2YZ\7g\2\2Z[\7k\2\2[\\\7c\2\2\\\f\3\2\2\2]^\7g\2\2^_\7u\2\2"+
		"_`\7e\2\2`a\7t\2\2ab\7g\2\2bc\7x\2\2cd\7c\2\2d\16\3\2\2\2ef\7r\2\2fg\7"+
		"c\2\2gh\7t\2\2hi\7c\2\2i\20\3\2\2\2jk\7g\2\2kl\7p\2\2lm\7s\2\2mn\7w\2"+
		"\2no\7c\2\2op\7p\2\2pq\7v\2\2qr\7q\2\2r\22\3\2\2\2st\7u\2\2tu\7g\2\2u"+
		"\24\3\2\2\2vw\7u\2\2wx\7g\2\2xy\7p\2\2yz\7c\2\2z{\7q\2\2{\26\3\2\2\2|"+
		"\u0082\7$\2\2}~\7^\2\2~\u0081\7$\2\2\177\u0081\13\2\2\2\u0080}\3\2\2\2"+
		"\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0083\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\7$\2\2\u0086"+
		"\30\3\2\2\2\u0087\u0088\7,\2\2\u0088\32\3\2\2\2\u0089\u008a\7\61\2\2\u008a"+
		"\34\3\2\2\2\u008b\u008c\7-\2\2\u008c\36\3\2\2\2\u008d\u008e\7/\2\2\u008e"+
		" \3\2\2\2\u008f\u0090\7*\2\2\u0090\"\3\2\2\2\u0091\u0092\7+\2\2\u0092"+
		"$\3\2\2\2\u0093\u0094\7\60\2\2\u0094&\3\2\2\2\u0095\u0096\t\2\2\2\u0096"+
		"(\3\2\2\2\u0097\u0098\7<\2\2\u0098\u0099\7?\2\2\u0099*\3\2\2\2\u009a\u009b"+
		"\7.\2\2\u009b,\3\2\2\2\u009c\u009d\7}\2\2\u009d.\3\2\2\2\u009e\u009f\7"+
		"\177\2\2\u009f\60\3\2\2\2\u00a0\u00aa\t\3\2\2\u00a1\u00a2\7@\2\2\u00a2"+
		"\u00aa\7?\2\2\u00a3\u00a4\7>\2\2\u00a4\u00aa\7?\2\2\u00a5\u00a6\7?\2\2"+
		"\u00a6\u00aa\7?\2\2\u00a7\u00a8\7#\2\2\u00a8\u00aa\7?\2\2\u00a9\u00a0"+
		"\3\2\2\2\u00a9\u00a1\3\2\2\2\u00a9\u00a3\3\2\2\2\u00a9\u00a5\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00aa\62\3\2\2\2\u00ab\u00af\t\4\2\2\u00ac\u00ae\t\5\2"+
		"\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0"+
		"\3\2\2\2\u00b0\64\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b4\t\6\2\2\u00b3"+
		"\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\u00bd\3\2\2\2\u00b7\u00b9\7\60\2\2\u00b8\u00ba\t\6\2\2\u00b9"+
		"\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2"+
		"\2\2\u00bc\u00be\3\2\2\2\u00bd\u00b7\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\66\3\2\2\2\u00bf\u00c0\t\7\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\b\34\2"+
		"\2\u00c28\3\2\2\2\13\2\u0080\u0082\u00a9\u00ad\u00af\u00b5\u00bb\u00bd"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}