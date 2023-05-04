package main_page;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Shop{

	public static void writer(String username, String password, String type) throws IOException{
		File csvFile = new File("C:\\Users\\Sahil\\Downloads\\data.csv");
		FileReader fileReader = new FileReader(csvFile);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    List<String> adminAuth = new ArrayList<>();
		String line;
	    while ((line = bufferedReader.readLine()) != null) {
	        adminAuth.add(line);
	    }
	    String s = username+","+password+","+type;
	    adminAuth.add(s);
	    bufferedReader.close();
		FileWriter fileWriter = new FileWriter(csvFile);
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    for (String record : adminAuth) {
	        bufferedWriter.write(record);
	        bufferedWriter.newLine();
	    }
	    
	    bufferedWriter.close();
	    System.out.println("User Details Saved");
	}

	static boolean login(String username, String password, String type) throws IOException {
		File csvFile = new File("C:\\Users\\Sahil\\Downloads\\data.csv");
		FileReader fileReader = new FileReader(csvFile);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    List<String> adminAuth = new ArrayList<>();
		String line;
	    while ((line = bufferedReader.readLine()) != null) {
	        adminAuth.add(line);
	    }
	    int found = 0;
	    for(String s:adminAuth){
	    	String[] ans = s.split(",");
	    	if(type.equals(ans[2]) && username.equals(ans[0]) && password.equals(ans[1]) && found==0) {
	    		System.out.println("Logged In");
	    		if(type=="A") {
	    			Admin admin = new Admin(username,password);
	    			admin.selectOption();
	    		} else if(type=="C") {
	    			Customer customer = new Customer("C:\\Users\\Sahil\\Downloads\\data.csv");
	    			customer.displayCustomerMenu();
	    		}
         	    return true;
         	   }
	    	System.out.println("User Not Found");
	    	
	    }
		return false;
	}
	public static void main(String[] args) throws IOException {
		String username, password, type;
//		BufferedReader br = new BufferedReader(new FileReader("D:\\Wiley Edge\\Code Practise\\Shopping_Application\\login_info.csv"));
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO ONLINE SHOPPING SYSTEM\n");
		System.out.println("*******************************************************");
		System.out.println("\n1.REGISTER AS ADMIN\n2.REGISTER AS CUSTOMER\n3.LOGIN TO SYSTEM\n4.EXIT\n");
		System.out.println("*******************************************************\n");
		System.out.println("Enter Choice: ");
		int n = sc.nextInt();
		switch(n) {
		case 1:
			System.out.println("Username: ");
			username = sc.next();
			System.out.println("Password: ");
			password = sc.next();
			type = "A";
			writer(username, password, type);
			break;
		case 2:
			System.out.println("Username: ");
			username = sc.next();
			System.out.println("Password: ");
			password = sc.next();
			type = "C";
			writer(username, password,type);
			break;
		case 3:
			System.out.println("Type of user (A->Admin & C->Customer): ");
			type = sc.next();
			System.out.println("Username: ");
			username = sc.next();
			System.out.println("Password: ");
			password = sc.next();
			login(username,password,type);
			break;
		case 4:
			System.out.println("Thank you");
			break;
		default:
			System.out.println("Not a valid option");
		}
		sc.close();
	}
}
