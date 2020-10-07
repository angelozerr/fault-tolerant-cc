package com.redhat.beaverama.parser;

import com.redhat.beaverama.parser.ast.IfStatement;
import com.redhat.beaverama.parser.ast.SourceFileNode;

public class Parser {

	private Token token;
	
	private TokenStreamProviderInterface lexer;

	public SourceFileNode parseSourceFile(String fileContents, String uri) {		
		this.lexer = this.makeLexer(fileContents);
		this.reset();
		
		SourceFileNode sourceFileNode = new SourceFileNode();
		parseIfStatement(sourceFileNode);
		return sourceFileNode;
	}
	
	private TokenStreamProviderInterface makeLexer(String fileContents) {
		return new PhpTokenizer(fileContents);
	}

	/**
     * Retrieve the current token, and check that it's of the expected TokenKind.
     * If so, advance and return the token. Otherwise return a MissingToken for
     * the expected token.
     * @param int|int[] ...$kinds
     * @return Token
     */
    private Token eat(TokenKind...$kinds) {
        Token $token = this.token;
        /*if (\is_array($kinds[0])) {
            $kinds = $kinds[0];
        }*/
        for (TokenKind $kind : $kinds) {			
            if ($token.kind == $kind) {
                this.token = this.lexer.scanNextToken();
                return $token;
            }
        }
        // TODO include optional grouping for token kinds
        return new MissingToken($kinds[0], $token.fullStart);
    }
    
    private Token eat1(TokenKind...$kinds) {
        Token $token = this.token;
        for (TokenKind $kind : $kinds) {			
            if ($token.kind == $kind) {
                this.token = this.lexer.scanNextToken();
                return $token;
            }
        }
        // TODO include optional grouping for token kinds
        return new MissingToken($kinds[0], $token.fullStart);
    }
        
    private IfStatement parseIfStatement(Node $parent) {
		IfStatement $n = new IfStatement();
	    $n.ifKeyword = eat1(TokenKind.IfKeyword);
	    $n.openParen = eat1(TokenKind.OpenParenToken);
	    //$n.expression = parseExpression();
	    $n.closeParen = eat1(TokenKind.CloseParenToken);
	    //$n.statement = parseStatement();
	    $n.parent = $parent;
	    return $n;
	}
    
    private void reset() {
        this.advanceToken();

        // Stores the current parse context, which includes the current and enclosing lists.
        //this.currentParseContext = 0;
    }
    
    private void advanceToken() {
        this.token = this.lexer.scanNextToken();
    }
}
