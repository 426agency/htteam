package it.unibz.utils;

import it.unibz.model.TweetUser;
import it.unibz.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import oauth.signpost.*;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;


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

	private static String useService(User user, String url){
		String line = null;
		try {
			OAuthConsumer consumer = Login(user);
			URL uu = new URL(url);
			// create an HTTP request to a protected resource
			HttpURLConnection request =  (HttpURLConnection)uu.openConnection();
			request.setRequestMethod("GET");
			request.setDoOutput(true);
			request.setReadTimeout(10000);

			// sign the request
			consumer.sign(request);
			BufferedReader rd  = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();

			while ((line = rd.readLine()) != null){
				sb.append(line + '\n');
			}
			line = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line.toString();
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
			while ((line = rd.readLine()) != null){
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
		{ consumer = new DefaultOAuthConsumer("Kgrg9GlpGU8yoza6u1KqQQ","bZUrgRsSWu9JiXMsE9mFFT5pcosZzPv4vKca7nhZsE");
		consumer.setTokenWithSecret(user.getUserAccessToken(),
				user.getUserTokenSecret());}

		return consumer;
	}



	public static ArrayList<TweetUser> getFollowing(User user) {
		//http://api.twitter.com/version/notifications/follow
		String followersId = useService(user, "http://api.twitter.com/1/followers/ids.xml");
		Document dom = stringToDom(followersId);
		NodeList nodeList = dom.getElementsByTagName("id");
		TweetUser u = new TweetUser();
		ArrayList<TweetUser> tweetUserList = new ArrayList<TweetUser>();
		// foreach id we get the value
		for (int i = 0; i < nodeList.getLength(); i++) {
			u.setId(getXmlElement(dom,i,"id")); 
			u.setName(getFollowerInfo(user,u.getId(),"name"));
			u.setScreenName(getFollowerInfo(user,u.getId(),"screen_name"));
			tweetUserList.add(u);
			u.print();
		}
		return tweetUserList;
	}

	private static String getXmlElement(Document d, int pos, String tag){
		NodeList nodeList = d.getElementsByTagName(tag);
		Element element = (Element) nodeList.item(pos);
		NodeList idNodeList = element.getChildNodes();
		if (idNodeList.item(0)==null)
			return "na";
		else return idNodeList.item(0).getNodeValue().toString();
	}
	
	private static Document stringToDom(String s){
		Document d = null;
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			d = builder.parse(new InputSource(new StringReader(s)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	private static String getFollowerInfo(User u, String id, String tag){
		String userInfo = useService(u, "http://api.twitter.com/1/users/show.xml?user_id="+id);
		Document dom = stringToDom(userInfo);
		return getXmlElement(dom,0,tag);
	}

	public static void getNotifications(User u) {
		//http://api.twitter.com/version/notifications/follow
		
		String notifications = useService(u, "http://api.twitter.com/1/notifications/follow.xml");
		System.out.println(notifications);
	}

	public static void unfollowUser(User u,int unfollowerid) {
		//http://api.twitter.com/version/notifications/follow

		String unfollowUser = useService(u, "http://api.twitter.com/version/friendships/destroy.xml?user_id="+unfollowerid);
		System.out.println(unfollowUser);
	}

	public static void followUser(User u,int followerid) {
		//http://api.twitter.com/version/notifications/follow
		String followUser = useService(u, "http://api.twitter.com/version/friendships/create.xml?user_id="+followerid);
		System.out.println(followUser);
	}

}

