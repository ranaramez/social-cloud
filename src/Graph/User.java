package Grpah;
import java.util.ArrayList;


public class User {
	public String name;
	public String id;
	public ArrayList<User> friends;
	public int postsNumber;
	
	public User(String name, String id){
		this.name = name;
		this.id = id;
		this.friends = new ArrayList<User>();
		this.postsNumber = 0;
	}
	
	public void AddFriend(User friend){
		this.friends.add(friend);
	}
	
	public void AddBulkFriends(ArrayList<User> friends)
	{
		this.friends = friends;
	}
	
	public void incrementPosts(){
		this.postsNumber ++ ;
	}
	
}
