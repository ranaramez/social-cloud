package Graph;

import java.util.ArrayList;

import cloud.DataCenter;
import cloud.Friends;
import cloud.Master;

public class User {

	String name;
	public String profileId;
	double usedMemory;
	double geolocation;
	String origin;
	public ArrayList<Friends> externalFriends;
	
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
	
	public boolean updateMemory(double dataSize)
	{
		int dc = Integer.parseInt(profileId.split("-")[1]);
		if(Master.getDataCenterbyId(dc).getFreeStorage() > dataSize)
		{
			usedMemory += dataSize;
			return true;
		}
		return false;
	}
	
	public double getMemory()
	{
		return usedMemory;
	}
	
	public double getPerformance()
	{
		DataCenter dc = Master.getDataCenterbyId(Integer.parseInt(profileId.split("-")[1]));
		int userId=Integer.parseInt(profileId.split("-")[0]);
		double localFriends =0;
		for(Edge e:dc.users.adj(userId)){
			localFriends++;
		}

		int totalNumberOfFriends=dc.getFriends(this.profileId).size();

		return localFriends/totalNumberOfFriends;
	}
}
