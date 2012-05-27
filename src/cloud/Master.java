package cloud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Graph.Edge;
import Graph.StdOut;
import Graph.User;
import java.io.*;
public class Master {

	private static ArrayList<DataCenter> dataCenters;
	UserMap userMap;
	
	public Master(){
		dataCenters = new ArrayList<DataCenter>();
		userMap = new UserMap();
	}
	
	public void addDataCenter(DataCenter dataCenter){
		dataCenters.add(dataCenter);
	}
	
	private static void checkAllocate(){
		for(DataCenter center:dataCenters){
			center.checkAllocate();
		}
	}
	
	private static void assignFriends(){
		// assign random friends for each user.
		  try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream("friends.txt");
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)
			  {
				  // Print the content on the console
				  int index1=Integer.parseInt((strLine.split(",")[0]).split("-")[0]);
				  int dc1=Integer.parseInt((strLine.split(",")[0]).split("-")[1]);
				  int index2=Integer.parseInt((strLine.split(",")[1]).split("-")[0]);
				  int dc2=Integer.parseInt((strLine.split(",")[1]).split("-")[1]);
				  if(dc1==dc2)
				  {
					  getDataCenterbyId(dc1).users.addEdge(new Edge(index1, index2));
				  }else
				  {
					  getDataCenterbyId(dc1).users.users.get(index1).externalFriends.add(new Friends(index2+"-"+dc2, true));
					  getDataCenterbyId(dc2).users.users.get(index2).externalFriends.add(new Friends(index1+"-"+dc1, true));
				  }
			  }
			  
			  //Close the input stream
			  in.close();
			  }catch (Exception e){//Catch exception if any
			    	System.err.println("Error: " + e.getMessage());
			  }		
	}
	public static DataCenter getDataCenterbyId(int id)
	{
		DataCenter dc=null;
		for(int i=0;i<dataCenters.size();i++)
		{
			if((dataCenters.get(i).getId())==id)
				dc= dataCenters.get(i);
		}
		return dc;
		
	}
	
	private static void assignCommunication()
	{
		for(DataCenter center: dataCenters)
		{
			ArrayList<User> users = center.users.users;
			int i = 0;
			while(i < users.size())
			{
				int randInd = (int)(Math.random() * (users.size() - 1));
				if(i != randInd)
				{
					Application.assignActivity(users.get(i).profileId, users.get(randInd).profileId, (int)(Math.random() * 10), (int)(Math.random() * 10));
					i++;
				}
			}
			
			for(User user: users)
			{
				for(Friends friend:user.externalFriends)
				{
					Application.assignActivity(user.profileId,friend.profileId, (int)(Math.random() * 100), (int)(Math.random() * 100));
				}
			}
		}
	}
	
	private static void assignUsers(){
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("users.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	    String line = null;
	    try {
			while ((line = in.readLine()) != null) {
			    String[] parts = line.split(",");
			    if(parts.length < 2){
			    	try {
		                in.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
			    	break;
			    }
			    String name = parts[0];
			    double geolocation = Double.parseDouble(parts[1]);
			    createUser(name, geolocation);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getDCIndex(int id)
	{
		for(int i = 0; i < dataCenters.size(); i++)
		{
			if(dataCenters.get(i).dataCenterId == id)
			{
				return i;
			}
		}
		return -1;
	}
	
	private static DataCenter assignDataCenter(User user)
	{
		DataCenter dc = getDataCenterbyId(Integer.parseInt(user.profileId.split("-")[1]));
		int v = Integer.parseInt(user.profileId.split("-")[0]);
		double localWeight = 0;
		
		for(Edge edge:dc.users.adj(v))
		{
			localWeight += edge.weight();
		}
		
		double[] dcWeights = new double[dataCenters.size()];
		
		for(Friends friend: user.externalFriends)
		{
			int dcIndex = getDCIndex(Integer.parseInt(friend.profileId.split("-")[1]));
			dcWeights[dcIndex] += friend.weight * ((1.0 / Math.abs(dataCenters.get(dcIndex).geolocation - dc.geolocation)) * 1000);
		}
		
		double max = localWeight;
		int maxIndex = -1;
		System.out.println("Weights:");
		System.out.println(user.profileId.split("-")[1] + ": " + localWeight);
		for(int i = 0; i < dcWeights.length; i++)
		{
			if(i != getDCIndex(Integer.parseInt(user.profileId.split("-")[1])))
			{
				System.out.println(dataCenters.get(i).dataCenterId + ": " + dcWeights[i] + ", Free Storage: " + dataCenters.get(i).getFreeStorage() + ", User storage: " + user.getMemory());
			}
			if(dcWeights[i] > max && dataCenters.get(i).getFreeStorage() > user.getMemory())
			{
				max = dcWeights[i];
				maxIndex = i;
			}
		}
		
		if(maxIndex == -1)
		{
			return getDataCenterbyId(Integer.parseInt(user.profileId.split("-")[1]));
		}
		else
		{
			return dataCenters.get(maxIndex);
		}
	}
	
	private static void createUser(String name, double geolocation){
		for(DataCenter center: dataCenters){
			if(center.geolocation == geolocation){
				center.addUser(new User(name, geolocation, center.country));
				break;
			}
		}
	}
	
	private static void printDataCenterUsers(DataCenter dataCenter) {
		StdOut.println(dataCenter.users);
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		// Create DataCenters  
		Master cloud=new Master();
		DataCenter egypt=new DataCenter(500, "Egypt", 1000, 2345) ;
		DataCenter france=new DataCenter(400, "France", 4000, 4273) ;
		DataCenter us=new DataCenter(1000, "U.S", 9000, 9200);
		DataCenter china=new DataCenter(10000, "China",8000, 8326);
		

		cloud.addDataCenter(egypt);
		cloud.addDataCenter(france);
		cloud.addDataCenter(us);
		cloud.addDataCenter(china);

		
		// Create Users and assign them to random data centers
		assignUsers();
		
		//printDataCenterUsers(china);
		
		assignFriends();
		StdOut.println(dataCenters.get(0).users);
		System.out.println();
		StdOut.println(dataCenters.get(1).users);
		
		assignCommunication();
		DataCenter dc = assignDataCenter(dataCenters.get(0).users.users.get(0));
		System.out.println(dc.getId());
		// call allocate users for each datacenter
//		checkAllocate();
	}
	
}
