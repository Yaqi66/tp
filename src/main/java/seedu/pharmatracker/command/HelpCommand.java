package seedu.pharmatracker.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.ui.Ui;

/**
 * Represents a command to display all available commands in PharmaTracker.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    /**
     * Executes the help command by printing all available commands to the user.
     *
     * @param inventory    The current medication inventory.
     * @param ui           The user interface for displaying messages.
     * @param customerList The list of registered customers.
     */
    @Override
    public void execute(Inventory inventory, Ui ui, CustomerList customerList) {
        assert inventory != null : "Inventory must not be null";
        assert ui != null : "Ui must not be null";
        assert customerList != null : "CustomerList must not be null";

        logger.log(Level.INFO, "Executing HelpCommand");

        try {
            printHelp();
        } catch (Exception e) {
            logger.log(Level.WARNING, "HelpCommand encountered an error: {0}", e.getMessage());
            System.out.println("An unexpected error occurred while displaying help.");
        }

        logger.log(Level.INFO, "HelpCommand executed successfully.");
    }

    /**
     * Prints all available commands grouped by category.
     */
    private void printHelp() {
        System.out.println("=======================================================");
        System.out.println("  PharmaTracker — Available Commands");
        System.out.println("=======================================================");

        System.out.println("\n--- Medication Commands ---");
        System.out.println("  add /n NAME /d DOSAGE /q QUANTITY /e EXPIRY [/t TAG]");
        System.out.println("  delete INDEX");
        System.out.println("  list");
        System.out.println("  find KEYWORD");
        System.out.println("  view INDEX");
        System.out.println("  dispense INDEX q/QUANTITY [c/CUSTOMER_INDEX]");
        System.out.println("  restock INDEX /q QUANTITY");
        System.out.println("  sort");
        System.out.println("  expiring [/days NUMBER]");
        System.out.println("  lowstock [/threshold NUMBER]");
        System.out.println("  label INDEX");

        System.out.println("\n--- Customer Commands ---");
        System.out.println("  add-customer /n NAME /p PHONE [/a ADDRESS]");
        System.out.println("  delete-customer INDEX");
        System.out.println("  list-customers");
        System.out.println("  find-customer KEYWORD");
        System.out.println("  view-customer INDEX");
        System.out.println("  updatecustomer INDEX [/n NAME] [/p PHONE] [/a ADDRESS]");

        System.out.println("\n--- General Commands ---");
        System.out.println("  help");
        System.out.println("  exit");

        System.out.println("\n=======================================================");
    }
}
