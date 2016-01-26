package tain.kr.com.test.enumtest.v01;

public enum EnumProduct {

	COMPUTER("S전자", "R70", 123400.0),
	NOTEBOOK("L전자", "R410", 43252.0)
	;
	
	private final String company;
	private final String model;
	private final double price;
	
	private static final double vat = 0.1;
	
	EnumProduct(String company, String model, double price) {
		this.company = company;
		this.model = model;
		this.price = price;
	}
	
	public String getCompany() {
		return this.company;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public double getPrice(int amount) {
		double price = this.price * (double) amount;
		return (price * vat) + price;
	}
	
	public static void main(String[] args) {
		int cnt = 1;
		
		double estimate = EnumProduct.COMPUTER.getPrice(5);
		String company = EnumProduct.COMPUTER.getCompany();
		String model = EnumProduct.COMPUTER.getModel();
		
		System.out.println("> " + company + " : " + model + " : " + estimate);
		System.out.println();
		
		for (EnumProduct x : EnumProduct.values()) {
			estimate = x.getPrice(5);
			company = x.getCompany();
			model = x.getModel();
			
			System.out.println("(" + cnt + ") " + company + " : " + model + " : " + estimate);
			cnt++;
		}
	}
}
