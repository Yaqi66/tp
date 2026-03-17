package seedu.pharmatracker.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MedicationTest {

    @Test
    public void medication_getName_returnsCorrectName() {
        Medication med = new Medication("Panadol", "500mg", 100, "2026-12-31", "painkiller");
        assertEquals("Panadol", med.getName());
    }

    @Test
    public void medication_getDosage_returnsCorrectDosage() {
        Medication med = new Medication("Panadol", "500mg", 100, "2026-12-31", "painkiller");
        assertEquals("500mg", med.getDosage());
    }

    @Test
    public void medication_getQuantity_returnsCorrectQuantity() {
        Medication med = new Medication("Panadol", "500mg", 100, "2026-12-31", "painkiller");
        assertEquals(100, med.getQuantity());
    }

    @Test
    public void medication_setQuantity_updatesQuantity() {
        Medication med = new Medication("Panadol", "500mg", 100, "2026-12-31", "painkiller");
        med.setQuantity(50);
        assertEquals(50, med.getQuantity());
    }

    @Test
    public void medication_getExpiryDate_returnsCorrectDate() {
        Medication med = new Medication("Panadol", "500mg", 100, "2026-12-31", "painkiller");
        assertEquals("2026-12-31", med.getExpiryDate());
    }

    @Test
    public void medication_getTag_returnsCorrectTag() {
        Medication med = new Medication("Ibuprofen", "200mg", 30, "2027-08-15", "antibiotic");
        assertEquals("antibiotic", med.getTag());
    }

    @Test
    public void medication_isExpired_past() {
        // Date in the past (before March 17, 2026)
        Medication med = new Medication("Aspirin", "500mg", 50, "2025-12-31", "painkiller");
        assertEquals(true, med.isExpired());
    }

    @Test
    public void medication_isExpired_future() {
        // Date in the future (after March 17, 2026)
        Medication med = new Medication("Paracetamol", "500mg", 100, "2026-12-31", "painkiller");
        assertEquals(false, med.isExpired());
    }

    @Test
    public void medication_toString_expired() {
        Medication med = new Medication("Expired Drug", "100mg", 10, "2025-01-01", "general");
        String result = med.toString();
        assertEquals(true, result.contains("[EXPIRED]"));
    }

    @Test
    public void medication_toString_valid() {
        Medication med = new Medication("Valid Drug", "100mg", 10, "2026-12-31", "general");
        String result = med.toString();
        assertEquals(false, result.contains("[EXPIRED]"));
    }
}
