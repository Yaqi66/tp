package seedu.pharmatracker.alert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Represents a restock alert for a medication below its configured threshold.
 */
public class RestockAlert {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final String id;
    private final String medicationKey;
    private final String medicationName;
    private int currentStock;
    private int threshold;
    private final LocalDateTime createdAt;
    private boolean acknowledged;
    private LocalDateTime acknowledgedAt;
    private String acknowledgmentNote;

    /**
     * Creates a new active alert.
     *
     * @param medicationKey Stable key used to identify medication in active alerts.
     * @param medicationName Medication display name.
     * @param currentStock Current stock quantity.
     * @param threshold Configured minimum threshold.
     */
    public RestockAlert(String medicationKey, String medicationName, int currentStock, int threshold) {
        this(UUID.randomUUID().toString(), medicationKey, medicationName, currentStock, threshold,
                LocalDateTime.now(), false, null, "");
    }

    /**
     * Reconstructs an alert from persisted data.
     */
    public RestockAlert(String id, String medicationKey, String medicationName, int currentStock,
                        int threshold, LocalDateTime createdAt, boolean acknowledged,
                        LocalDateTime acknowledgedAt, String acknowledgmentNote) {
        this.id = id;
        this.medicationKey = medicationKey;
        this.medicationName = medicationName;
        this.currentStock = currentStock;
        this.threshold = threshold;
        this.createdAt = createdAt;
        this.acknowledged = acknowledged;
        this.acknowledgedAt = acknowledgedAt;
        this.acknowledgmentNote = acknowledgmentNote;
    }

    public String getId() {
        return id;
    }

    public String getMedicationKey() {
        return medicationKey;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getThreshold() {
        return threshold;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public LocalDateTime getAcknowledgedAt() {
        return acknowledgedAt;
    }

    public String getAcknowledgmentNote() {
        return acknowledgmentNote;
    }

    public void updateCurrentStock(int newStock) {
        this.currentStock = newStock;
    }

    public void updateThreshold(int updatedThreshold) {
        this.threshold = updatedThreshold;
    }

    /**
     * Marks this alert as acknowledged and closes it.
     *
     * @param note Optional acknowledgment note.
     */
    public void acknowledge(String note) {
        this.acknowledged = true;
        this.acknowledgedAt = LocalDateTime.now();
        this.acknowledgmentNote = note == null ? "" : note;
    }

    public String getCreatedAtString() {
        return createdAt.format(FORMATTER);
    }

    public String getAcknowledgedAtString() {
        return acknowledgedAt == null ? "" : acknowledgedAt.format(FORMATTER);
    }

    /**
     * Parses an ISO local date-time string.
     */
    public static LocalDateTime parseDateTime(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(value.trim(), FORMATTER);
    }
}
