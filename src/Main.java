import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	public static void main(String[] args){

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
}
