package main_page;

import java.util.*;
import java.io.*;
import java.nio.channels.spi.SelectorProvider;

public class Admin {
	
	private String adminName;
	private String adminPassword;
	
	static Scanner adminReader;
	static Scanner scan;
	static Scanner productReader; 

	public Admin(String adminName, String password) {
		this.adminName = adminName;
		this.adminPassword = password;
	}
	
	

	public static int getNumberOfProducts() throws FileNotFoundException {
		productReader =  new Scanner(new File(FilePath.Productpath));
		int noOfProducts=0;
		try (BufferedReader reader = new BufferedReader(new FileReader(FilePath.Productpath))) {
	          while (reader.readLine() != null) noOfProducts++;
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
		return noOfProducts;		
	}
	
	
	public static void addProduct(int prodID, String prodName, double rate) throws IOException {
		Product p = new Product(prodID, prodName, rate);
		/*
		FileWriter fw = new FileWriter(new File("C:\\Users\\Sahil\\eclipse-workspace\\Wiley_Java_Course\\data\\data.csv"));
		String s = "";
		s+=p.getId()+","+p.getName()+","+p.getPrice()+"\n";
		fw.write(s);
		System.out.println("Product added");
		*/
		
		
		
		File csvFile = new File(FilePath.Productpath);
		FileReader fileReader = new FileReader(csvFile);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    List<String> products = new ArrayList<>();
		String line;
	    while ((line = bufferedReader.readLine()) != null) {
	        products.add(line);
	    }
	    String s = p.getId()+","+p.getName()+","+p.getPrice();
	    products.add(s);
	    bufferedReader.close();
		FileWriter fileWriter = new FileWriter(csvFile);
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    for (String record : products) {
	        bufferedWriter.write(record);
	        bufferedWriter.newLine();
	    }
	    
	    bufferedWriter.close();
	    System.out.println("Product Added");
	    
	}
	
	public static void removeProduct(int prodID) throws IOException {
		File csvFile = new File(FilePath.Productpath);
        FileReader fileReader = new FileReader(csvFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Read all the records into a List
        List<String> allLines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            allLines.add(line);
        }

        // Close the BufferedReader
        bufferedReader.close();
        System.out.println(allLines);
        // Remove the record you want to delete from the list
        if(prodID > allLines.size() ) {
        	System.out.println("No Such Product");
        	return;
        }
        
        int indexToDelete = prodID-1; // for example
        allLines.remove(indexToDelete);

        // Open the CSV file for writing
        FileWriter fileWriter = new FileWriter(csvFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Write all the remaining records to the CSV file
        for (String record : allLines) {
            bufferedWriter.write(record);
            bufferedWriter.newLine();
        }

        // Close the BufferedWriter
        bufferedWriter.close();
	}
	
	public static void editProduct(int prodID, Product product) throws IOException {
		File csvFile = new File(FilePath.Productpath);
        FileReader fileReader = new FileReader(csvFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Open a temporary file for writing
        File tempFile = new File("tempFile.csv");
        FileWriter fileWriter = new FileWriter(tempFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Read and write each line of the CSV file
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // Split the line into fields using a comma separator
            String[] fields = line.split(",");

            // Modify the field(s) as needed
            if (fields[0].equals(Integer.toString(prodID))) {
                fields[1] = product.getName();
                fields[2] = Double.toString(product.getPrice());
//                fields[3] = product.getDescription();
            }

            // Join the fields into a line using a comma separator
            String modifiedLine = String.join(",", fields);

            // Write the modified line to the temporary file
            bufferedWriter.write(modifiedLine);
            bufferedWriter.newLine();
        }

        // Close the BufferedReader and BufferedWriter
        bufferedReader.close();
        bufferedWriter.close();

        // Delete the original CSV file
        csvFile.delete();

        // Rename the temporary file to the original CSV file name
        tempFile.renameTo(csvFile);
	}
	
	
//	public void registerAdmin(String username, String password) throws IOException {
//		File csvFile = new File("D:\\\\Wiley_Training\\\\ShopppingApp\\productDetails.csv");
//		FileReader fileReader = new FileReader(csvFile);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        List<String> adminAuth = new ArrayList<>();
//		String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            adminAuth.add(line);
//        }
//        String s = username+","+password;
//        adminAuth.add(s);
//        bufferedReader.close();
//		FileWriter fileWriter = new FileWriter(csvFile);
//        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//        for (String record : adminAuth) {
//            bufferedWriter.write(record);
//            bufferedWriter.newLine();
//        }
//        bufferedWriter.close();
//	}
//	
//	public void loginAdmin(String username, String password) throws IOException{
//		File csvFile = new File("D:\\\\Wiley_Training\\\\ShopppingApp\\productDetails.csv");
//		FileReader fileReader = new FileReader(csvFile);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        
//        String line;
//        int flag=0;
//        while ((line = bufferedReader.readLine()) != null) {
//            // Split the line into fields using a comma separator
//            String[] fields = line.split(",");
//            if(fields[0].equals(username) && fields[1].equals(password)) {
//            	flag=1;
//            }
//        }
//        
//        bufferedReader.close();
//        if(flag==0) {
//        	System.out.println("Invalid credentials.");
//        }
//        
//	}
	
public static void selectOption() {
		
		System.out.println("\n\n*****   WELCOME TO ADMIN PORTAL   *****\n");
		scan = new Scanner(System.in);
		int i;
		do {
			
		System.out.println("Select 1 to add Product");
		System.out.println("Select 2 to delete Product");
		System.out.println("Select 3 to edit Product");
//		System.out.println("Select 4 to search Product");
		System.out.println("Select 4 to view Customer");
		System.out.println("Select 5 to delete Customer");
		System.out.println("Select 10 to exit");
		System.out.println("\nEnter: ");
		
		
		i = scan.nextInt();
		if(i>=1 && i<=10) {
			try {
				callFunction(i);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			
		}while(i!=10);
	}
	
	public static void callFunction(int i) throws IOException {
		
		
		
		
		switch(i) {
		
		case 1:{
			System.out.println("You have selected to add a Product.\nPlease Enter:\n");
			System.out.println("\nName:");
			String name = scan.next();
			System.out.println("\nRate:");
			double rate = scan.nextDouble();
//			System.out.println("\nDescription:");
//			String desc = scan.next();
			addProduct(getNumberOfProducts(), name, rate);
			break;
		}
			
		case 2:{
			System.out.println("You have selected to remove a Product.\nPlease Enter:\n");
			System.out.println("\nID:");
			int id = scan.nextInt();
			removeProduct(id);
			break;
		}
		
		case 3:{
			System.out.println("You have selected to edit a Product.\nPlease Enter:\n");
			System.out.println("\nID:");
			int id = scan.nextInt();
			System.out.println("\nName:");
			String name = scan.next();
			System.out.println("\nRate:");
			double rate = scan.nextDouble();
//			System.out.println("\nDescription:");
//			String desc = scan.next();
			Product pd = new Product(id, name, rate);
			editProduct(id, pd);
			break;
		}
			
		case 4:
			System.out.println("You have selected to view Customers.");
			viewCustomer();
			break;
		
		case 5:
			System.out.println("You have selected to delete a Customer.\nPlease Enter:\n");
			System.out.println("\nID:");
			String id = scan.next();
			deleteCustomer(id);
			break;
			
		
		
		case 10:
			return;
			
		}
		
	}
	
//	public static void main(String[] args) throws IOException {
//		Admin ad = new Admin(" ", " ");
//	//	ad.viewCustomers();
////		viewCustomers();
//		ad.selectOption();
//	}
	
	public static void viewCustomer() throws IOException {
	
		FileReader fileR = new FileReader(FilePath.Userpath);
		System.out.println("\n********************************************\n");

		String line = "";
		String splitBy = ",";
		
		BufferedReader br = new BufferedReader(fileR);
		while((line = br.readLine())!=null) {
			String[] customers = line.split(splitBy);
			System.out.println("Customer UserName: "+ customers[0] + "Customer Password: "+customers[1]);
		}
	
		System.out.println("\n********************************************\n\n\n");
		br.close();
	}
	
public static void deleteCustomer(String id) throws IOException {
		
		FileReader fileR = new FileReader(FilePath.Userpath);
		BufferedReader br = new BufferedReader(fileR);
		
		int idxToDel=-1;
		
		String line;
		String splitBy = ",";
		
		int curr = 0;
		
		ArrayList<String> data = new ArrayList<>();
		while((line = br.readLine())!=null) {
			String split[] = line.split(splitBy);
			if(split[0].equals(id)) {
				idxToDel = curr;			
			}
			data.add(line);
			curr++;
		}
		
		
		if(idxToDel == -1) {
			System.out.println("No valid Customer exist.");
			return;
		}
		
		
		
		fileR.close();
		br.close();
		
		
		data.remove(idxToDel);
		
		
		
		writeCSV(data);
		
	}

public static void writeCSV(ArrayList<String> data) {
	FileWriter fw;
	try {
		fw = new FileWriter(FilePath.Userpath);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(String str: data) {
			bw.write(str);
			bw.newLine();
		}
		
		bw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("Customer is successfully deleted.!!!!!!");

}
	
	
	
	
	private void registerCustomer(Customer obj) {
		//Shop.registerCustomer();
	}
	
}
