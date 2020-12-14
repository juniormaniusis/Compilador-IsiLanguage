package br.com.professorisidro.isilanguage.main;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
import br.com.professorisidro.isilanguage.parser.IsiLangLexer;
import br.com.professorisidro.isilanguage.parser.IsiLangParser;

/* esta é a classe que é responsável por criar a interação com o usuário
 * instanciando nosso parser e apontando para o arquivo fonte
 * 
 * Arquivo fonte: extensao .isi
 * 
 */
public class MainClass {
	public static void main(String[] args) {
		try {
			Instant start = Instant.now();

			IsiLangLexer lexer;
			IsiLangParser parser;
			
			String fileName = "input.isi";
			if (args.length > 0) {
				fileName = args[0];
			}
			// leio o arquivo "input.isi" e isso é entrada para o Analisador Lexico
			lexer = new IsiLangLexer(CharStreams.fromFileName(fileName));
			
			// crio um "fluxo de tokens" para passar para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// crio meu parser a partir desse tokenStream
			parser = new IsiLangParser(tokenStream);
			
			parser.prog();

			ArrayList<String> warnings = parser.getWarnings();
			if (warnings.size() > 0) {
				System.out.println(repeatChar('#', 34) + " WARNINGS  " + repeatChar('#', 34));
				for (String warning : warnings) {
					MessagesHelper.warning(warning);
				}
				System.out.println(repeatChar('#', 80));
			}
			
			MessagesHelper.success("IsiCompilado com Sucesso");
			
			
			//parser.exibeComandos();
			
			parser.generateCode();
			
			Instant end = Instant.now();			
			MessagesHelper.info("tempo decorrido: " + Duration.between(start, end).toMillis() + "ms");

		}
		catch(IsiSemanticException ex) {
			System.err.println("Semantic error - "+ex.getMessage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("ERROR "+ex.getMessage());
		}
		
	}

	private static String repeatChar(char c, int times) {
		String s = "";
		for (int i = 0; i < times; i++) {
			s += c;
		}
		return s;
	}

}
