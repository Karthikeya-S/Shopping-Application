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


public class shop{

	private String user;
	private String type;
	public static void writer(String username, String password, String type) throws IOException{
		File csvFile = new File(FilePath.Userpath);
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
		File csvFile = new File(FilePath.Userpath);
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
	    		System.out.println("User Exist");
	    		bufferedReader.close();
	    		fileReader.close();
         	    return true;
         	   }
	    	//System.out.println("User Not Found");
	    	
	    }
	    bufferedReader.close();
		fileReader.close();
		return false;
	}
	public static void main(String[] args) throws IOException {
		String username, password, type;
		
		
        Scanner sc = new Scanner(System.in);

		
		while(true) {
			System.out.println("WELCOME TO ONLINE SHOPPING SYSTEM\n");
			System.out.println("*******************************************************");
			System.out.println("\n1.REGISTER AS ADMIN\n2.REGISTER AS CUSTOMER\n3.LOGIN TO SYSTEM\n4.EXIT\n");
			System.out.println("*******************************************************\n");
			System.out.println("Enter Choice: ");
			int n = sc.nextInt();
		    if(n == 4) {
		    	System.out.println("Thank you for shooping with us.");
		    	break;
		    }
		    else {
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
				boolean islogin = login(username,password,type);
				shop sh = new shop();
				if (islogin) {
					sh.setUser(username);
					sh.setType(type);
					if(type.equals("A")){
						Admin.selectOption();
						break;
					}else {
						Customer c = new Customer(FilePath.Productpath);
						c.displayCustomerMenu();
						break;
					}
				}else {
					break;
				}
				
				
			
			default:
				System.out.println("Not a valid option");
				break;
			}
			}
			
			
			}
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
