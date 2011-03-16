package it.unibz.utils;

import it.unibz.model.Follower;
import it.unibz.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import oauth.signpost.*;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;


public class CallInvoker {
	private static OAuthConsumer consumer=null;
	private static ArrayList<String> ret = null;
	private static int not = 0;


	private static String useService(User user, String url){
		return useService(user, url, "GET");
	}

	private static String useService(User user, String url, String requestMethod){
		String line = null;
		try {
			OAuthConsumer consumer = Login(user);
			URL uu = new URL(url);
			HttpURLConnection request =  (HttpURLConnection)uu.openConnection();
			request.setRequestMethod(requestMethod);
			request.setDoOutput(true);
			request.setReadTimeout(10000);
			consumer.sign(request);
			BufferedReader rd  = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();

			while ((line = rd.readLine()) != null){
				sb.append(line + '\n');
			}

			line = sb.toString();
			request.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return line.toString();
	}

	private static OAuthConsumer Login(User user) {
		// Create a new consumer using the commons implementation
		if(consumer==null){ 
			consumer = new DefaultOAuthConsumer("Kgrg9GlpGU8yoza6u1KqQQ","bZUrgRsSWu9JiXMsE9mFFT5pcosZzPv4vKca7nhZsE");
			consumer.setTokenWithSecret(user.getAccesstoken(), user.getTokensecret());
		}
		return consumer;
	}

	public static ArrayList<String> getTweets(User user) {
		String tweets = useService(user, "http://api.twitter.com/1/statuses/home_timeline.xml?count=21");
		Document dom = stringToDom(tweets);
		NodeList nodeList = dom.getElementsByTagName("status");
		if (ret == null) 
			ret = new ArrayList<String>();
		ArrayList<String> t = new ArrayList<String>();
		String temp;
 		not=0;
		for (int i = 0; i < nodeList.getLength(); i++) {
			temp = getXmlElement(dom,i,"screen_name")+": "+getXmlElement(dom,i,"text");
			t.add(temp);
			if (!ret.contains(temp)){	  
			  not++;
			}
		}
		ret=t;
		return ret;
	}

	public static ArrayList<Follower> getFollowing(User user) {
		//http://api.twitter.com/version/notifications/follow
		String followersId = useService(user, "http://api.twitter.com/1/friends/ids.xml");
		Document dom = stringToDom(followersId);
		NodeList nodeList = dom.getElementsByTagName("id");
		Follower u;
		ArrayList<Follower> tweetUserList = new ArrayList<Follower>();
		// foreach id we get the value
		for (int i = 0; i < nodeList.getLength(); i++) {
			u=new Follower();
			u.setId(getXmlElement(dom,i,"id")); 
			u.setName(getFollowerInfo(user,u.getId(),"name"));
			u.setScreenName(getFollowerInfo(user,u.getId(),"screen_name"));
			tweetUserList.add(u);
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
		String userInfo = useService(u, "http://api.twitter.com/1/users/show.xml?user_id="+urlEncoder(id));
		Document dom = stringToDom(userInfo);
		return getXmlElement(dom,0,tag);
	}

	public static int getNotifications() {
		return not;
	}

	public static boolean unfollowUser(User u,String unfollowerScreen) {
		String unfollowUser = useService(u, "http://api.twitter.com/1/friendships/destroy.xml?screen_name="+urlEncoder(unfollowerScreen), "POST");
		Document dom = stringToDom(unfollowUser);
		if (getXmlElement(dom,0,"screen_name").equals(unfollowerScreen))
			return true;
		else 
			return false;
	}

	public static boolean followUser(User u,String followerScreen) {
		String followUser = useService(u, "http://api.twitter.com/1/friendships/create.xml?screen_name="+urlEncoder(followerScreen),"POST");
		Document dom = stringToDom(followUser);
		if (getXmlElement(dom,0,"screen_name").equals(followerScreen))
			return true;
		else 
			return false;
	}

	public static void updateUser(User u, String status) {
		useService(u, "http://api.twitter.com/1/statuses/update.xml?status="+urlEncoder(status),"POST");
	}
	
	private static String urlEncoder(String url){
		String encodedData=null;
		try {
			encodedData = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodedData;
	}

}

