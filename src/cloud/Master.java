package cloud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Graph.StdOut;
import Graph.User;

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
		assignUsers();
		
		printDataCenterUsers(us);
		
//		assignFriends();
		
		// call allocate users for each datacenter
//		checkAllocate();
	}

	
}
