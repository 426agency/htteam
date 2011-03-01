package it.unibz.view;

import it.unibz.model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while(true){
		// TODO Auto-generated method stub
System.out.println("Welcome to twitter");
System.out.println("What do you wanna do: 1.Login 2.Logout 3.Exit");
//open up standard input
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

String action = null;
try {
   action = br.readLine();
} catch (IOException ioe) {
   System.out.println("IO error trying to read action!");
   System.exit(1);
}
if(action.equals("1")){
	User loggeduser = OAuthProducer.getOAuthAccessToken();
	if(loggeduser==null){
		//Start pin procedure
		try {
			loggeduser=OAuthProducer.getFirstTimeAccessCode();
		} catch (Exception e) {
			
		}
		if(loggeduser!=null){
			System.out.println("Successfully logged in");
			
		}
	}
	else{
		
			System.out.println("Successfully logged in");
	}
}
else{
	if(action.equals("2")){
		OAuthProducer.removeAccess();
		System.out.println("Successfully logged out");

	}else{
	System.exit(0);
}}
		}

	}

}
