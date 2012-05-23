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
			    if(line == "")
			    	System.out.println("end");
			    System.out.println(parts.length + parts[0]);
			    String title = parts[0];
//			    int id = Integer.parseInt(parts[1]);
//			    System.out.println(title);
//			    System.out.println(id);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
	        if (in != null) {
	            try {
	                in.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
