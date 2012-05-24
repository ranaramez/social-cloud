package Graph;

import java.util.ArrayList;

import cloud.Friends;

public class User {
	String name;
	String profileId;
	double usedMemory;
	double geolocation;
	String origin;
	ArrayList<Friends> externalFriends;
	
	public User(String name){
		this.name = name;
		this.usedMemory = 0.1;
		this.externalFriends= new ArrayList<Friends>();	
	}
	
	public User(String name,double geolocation,String origin){
		this.name=name;
		this.geolocation=geolocation;
		this.usedMemory = 0.1;
		this.origin=origin;
		this.externalFriends= new ArrayList<Friends>();
	}
}
