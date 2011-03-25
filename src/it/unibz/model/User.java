package it.unibz.model;

/**
 * Represents the currently logged in user having the two keys
 *
 */
public class User {
	
	String accesstoken=null;
	String tokensecret=null;

	public User(String accesstoken, String tokensecret) {
		super();
		this.accesstoken = accesstoken;
		this.tokensecret = tokensecret;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public String getTokensecret() {
		return tokensecret;
	}
	
	public void print(){
		System.out.println(accesstoken+" "+tokensecret);
	}

}
