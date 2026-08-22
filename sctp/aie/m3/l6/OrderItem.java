package sctp.aie.m3.l6;

public class OrderItem {
    private String productName;
    private double amount;

    public OrderItem(String productName, double amount) {
        this.productName = productName;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderItem {" +
                "productName='" + productName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
