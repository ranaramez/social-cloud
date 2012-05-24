package cloud;

public class Friends {
	
	String profileId;
	boolean externalFriend;
	double weight;
	
	public Friends(String profileId, boolean externalFriend){
		this.profileId = profileId;
		weight = 10;
		this.externalFriend = externalFriend;
	}
	
	public void updateWeight(double weight){
		this.weight += weight;
	}
	
	
}
