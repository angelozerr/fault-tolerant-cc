package com.redhat.beaverama.parser;

public interface TokenStreamProviderInterface {

	public Token scanNextToken();

    public int getCurrentPosition();

    public void setCurrentPosition(int $pos);

    public int getEndOfFilePosition();

    //public function getTokensArray() : array;
}
