package Graph;

import java.util.ArrayList;

public class User {
	String name;
	int id;
	double usedMemory;
	int geolocation;
	String origin;
	ArrayList<Integer> externalFriends;
	
	public User(String name){
		this.name = name;
		this.usedMemory = 0.1;
		this.externalFriends= new ArrayList<Integer>();	
	}
	
	public User(String name,int geolocation,String origin){
		this.name=name;
		this.geolocation=geolocation;
		this.origin=origin;
	}
}
