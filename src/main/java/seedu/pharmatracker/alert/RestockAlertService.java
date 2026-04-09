package seedu.pharmatracker.alert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.data.Medication;

/**
 * Generates and tracks automatic restock alerts based on per-medication thresholds.
 */
public class RestockAlertService {
    private final Map<String, RestockAlert> activeAlertsByMedicationKey;
    private final ArrayList<RestockAlert> alertHistory;

    /**
     * Constructs an alert service with persisted history.
     *
     * @param persistedHistory Alert history from storage.
     */
    public RestockAlertService(List<RestockAlert> persistedHistory) {
        this.activeAlertsByMedicationKey = new LinkedHashMap<>();
        this.alertHistory = new ArrayList<>();

        if (persistedHistory != null) {
            this.alertHistory.addAll(persistedHistory);
            for (RestockAlert alert : persistedHistory) {
                if (!alert.isAcknowledged()) {
                    activeAlertsByMedicationKey.put(alert.getMedicationKey(), alert);
                }
            }
        }
    }

    /**
     * Scans inventory against configured thresholds and updates active alerts.
     *
     * @param inventory Current inventory.
     */
    public void evaluateInventory(Inventory inventory) {
        if (inventory == null) {
            return;
        }

        for (Medication medication : inventory.getMedications()) {
            String key = buildMedicationKey(medication);
            int quantity = medication.getQuantity();
            int threshold = medication.getMinimumStockThreshold();

            if (quantity < threshold) {
                RestockAlert activeAlert = activeAlertsByMedicationKey.get(key);
                if (activeAlert == null) {
                    RestockAlert newAlert = new RestockAlert(key, medication.getName(), quantity, threshold);
                    activeAlertsByMedicationKey.put(key, newAlert);
                    alertHistory.add(newAlert);
                } else {
                    activeAlert.updateCurrentStock(quantity);
                    activeAlert.updateThreshold(threshold);
                }
            } else {
                RestockAlert activeAlert = activeAlertsByMedicationKey.get(key);
                if (activeAlert != null && !activeAlert.isAcknowledged()) {
                    activeAlert.acknowledge("Auto-resolved: stock recovered above threshold.");
                    activeAlertsByMedicationKey.remove(key);
                }
            }
        }
    }

    /**
     * Acknowledges an active alert by one-based display index.
     *
     * @param index One-based index.
     * @return Acknowledged alert or null if index invalid.
     */
    public RestockAlert acknowledgeActiveAlert(int index) {
        ArrayList<RestockAlert> active = getActiveAlerts();
        if (index < 1 || index > active.size()) {
            return null;
        }
        RestockAlert selected = active.get(index - 1);
        selected.acknowledge("Acknowledged by user.");
        activeAlertsByMedicationKey.remove(selected.getMedicationKey());
        return selected;
    }

    /**
     * Returns current active alerts.
     *
     * @return List of active alerts in display order.
     */
    public ArrayList<RestockAlert> getActiveAlerts() {
        return new ArrayList<>(activeAlertsByMedicationKey.values());
    }

    /**
     * Returns full alert history.
     *
     * @return Persistable history list.
     */
    public ArrayList<RestockAlert> getAlertHistory() {
        return new ArrayList<>(alertHistory);
    }

    private String buildMedicationKey(Medication medication) {
        return medication.getName() + "::" + medication.getDosage();
    }
}
