package cloud;

public class ExternalFriends {
	
	int userId;
	double weight;
	
	public ExternalFriends(int userId){
		this.userId = userId;
		weight = 10;
	}
	
	public void updateWeight(double weight){
		this.weight += weight;
	}
	
	
}
