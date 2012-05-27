package cloud;
import Graph.User;
import Graph.Edge;

public class Application {

	public Application(){
		
	}
	
	public static void assignActivity(String profileId1,String profileId2,int noOfReads,int noOfWrites)
	{
		double w = noOfReads * 0.5 + noOfWrites;
		int dc1 = Integer.parseInt(profileId1.split("-")[1]);		
		int dc2 = Integer.parseInt(profileId2.split("-")[1]);
		int v1 = Integer.parseInt(profileId1.split("-")[0]);
		int v2 = Integer.parseInt(profileId2.split("-")[0]);
		
		boolean writeSuccess1 = Master.getDataCenterbyId(dc1).users.getUserByProfileId(profileId1).updateMemory(noOfWrites * 0.1);
		boolean writeSuccess2 = Master.getDataCenterbyId(dc2).users.getUserByProfileId(profileId2).updateMemory(noOfWrites * 0.1);
		if(writeSuccess1 && writeSuccess2)
		{
			if(dc1 == dc2)
			{
				for(Edge edge: Master.getDataCenterbyId(dc1).users.adj(v1))
				{
					if(edge.other(v1) == v2)
					{
						edge.updateWeight(w);
						break;
					}
				}
			}
			else
			{
				for(Friends friend: Master.getDataCenterbyId(dc1).users.getUserByProfileId(profileId1).externalFriends)
				{
					if(friend.profileId.equals(profileId2))
					{
						friend.updateWeight(w);
						break;
					}
				}
			
				for(Friends friend:Master.getDataCenterbyId(dc2).users.getUserByProfileId(profileId2).externalFriends)
				{
					if(friend.profileId.equals(profileId1))
					{
						friend.updateWeight(w);
						break;
					}
				}
			}
		}
	}
}
