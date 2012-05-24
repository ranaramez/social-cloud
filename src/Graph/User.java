package Graph;

import java.util.ArrayList;

import cloud.Friends;

public class User {
	String name;
	public String profileId;
	double usedMemory;
	int geolocation;
	String origin;
	ArrayList<Friends> externalFriends;
	
	public User(String name){
		this.name = name;
		this.usedMemory = 0.1;
		//this.profileId=userId+"-"+dataCenterId;
		this.externalFriends= new ArrayList<Friends>();	
	}
	
	public User(String name,int geolocation,String origin){
		this.name=name;
		this.geolocation=geolocation;
		this.usedMemory = 0.1;
		this.origin=origin;
		this.externalFriends= new ArrayList<Friends>();
	}
}
