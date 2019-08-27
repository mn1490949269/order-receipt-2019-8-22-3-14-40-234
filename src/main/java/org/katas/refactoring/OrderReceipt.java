package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;
    private static final double DISCOUNT = 0.10;
    private static final String TOTTAL_AMOUNT = "Total Amount";
    private static final String SALES_TAX = "Sales Tax";

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        printHeaders(output);

        // print date, bill no, customer name
//        output.append("Date - " + order.getDate();
        printCustomerDetails(output);
//        output.append(order.getCustomerLoyaltyNumber());

        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : o.getLineItems()) {
        	// prints lineItems
            printItem(output, lineItem);
            // calculate sales tax @ rate of 10%
			double salesTax = lineItem.totalAmount() * DISCOUNT;
            totSalesTx += salesTax;
            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + salesTax;
        }

        // prints the state tax
        pintStateTax(output, totSalesTx);

        // print total amount
        extracted(output, tot);
        return output.toString();
    }

	private void extracted(StringBuilder output, double tot) {
		output.append(TOTTAL_AMOUNT).append('\t').append(tot);
	}

	private void pintStateTax(StringBuilder output, double totSalesTx) {
		output.append(SALES_TAX).append('\t').append(totSalesTx);
	}

	private void printItem(StringBuilder output, LineItem lineItem) {
		output.append(lineItem.getDescription());
		output.append('\t');
		output.append(lineItem.getPrice());
		output.append('\t');
		output.append(lineItem.getQuantity());
		output.append('\t');
		output.append(lineItem.totalAmount());
		output.append('\n');
	}

	private void printCustomerDetails(StringBuilder output) {
		output.append(o.getCustomerName());
        output.append(o.getCustomerAddress());
	}

	private void printHeaders(StringBuilder output) {
		output.append("======Printing Orders======\n");
	}
}