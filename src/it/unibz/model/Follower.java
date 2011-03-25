package it.unibz.model;

/**
 * Class represents a Twitter Follower with ID, Name and Screenname for future expansion of the client
 *
 */
public class Follower {

	private String id;
	private String name;
	private String screenName;
	
	public Follower (){
		this.id = null;
		this.name = null;
		this.screenName = null;
	}
	
	public Follower (String id, String name, String screenName){
		this.id = id;
		this.name = name;
		this.screenName = screenName;
	}
	
	public void print(){
		System.out.println(id + " " + name + " " + screenName);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
}
