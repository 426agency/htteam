package it.unibz.exception;

public class TweetException extends Exception{

	private static final long serialVersionUID = 7577136074623618615L;

	public TweetException(Exception e) {
		super(e);
	}

}
