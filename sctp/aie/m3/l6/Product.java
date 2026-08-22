package sctp.aie.m3.l6;

public class Product {
    private static final String DEFAULT_PRODUCT_ID = "0000";
    private static final String DEFAULT_NAME = "Unknown Product";

    private static int instanceCount = 0;

    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String productId, String name, double price, int stockQuantity) {
        if (productId == null || productId.isEmpty()) {
            productId = DEFAULT_PRODUCT_ID;
        }
        if (name == null || name.isEmpty()) {
            name = DEFAULT_NAME;
        }

        this.productId = productId;
        this.name = name;
        setPrice(price);
        setStockQuantity(stockQuantity);

        instanceCount++;
    }

    public Product(String productId, String name, double price) {
        this(productId, name, price, 0);
    }

    public Product() {
        this(DEFAULT_PRODUCT_ID, DEFAULT_NAME, 0.0, 0);
    }

    public Product(Product other) {
        this(other.productId, other.name, other.price, other.stockQuantity);
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative. Setting price to 0.");
            this.price = 0;
        }
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            System.out.println("Stock quantity cannot be negative. Setting stock quantity to 0.");
            this.stockQuantity = 0;
        }
    }

    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", stockQuantity="
                + stockQuantity + "]";
    }

    public String toHashCodeString() {
        return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this));
    }

    public static void main(String[] args) {
        Product pdtAllParameters = new Product("1234", "Laptop", 999.99, 10);
        Product pdtWithoutQty = new Product("5678", "Smartphone", 499.99);
        Product pdtNoParameters = new Product();

        System.out.println("Product with all parameters: " + pdtAllParameters);
        System.out.println("Product without quantity: " + pdtWithoutQty);
        System.out.println("Product with no parameters: " + pdtNoParameters);

        Product pdtCopy = new Product(pdtAllParameters);
        System.out.println("Copied product: " + pdtCopy);
        System.out.println("Are the original and copied products equal? " + pdtAllParameters.equals(pdtCopy));
        System.out.println("Hash code of original product and copied product: "
                + pdtAllParameters.toHashCodeString() + " and " + pdtCopy.toHashCodeString());
    }
}