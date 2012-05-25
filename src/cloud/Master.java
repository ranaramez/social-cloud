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
					  getDataCenterbyId(dc1).users.addEdge(new Edge(index1, index2, Math.random()*10));
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
		//assignUsers();
		
		//printDataCenterUsers(china);
		
		assignFriends();
		
		// call allocate users for each datacenter
//		checkAllocate();
	}
	
}
