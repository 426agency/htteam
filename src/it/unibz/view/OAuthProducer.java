package it.unibz.view;

import it.unibz.model.User;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;



public class OAuthProducer {

	private static OAuthProvider provider =null;
	
    public static User getFirstTimeAccessCode() throws Exception{

    	OAuthConsumer consumer = new DefaultOAuthConsumer(
				"Kgrg9GlpGU8yoza6u1KqQQ",
		"bZUrgRsSWu9JiXMsE9mFFT5pcosZzPv4vKca7nhZsE");

provider= new DefaultOAuthProvider("http://twitter.com/oauth/request_token",
        "http://twitter.com/oauth/access_token", "http://twitter.com/oauth/authorize");

        System.out.println("Fetching request token from Twitter...");

     // we do not support callbacks, thus pass OOB
        String authUrl = provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);

        System.out.println("Request token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());

        System.out.println("Now visit:\n" + authUrl
                + "\n... and grant this app authorization");
        System.out.println("Enter the PIN code and hit ENTER when you're done:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pin = br.readLine();

        System.out.println("Fetching access token from Twitter...");

       
        provider.retrieveAccessToken(consumer, pin);

        System.out.println("Access token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());

        URL url = new URL("http://twitter.com/statuses/mentions.xml");
        HttpURLConnection request = (HttpURLConnection) url.openConnection();

        consumer.sign(request);

        System.out.println("Sending request to Twitter...");
        request.connect();

        System.out.println("Response: " + request.getResponseCode() + " "
                + request.getResponseMessage());
        
        //Store Keys in properties
        Properties properties = new Properties();
     // Write properties file.
        try {
        	properties.setProperty("access", consumer.getToken());
        	properties.setProperty("secret", consumer.getConsumerSecret());
            properties.store(new FileOutputStream("twitterclient.properties"), null);
        } catch (IOException e) {
        }
        return new User(consumer.getToken(),consumer.getTokenSecret());
    }

    /**
     * Method retrieves the accesstoken from the properties file
     * @return 
     */
	public static User getOAuthAccessToken() {
		//Read properties file.
		User u =null;
		String accesskey=null;
		String secretkey=null;
		Properties properties = new Properties();
		try {
		    properties.load(new FileInputStream("twitterclient.properties"));
		    accesskey = properties.getProperty("access");
		    secretkey = properties.getProperty("secret");
		    if(accesskey!=null&&secretkey!=null)
		    u= new User(accesskey, secretkey);
		} catch (IOException e) {
		}
		return u;
		
	}

	public static void removeAccess() {
		Properties properties = new Properties();
		try {
		    properties.load(new FileInputStream("twitterclient.properties"));
		    properties.remove("access");
		    properties.remove("secret");
            properties.store(new FileOutputStream("twitterclient.properties"), null);

		}catch (IOException e) {
		}
	} 
}
