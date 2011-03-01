package it.unibz.utils;

import it.unibz.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.*;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;


public class Tweetproducer {
    private static OAuthConsumer consumer=null;
    
    public static HttpParams getParams() {
      // Tweak further as needed for your app
      HttpParams params = new BasicHttpParams();
      // set this to false, or else you'll get an
      // Expectation Failed: error
      HttpProtocolParams.setUseExpectContinue(params, false);
      return params;
  }

  // Your actual method might look like this:
  public static void getTweets(User user) {
      try {
          OAuthConsumer consumer = Login(user);

          URL uu = new URL("http://twitter.com/statuses/friends_timeline.xml?count=200");
       // create an HTTP request to a protected resource
          HttpURLConnection request =  (HttpURLConnection)uu.openConnection();
          request.setRequestMethod("GET");
          request.setDoOutput(true);
          request.setReadTimeout(10000);
                    
          //request.connect();
          // sign the request
          consumer.sign(request);
          BufferedReader rd  = new BufferedReader(new InputStreamReader(request.getInputStream()));
          StringBuilder sb = new StringBuilder();
        
          String line;
					while ((line = rd.readLine()) != null)
          {
              sb.append(line + '\n');
          }
        
          System.out.println(sb.toString());
          // send the request

      } catch (Exception e) {
          // do some proper exception handling here
          e.printStackTrace();
      }
  }

	private static OAuthConsumer Login(User user) {
		// Create a new consumer using the commons implementation
		if(consumer==null)
		{ consumer = new DefaultOAuthConsumer(
		  "Kgrg9GlpGU8yoza6u1KqQQ",
		  "bZUrgRsSWu9JiXMsE9mFFT5pcosZzPv4vKca7nhZsE");
		consumer.setTokenWithSecret(user.getUserAccessToken(),
		        user.getUserTokenSecret());}
		
		return consumer;
	}

    

		public static void getFollowing(User u) {
			// TODO Auto-generated method stub
			//http://api.twitter.com/version/notifications/follow
			try {
        OAuthConsumer consumer = Login(u);

        URL uu = new URL("http://api.twitter.com/1/followers/ids.xml");
     // create an HTTP request to a protected resource
        HttpURLConnection request =  (HttpURLConnection)uu.openConnection();
        request.setRequestMethod("GET");
        request.setDoOutput(true);
        request.setReadTimeout(10000);
                  
        //request.connect();
        // sign the request
        consumer.sign(request);
        BufferedReader rd  = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
      
        String line;
				while ((line = rd.readLine()) != null)
        {
            sb.append(line + '\n');
        }
      
        System.out.println(sb.toString());
        // send the request

    } catch (Exception e) {
        // do some proper exception handling here
        e.printStackTrace();
    }
		}
		
		public static void getNotifications(User u) {
			// TODO Auto-generated method stub
			//http://api.twitter.com/version/notifications/follow
			try {
        OAuthConsumer consumer = Login(u);

        URL uu = new URL("http://api.twitter.com/1/notifications/follow.xml");
     // create an HTTP request to a protected resource
        HttpURLConnection request =  (HttpURLConnection)uu.openConnection();
        request.setRequestMethod("GET");
        request.setDoOutput(true);
        request.setReadTimeout(10000);
                  
        //request.connect();
        // sign the request
        consumer.sign(request);
        BufferedReader rd  = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
      
        String line;
				while ((line = rd.readLine()) != null)
        {
            sb.append(line + '\n');
        }
      
        System.out.println(sb.toString());
        // send the request

    } catch (Exception e) {
        // do some proper exception handling here
        e.printStackTrace();
    }
		}

		public static void unfollowUser(User u,int unfollowerid) {
			// TODO Auto-generated method stub
			//http://api.twitter.com/version/notifications/follow
			try {
        OAuthConsumer consumer = Login(u);

        URL uu = new URL("http://api.twitter.com/version/friendships/destroy.xml?user_id="+unfollowerid);
     // create an HTTP request to a protected resource
        HttpURLConnection request =  (HttpURLConnection)uu.openConnection();
        request.setRequestMethod("GET");
        request.setDoOutput(true);
        request.setReadTimeout(10000);
                  
        //request.connect();
        // sign the request
        consumer.sign(request);
        BufferedReader rd  = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
      
        String line;
				while ((line = rd.readLine()) != null)
        {
            sb.append(line + '\n');
        }
      
        System.out.println(sb.toString());
        // send the request

    } catch (Exception e) {
        // do some proper exception handling here
        e.printStackTrace();
    }
		}
		public static void followUser(User u,int followerid) {
			// TODO Auto-generated method stub
			//http://api.twitter.com/version/notifications/follow
			try {
        OAuthConsumer consumer = Login(u);

        URL uu = new URL("http://api.twitter.com/version/friendships/create.xml?user_id="+followerid);
     // create an HTTP request to a protected resource
        HttpURLConnection request =  (HttpURLConnection)uu.openConnection();
        request.setRequestMethod("GET");
        request.setDoOutput(true);
        request.setReadTimeout(10000);
                  
        //request.connect();
        // sign the request
        consumer.sign(request);
        BufferedReader rd  = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
      
        String line;
				while ((line = rd.readLine()) != null)
        {
            sb.append(line + '\n');
        }
      
        System.out.println(sb.toString());
        // send the request

    } catch (Exception e) {
        // do some proper exception handling here
        e.printStackTrace();
    }
		}
}

