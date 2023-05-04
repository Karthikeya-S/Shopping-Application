package main_page;

public class Product {
	private int id;
	private double price;
	 private String name;
	 private int count=0;
	 
	
	public Product(int id, String name, double price) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
	

/*
 * package main_page;

public class Product {
	private int prodId;
	private String prodName;
//	private String description;
	private double rate;
	public Product(int prodId, String prodName,  double rate) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.rate = rate;
//		this.description = description;
	}
	
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
}

 */
