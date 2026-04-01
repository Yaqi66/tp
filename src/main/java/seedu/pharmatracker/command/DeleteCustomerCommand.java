package seedu.pharmatracker.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.pharmatracker.customer.Customer;
import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.ui.Ui;

/**
 * Represents a command to remove a customer from the database.
 * The customer to be removed is identified by his 1-based index as shown in the customer list.
 */
public class DeleteCustomerCommand extends Command {

    public static final String COMMAND_WORD = "delete-customer";

    private static final Logger logger = Logger.getLogger(DeleteCustomerCommand.class.getName());
    private final String description;

    public DeleteCustomerCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, CustomerList customerList) {
        logger.log(Level.INFO, "Starting execution of DeleteCommand for index: " + description);

        assert customerList != null : "Customer list cannot be empty in DeleteCustomerCommand";
        assert ui != null : "Ui cannot be null in DeleteCustomerCommand";

        int index = Integer.parseInt(description);
        int zeroBasedIndex = index - 1;

        Customer customer = customerList.getCustomer(zeroBasedIndex);
        assert customer != null : "Retrieved customer to be removed cannot be null";

        customerList.removeCustomer(customer);
        ui.printDeletedCustomerMessage(customer, customerList);

        logger.log(Level.INFO, "Successfully executed DeleteCommand");
    }
}
