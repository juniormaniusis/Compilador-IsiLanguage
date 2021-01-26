# Compilador-IsiLanguage

Fork do projeto inicial do projeto de compiladores do professor Isidro.

Implementamos um compilador usando Java + ANTLR4 capaz de compilar a IsiLanguage (Uma linguagem com gramática definida pelo professor) em Java.

[YouTube] (https://www.youtube.com/watch?v=CPrPTaqif98&feature=youtu.be) - Apresentação do Projeto

Além da gramática básica proposta pelo professor, incrementamos a linguagem trazendo novas possibilidades
- Laços de repetição.
- Estruturas de decisão.
- Analise Semantica de Tipos em empressões comparativas.
- Check de variaveis declaradas e não utilizadas.
- Check de uso de variável antes de ser atribuída.
- Operações de Entrada e Saída.
- Syntax Sugar para operações de forma [ x = x OPERADOR y] onde operador pode ser "+", "-", "*", "/".
- Análise semântica de aplicação de operadores.

A gramática dada pelo professor não estava em LL(1). desta forma, fizemos algumas adaptações para que fossem eliminadas as recursões a esquerda.


A gramática base da IsiLanguage
Observe o conjunto de regras gramaticais da IsiLanguage
abaixo:
Prog —> programa Declara Bloco
 fimprog.
Declara —> declare Id (, Id)* .
Bloco —> (Cmd. )+
Cmd —> CmdLeitura | CmdEscrita |
 CmdExpr | CmdIf
CmdLeitura —> leia ( Id )
CmdEscrita —> escreva( Texto | Id )
CmdIf —> se ‘(‘ Expr Op_rel Expr ‘)’
 entao ‘{‘ Cmd+ ‘}’
 (senao ‘{‘ Cmd+ ‘}’ )?
CmdExpr —> Id := Expr
Op_rel —> ‘<’ | ‘>’ | “<=” | “>=” |
 “!=” | “==”
Expr —> Expr + Termo | Expr – Termo |
 Termo
Termo —> Termo * Fator |
 Termo / Fator | Fator
Fator —> Num | Id | ( Expr )
Texto —> “(0..9 | a..z | A..Z | ‘ ‘)+ ”
Num —> (0..9)+
Id —> (a..z | A..Z)
 (a..z | A..Z | 0..9)* 
