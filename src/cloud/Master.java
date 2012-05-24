package cloud;

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
		egypt.addUser(new User("Ahmed Behairy"));
		egypt.addUser(new User("Rana Ramez"));
		egypt.addUser(new User("Ismail Taha"));
		//France
		france.addUser(new User("Carmen Gervet"));
		france.addUser(new User("Anelka"));
		france.addUser(new User("Zindine Zidan"));
		//U.S
		us.addUser(new User("Roger Federer"));
		us.addUser(new User("Martin Luther King"));
		us.addUser(new User("Nadal"));
		//China
		china.addUser(new User("Chom Sing jung"));
		china.addUser(new User("Chi li so"));
		china.addUser(new User("Lee mic"));
		
		egypt.users.addEdge(new Edge(0,1,100));

		

		 
		cloud.addDataCenter(egypt);
		cloud.addDataCenter(france);
		cloud.addDataCenter(us);
		cloud.addDataCenter(china);
		
		StdOut.println(cloud.dataCenters.get(0).users);
	
		
		
	}
}
