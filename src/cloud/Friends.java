package cloud;

public class Friends {
	
	int userId;
	boolean externalFriend;
	double weight;
	
	public Friends(int userId, boolean externalFriend){
		this.userId = userId;
		weight = 10;
		this.externalFriend = externalFriend;
	}
	
	public void updateWeight(double weight){
		this.weight += weight;
	}
	
	
}
