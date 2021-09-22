package vo;

public class Product {
	private String product_code;
	private String product_name;
	private int product_kcal;
	private String product_allergy;
	private int product_price;
	private String product_choice;
	private String product_inout;
	private String product_date;
	private int product_count;
	private String product_img;
	
	public Product() {}

	public Product(String product_code, String product_name, int product_kcal, String product_allergy,
			int product_price, String product_choice, String product_inout, String product_date, int product_count,
			String product_img) {
		super();
		this.product_code = product_code;
		this.product_name = product_name;
		this.product_kcal = product_kcal;
		this.product_allergy = product_allergy;
		this.product_price = product_price;
		this.product_choice = product_choice;
		this.product_inout = product_inout;
		this.product_date = product_date;
		this.product_count = product_count;
		this.product_img = product_img;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_kcal() {
		return product_kcal;
	}

	public void setProduct_kcal(int product_kcal) {
		this.product_kcal = product_kcal;
	}

	public String getProduct_allergy() {
		return product_allergy;
	}

	public void setProduct_allergy(String product_allergy) {
		this.product_allergy = product_allergy;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getProduct_choice() {
		return product_choice;
	}

	public void setProduct_choice(String product_choice) {
		this.product_choice = product_choice;
	}

	public String getProduct_inout() {
		return product_inout;
	}

	public void setProduct_inout(String product_inout) {
		this.product_inout = product_inout;
	}

	public String getProduct_date() {
		return product_date;
	}

	public void setProduct_date(String product_date) {
		this.product_date = product_date;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	
	
}
