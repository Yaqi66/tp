package seedu.pharmatracker.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.pharmatracker.customer.Customer;
import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.data.Medication;
import seedu.pharmatracker.ui.Ui;

/**
 * Command that dispenses a specified quantity of a medication from the inventory.
 *
 * <p>The target medication is identified by a 1-based index. Dispensing fails
 * if the index is out of range or if the requested quantity exceeds current stock,
 * in both cases printing an error message and leaving the inventory unchanged.
 * An optional customer index links the dispense event to a registered customer's
 * dispensing history. If omitted, behaviour is identical to the original command.
 */
public class DispenseCommand extends Command {

    /** Command keyword used to trigger this command. */
    public static final String COMMAND_WORD = "dispense";

    /** Sentinel value indicating no customer was linked. */
    private static final int NO_CUSTOMER = -1;

    private static final Logger logger = Logger.getLogger(DispenseCommand.class.getName());

    /** 1-based index of the medication to dispense from the inventory. */
    private final int index;

    /** Number of units to dispense. */
    private final int quantity;

    /** 1-based index of the customer to link, or {@value #NO_CUSTOMER} if none. */
    private final int customerIndex;

    /**
     * Constructs a {@code DispenseCommand} with no customer linking.
     *
     * @param index    1-based position of the medication in the inventory
     * @param quantity number of units to dispense; must not exceed available stock
     */
    public DispenseCommand(int index, int quantity) {
        this.index = index;
        this.quantity = quantity;
        this.customerIndex = NO_CUSTOMER;
    }

    /**
     * Constructs a {@code DispenseCommand} with optional customer linking.
     *
     * @param index         1-based position of the medication in the inventory
     * @param quantity      number of units to dispense; must not exceed available stock
     * @param customerIndex 1-based position of the customer to link, or -1 if none
     */
    public DispenseCommand(int index, int quantity, int customerIndex) {
        this.index = index;
        this.quantity = quantity;
        this.customerIndex = customerIndex;
    }

    /**
     * Executes the dispense command, reducing the medication's stock by the specified quantity.
     * If a valid customer index is provided, records the dispense event in that customer's history.
     *
     * <p>Prints an error and returns early under the following conditions:
     * <ul>
     *   <li>The medication index is less than 1 or exceeds the inventory size.</li>
     *   <li>The requested quantity exceeds the medication's current stock.</li>
     *   <li>A customer index is provided but is out of range.</li>
     * </ul>
     *
     * @param inventory    the inventory from which to dispense the medication
     * @param ui           the UI instance (unused directly; output goes via {@code System.out})
     * @param customerList the list of registered customers
     */
    @Override
    public void execute(Inventory inventory, Ui ui, CustomerList customerList) {
        logger.log(Level.INFO, "Executing DispenseCommand: index={0}, quantity={1}",
                new Object[]{index, quantity});

        if (index < 1 || index > inventory.getMedications().size()) {
            logger.log(Level.WARNING, "Invalid index: {0}", index);
            System.out.println("Invalid index. Please enter a valid index.");
            return;
        }

        Medication med = inventory.getMedication(index - 1);

        if (quantity > med.getQuantity()) {
            logger.log(Level.WARNING, "Insufficient stock for {0}: requested={1}, available={2}",
                    new Object[]{med.getName(), quantity, med.getQuantity()});
            System.out.println("Insufficient stock. Current stock: " + med.getQuantity());
            return;
        }

        if (customerIndex != NO_CUSTOMER && (customerIndex < 1 || customerIndex > customerList.size())) {
            logger.log(Level.WARNING, "Invalid customer index: {0}", customerIndex);
            System.out.println("Invalid customer index. Please enter a valid index.");
            return;
        }

        med.setQuantity(med.getQuantity() - quantity);
        logger.log(Level.INFO, "Dispensed {0} units of {1}. Updated stock: {2}",
                new Object[]{quantity, med.getName(), med.getQuantity()});

        System.out.println("Dispensing successfully!");
        System.out.println("Medication: " + med.getName());
        System.out.println("Amount: " + quantity + " units");
        System.out.println("Updated Stock: " + med.getQuantity() + " units");

        if (customerIndex != NO_CUSTOMER) {
            Customer customer = customerList.getCustomer(customerIndex - 1);
            String record = med.getName() + " | " + med.getDosage()
                    + " | Qty: " + quantity;
            customer.addDispensingHistory(record);
            logger.log(Level.INFO, "Linked dispense to customer: {0}", customer.getName());
            System.out.println("Recorded for customer: [" + customer.getCustomerId() + "] "
                    + customer.getName() + ".");
        }
    }
}
