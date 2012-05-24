package Graph;

import java.util.ArrayList;

import cloud.ExternalFriends;

public class User {
	String name;
	int id;
	double usedMemory;
	int geolocation;
	String origin;
	ArrayList<ExternalFriends> externalFriends;
	
	public User(String name){
		this.name = name;
		this.usedMemory = 0.1;
		this.externalFriends= new ArrayList<ExternalFriends>();	
	}
	
	public User(String name,int geolocation,String origin){
		this.name=name;
		this.geolocation=geolocation;
		this.usedMemory = 0.1;
		this.origin=origin;
		this.externalFriends= new ArrayList<ExternalFriends>();
	}
}
