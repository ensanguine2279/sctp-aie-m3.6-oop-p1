package sctp.aie.m3.l6;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static int cartCount = 0;

    private String cartId;
    private String customerName;
    private List<OrderItem> items;

    public ShoppingCart(String cartId, String customerName) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.items = new ArrayList<>();

        cartCount++;
    }

    public static int getCartCount() {
        return cartCount;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getAmount();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shopping Cart ID: ").append(cartId).append("\n");
        sb.append("Customer Name: ").append(customerName).append("\n");
        sb.append("Items:\n");
        for (OrderItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Total Amount: $").append(getTotal()).append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {

        OrderItem item1 = new OrderItem("Laptop", 999.99);
        OrderItem item2 = new OrderItem("Smartphone", 499.99);

        ShoppingCart cart1 = new ShoppingCart("C001", "John Doe");
        cart1.addItem(item1);
        cart1.addItem(item2);

        System.out.println(cart1.toString());

        OrderItem item3 = new OrderItem("Headphones", 199.99);
        OrderItem item4 = new OrderItem("Keyboard", 49.99);

        ShoppingCart cart2 = new ShoppingCart("C002", "Jane Smith");
        cart2.addItem(item3);
        cart2.addItem(item4);

        System.out.println(cart2.toString());

        System.out.println("Total number of shopping carts created: " + ShoppingCart.getCartCount());

        List<OrderItem> copyOfOrderItems = cart1.getItems();
        copyOfOrderItems.clear();
        System.out.println("\nAfter clearing the copy of cart1 items:\n" + cart1.getItems()); // Should still show the
                                                                                              // original items
    }
}
