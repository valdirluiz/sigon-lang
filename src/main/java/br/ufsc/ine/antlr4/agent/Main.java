package br.ufsc.ine.antlr4.agent;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import agent.AgentLexer;
import agent.AgentParser;

public class Main {

	private static final String TEST = "beliefs(prop): a. a :- b.";

	public static void main(String[] args) {

		String filename = getLastArgument(args);

		CharStream stream = new ANTLRInputStream(TEST);
		AgentLexer lexer = new AgentLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		AgentParser parser = new AgentParser(tokens);
		ParseTree tree = parser.agent();

		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new AgentWalker(), tree);

	}

	private static String getLastArgument(String[] args) {
		return args[args.length - 1];
	}

}
