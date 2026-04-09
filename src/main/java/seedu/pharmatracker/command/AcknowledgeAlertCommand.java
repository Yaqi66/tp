package seedu.pharmatracker.command;

import seedu.pharmatracker.alert.RestockAlert;
import seedu.pharmatracker.alert.RestockAlertService;
import seedu.pharmatracker.core.AppServices;
import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.ui.Ui;

/**
 * Acknowledges a currently active alert by index.
 */
public class AcknowledgeAlertCommand extends Command {
    public static final String COMMAND_WORD = "ack-alert";

    private final int index;

    public AcknowledgeAlertCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Inventory inventory, Ui ui, CustomerList customerList) {
        RestockAlertService alertService = AppServices.getRestockAlertService();
        if (alertService == null) {
            ui.printMessage("Alert service is unavailable.");
            return;
        }

        RestockAlert acknowledged = alertService.acknowledgeActiveAlert(index);
        if (acknowledged == null) {
            ui.printMessage("Invalid alert index. Use 'alerts' to view active alerts.");
            return;
        }

        ui.printMessage("Acknowledged alert for " + acknowledged.getMedicationName()
                + " (stock: " + acknowledged.getCurrentStock() + ").");
    }
}
