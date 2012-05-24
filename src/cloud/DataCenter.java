package cloud;

import java.util.ArrayList;

import Graph.Edge;
import Graph.EdgeWeightedGraph;
import Graph.User;


public class DataCenter {

	EdgeWeightedGraph users;
	int dataCenterId;
	int storage;
	ArrayList<Server> servers;
	String country;
	long geolocation;
	
	
	public DataCenter(int storage, String country, long geolocation, int dataCenterId){
		this.users=new EdgeWeightedGraph();
		this.storage=storage;
		this.country = country;
		this.geolocation = geolocation;
		this.dataCenterId = dataCenterId;
		
		// Create two initial servers.
		this.servers=new ArrayList<Server>();
		this.addServer(new Server(100,100));
		this.addServer(new Server(100,100));

	}
	
	public ArrayList<Friends> getFriends(int user){
		ArrayList<Friends> friends=new ArrayList<Friends>();
		
		int nodeId=0;
		
	      for (Edge e : users.adj(nodeId)) {
	    	  	friends.add(new Friends(this.users.users.get(e.either()).profileId ,false));
	      }
	      
	      return friends;
		
		
	}
	
	public void addServer(Server server){
		this.servers.add(server);
	}
	
	public void addUser(User user){
		users.addVertex(user,this.dataCenterId);	
	}

	
	public void checkAllocate() {
	//For each User get it's friends 
	
	
		

		
	}
}
