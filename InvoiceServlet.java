
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve customer information from the form
        String customerName = request.getParameter("customerName");
        String customerAddress = request.getParameter("customerAddress");

        // Create a list to store invoice items
        List<InvoiceItem> items = new ArrayList<>();

        // Retrieve invoice items from the form
        String[] productNames = request.getParameterValues("productName");
        String[] productPrices = request.getParameterValues("productPrice");
        String[] quantities = request.getParameterValues("quantity");

        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            double productPrice = Double.parseDouble(productPrices[i]);
            int quantity = Integer.parseInt(quantities[i]);
            Product product = new Product(productName, productPrice);
            InvoiceItem item = new InvoiceItem(product, quantity);
            items.add(item);
        }

        // Create a customer and an invoice
        Customer customer = new Customer(customerName, customerAddress);
        Invoice invoice = new Invoice(customer);
        for (InvoiceItem item : items) {
            invoice.addItem(item);
        }

        // Set the invoice as an attribute to be accessed in the JSP
        request.setAttribute("invoice", invoice);

        // Forward the request to the JSP for display
        request.getRequestDispatcher("/billing.jsp").forward(request, response);
    }
}
