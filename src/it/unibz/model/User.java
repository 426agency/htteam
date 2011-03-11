package it.unibz.model;

public class User {
	
	//String username=null;
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

//	public String getUserAccessToken() {
//		// TODO Auto-generated method stub
//		return "241950624-tbJdWcp4Bbl7HZ3YaaS4TGB1vDQ7kM2T1VtDrTDa";
//	}
//
//	public String getUserTokenSecret() {
//		// TODO Auto-generated method stub
//		return "4d6CbEqw0aZQVDGam9es7xACDfqbrQqy1teXe0Fec";
//	}

}
