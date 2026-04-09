package seedu.pharmatracker.command;

import java.util.ArrayList;

import seedu.pharmatracker.alert.RestockAlert;
import seedu.pharmatracker.alert.RestockAlertService;
import seedu.pharmatracker.core.AppServices;
import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.ui.Ui;

/**
 * Displays persisted restock alert history.
 */
public class AlertHistoryCommand extends Command {
    public static final String COMMAND_WORD = "alert-history";

    @Override
    public void execute(Inventory inventory, Ui ui, CustomerList customerList) {
        RestockAlertService alertService = AppServices.getRestockAlertService();
        if (alertService == null) {
            ui.printMessage("Alert service is unavailable.");
            return;
        }

        ArrayList<RestockAlert> history = alertService.getAlertHistory();
        ui.printAlertHistory(history);
    }
}
