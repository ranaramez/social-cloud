package cloud;

import java.util.ArrayList;
import Graph.EdgeWeightedGraph;
import Graph.User;


public class DataCenter {

	EdgeWeightedGraph users;
	int dataCenterNumber;
	int storage;
	ArrayList<Server> servers;
	String country;
	long geolocation;
	
	
	public DataCenter(int storage, String country, long geolocation, int dataCenterNumber){
		this.users=new EdgeWeightedGraph();
		this.storage=storage;
		this.country = country;
		this.geolocation = geolocation;
		this.dataCenterNumber = dataCenterNumber;
		
		// Create two initial servers.
		this.servers=new ArrayList<Server>();
		this.addServer(new Server(100,100));
		this.addServer(new Server(100,100));

	}
	
	public void addServer(Server server){
		this.servers.add(server);
	}
	
	public void addUser(User user){
		users.addVertex(user);
		
	}

	public void checkAllocate() {
		//For each User get it's friends 
		

		
	}
}
