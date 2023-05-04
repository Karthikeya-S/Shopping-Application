package main_page;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer {
	static ArrayList<Product> cart;
	static HashMap<String,String> productList;
	// name , id + +price

	Customer(String csvFile){
		cart = new ArrayList<>();
		productList = new HashMap<>();
		String line = "";
		String csvDelimiter = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {
				String[] productData = line.split(csvDelimiter);
				String details = ""+productData[0]+" "+productData[2];
				productList.put(productData[1], details);
				
				
			}
//			System.out.println(productList);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void viewProducts() {
		
		// TODO Auto-generated catch block
		System.out.println("inside view ");
		System.out.println(productList);

		
		System.out.format("%-20s  %-20s  %-20s%n","productId","name","price");
		for(Map.Entry<String,String> i: productList.entrySet()) {
			String []sp = i.getValue().split(" ");
			
			System.out.format("%-20s  %-20s  %-20s%n",sp[0],i.getKey(),sp[1]);
		}
		

	}

	public static boolean searchProduct(String name) {
		return productList.containsKey(name); 
	}

	public static void addToCart(String name) {
		String productdetails = productList.get(name);
		String []sp =  productdetails.split(" ");  // id +$+price
		int id = Integer.valueOf(sp[0]);
		double price = Double.valueOf(sp[1]);
		Product obj = new Product(id,name,price);
		if(checkContains(obj)!=-1) {
			int oldCount = cart.get(checkContains(obj)).getCount();
			cart.remove(checkContains(obj));
			obj.setCount(oldCount+1);	
			cart.add(obj);
		}
		else {
			obj.setCount(obj.getCount()+1);	
			cart.add(obj);			
		}
		
		System.out.println("Added to Cart");
	}


	static boolean removeProduct(String name){
		String productdetails = productList.get(name);
		System.out.println(productdetails);
		String []sp =  productdetails.split(" ");  // id +$+price
		int id = Integer.valueOf(sp[0]);
		double price = Double.valueOf(sp[1]);
		Product obj = new Product(id,name,price); 

		if(checkContains(obj)!=-1){
			if(obj.getCount()>1) {
				obj.setCount(obj.getCount()-1);
			}
			else {
				cart.remove(checkContains(obj));
			}
			System.out.println("Product removed from cart!");
			return true;
		}
		return false;     
	}

	
	
	public static int checkContains(Product obj) {
		for(int i=0;i<cart.size();i++) {
			if(obj.getName() == cart.get(i).getName())
				
				return i;
		}
		return -1;
	}
	

	static void viewCart() {
		System.out.println("All Cart Items:");
		System.out.println("********************************************");
		System.out.format("%-20s  %-20s  %-20s  %-20s%-20s%n","S.no","productId","name","price","Quantity");

		for(int i=0;i<cart.size();i++) {
			Product curr=cart.get(i);
			System.out.format("%-20s  %-20s  %-20s  %-20s %-20s%n",""+(i+1),""+curr.getId(),""+curr.getName(),""+curr.getPrice(),""+curr.getCount());

		}
	}

	static ArrayList<Product> pay() {
		System.out.println("Call function of payment class and pass arrayList");
		
		return cart;
	}
	
	public void displayCustomerMenu() {
		int i;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("1. View Products");
			System.out.println("2. Search Product");
			System.out.println("3. Add to Cart");
			System.out.println("4. Remove from Cart");
			System.out.println("5. View Cart");
			System.out.println("6. Payment");
			System.out.println("7.Exit");
			System.out.println("Enter your choice: ");
			i = sc.nextInt();
			if(i == 7) break;
			customerOperations(i);
		}
		//sc.close();
		
		
	}
	
	public static void customerOperations(int i) {
		Scanner sc = new Scanner(System.in);
		switch(i) {
		case 1:
			viewProducts();
			break;
		case 2:
			System.out.println("Enter the product you wish to search: ");
			String nameInput = sc.nextLine();
			if(searchProduct(nameInput)) {
				System.out.println(nameInput +" is available in our shop!");
			} else {
				System.out.println(nameInput+" is not available in our shop!");
			}
			break;
		case 3:
			System.out.println("Enter the product you wish to add to cart: ");
			String namenput = sc.nextLine();
			addToCart(namenput);
			break;
		case 4:
			System.out.println("Enter the product you wish to remove from cart: ");
			String removeName = sc.nextLine();
			removeProduct(removeName);
			break;
		case 5:
			viewCart();
			break;
		case 6:
			System.out.println("Are you sure you want to go to payment? (Enter y or n) : ");
			String choice = sc.nextLine();
			if(choice=="y" || choice=="Y") {
				pay();
			}
			break;
		default:
			System.out.println("Incorrect Input!");
			break;
		}	
		
	}
//
//	public static void main(String[] args) {
//		Customer obj = new Customer("product.csv");
//		obj.viewProducts();
//		
//		obj.addToCart("Watch");
//		obj.addToCart("Suitcase");
//		obj.addToCart("Suitcase");
//
//		obj.viewCart();
//		obj.removeProduct("Watch");
//		obj.viewCart();
//
//		
//		//		System.out.println(obj.searchProduct("Suitcase"));
////
//	}

}
