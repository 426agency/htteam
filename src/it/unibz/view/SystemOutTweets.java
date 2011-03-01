package it.unibz.view;

import it.unibz.exception.TweetException;
import it.unibz.model.User;
import it.unibz.utils.Tweetproducer;

import org.dom4j.Element;

/**
 * Class that can be used to test if the TweetProducer works.
 * Writes every tweet to the system out as XML.
 */
public class SystemOutTweets {
	
	/**
	 * Writes the XML of a tweet element to the System.out.
	 * @param tweet	a dom4j element
	 * @see com.lowagie.twitter.TweetConsumer#tweet(org.dom4j.Element)
	 */
	public String tweet(Element tweet) {
		System.out.println(tweet.asXML());
		return tweet.elementText("id");
	}

	/**
	 * Creates a TweetProducer and declares SystemOutTweets as its consumer.
	 * If you provide a valid username/password combination this will send
	 * 200 tweets to the System.out in the form of XML.
	 * @param args	no arguments needed
	 * @throws TweetException	if something goes wrong
	 */
	/*public static void main(String[] args) throws TweetException {
	
		System.out.println("Start tweeting:");
		//User u = new User();
		//t.getTweets(u);
		//t.getFollowing(u);
		Tweetproducer.getFollowing(u);
		//System.out.println("The End; last tweet: " + id);
	}*/

}
