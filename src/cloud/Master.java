package cloud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Graph.Edge;
import Graph.StdOut;
import Graph.User;

public class Master {

	private ArrayList<DataCenter> dataCenters;
	UserMap userMap;
	
	public Master(){
		this.dataCenters = new ArrayList<DataCenter>();
		userMap = new UserMap();
	}
	
	public void addDataCenter(DataCenter dataCenter){
		this.dataCenters.add(dataCenter);
	}
	
	public void checkAllocate(){
		for(DataCenter center:dataCenters){
			center.checkAllocate();
		}
	}
	
	public static void assignUsers(){
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
			    String title = parts[0];
			    int id = Integer.parseInt(parts[1]);
			    System.out.println(id + " " + title);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		// Create DataCenters  
		Master cloud=new Master();
		DataCenter egypt=new DataCenter(500, "Egypt", 1000, cloud.dataCenters.size()) ;
		DataCenter france=new DataCenter(400, "France", 4000, cloud.dataCenters.size()) ;
		DataCenter us=new DataCenter(1000, "U.S", 9000, cloud.dataCenters.size());
		DataCenter china=new DataCenter(10000, "China",8000, cloud.dataCenters.size());


		
		// Create Users and assign them to random data centers
		//Egypt
		
		assignUsers();
		
		// call allocate users for each datacenter
		
//		egypt.addUser(new User("Ahmed Behairy"));
//		egypt.addUser(new User("Rana Ramez"));
//		egypt.addUser(new User("Ismail Taha"));
//		//France
//		france.addUser(new User("Carmen Gervet"));
//		france.addUser(new User("Anelka"));
//		france.addUser(new User("Zindine Zidan"));
//		//U.S
//		us.addUser(new User("Roger Federer"));
//		us.addUser(new User("Martin Luther King"));
//		us.addUser(new User("Nadal"));
//		//China
//		china.addUser(new User("Chom Sing jung"));
//		china.addUser(new User("Chi li so"));
//		china.addUser(new User("Lee mic"));
//		
//		egypt.users.addEdge(new Edge(0,1,100));

		

		 
//		cloud.addDataCenter(egypt);
//		cloud.addDataCenter(france);
//		cloud.addDataCenter(us);
//		cloud.addDataCenter(china);
//		
//		StdOut.println(cloud.dataCenters.get(0).users);
	
		
		
	}
}
