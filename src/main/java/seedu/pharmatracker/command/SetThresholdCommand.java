package seedu.pharmatracker.command;

import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.data.Medication;
import seedu.pharmatracker.ui.Ui;

/**
 * Sets a medication-specific minimum stock threshold for auto restock alerts.
 */
public class SetThresholdCommand extends Command {
    public static final String COMMAND_WORD = "set-threshold";

    private final int index;
    private final int threshold;

    public SetThresholdCommand(int index, int threshold) {
        this.index = index;
        this.threshold = threshold;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, CustomerList customerList) {
        if (threshold <= 0) {
            ui.printMessage("Threshold must be a positive number.");
            return;
        }

        if (index < 1 || index > inventory.getMedicationCount()) {
            ui.printMessage("Invalid index. Please enter a number between 1 and "
                    + inventory.getMedicationCount() + ".");
            return;
        }

        Medication medication = inventory.getMedication(index - 1);
        medication.setMinimumStockThreshold(threshold);
        ui.printMessage("Updated minimum stock threshold for " + medication.getName()
                + " to " + threshold + " units.");
    }
}
