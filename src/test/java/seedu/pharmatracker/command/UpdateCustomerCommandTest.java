package seedu.pharmatracker.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pharmatracker.customer.Customer;
import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.ui.Ui;

public class UpdateCustomerCommandTest {

    private Inventory inventory;
    private Ui ui;
    private CustomerList customerList;
    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
        ui = new Ui();
        customerList = new CustomerList();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    // -------------------------------------------------------------------------
    // Update individual fields
    // -------------------------------------------------------------------------

    @Test
    public void execute_updateNameOnly_nameChangedOthersUnchanged() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(1, "Bob", null, null).execute(inventory, ui, customerList);

        Customer updated = customerList.getCustomer(0);
        assertEquals("Bob", updated.getName());
        assertEquals("91234567", updated.getPhone());
        assertEquals("123 Main St", updated.getAddress());
    }

    @Test
    public void execute_updatePhoneOnly_phoneChangedOthersUnchanged() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(1, null, "99999999", null).execute(inventory, ui, customerList);

        Customer updated = customerList.getCustomer(0);
        assertEquals("Alice", updated.getName());
        assertEquals("99999999", updated.getPhone());
        assertEquals("123 Main St", updated.getAddress());
    }

    @Test
    public void execute_updateAddressOnly_addressChangedOthersUnchanged() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(1, null, null, "456 New Rd").execute(inventory, ui, customerList);

        Customer updated = customerList.getCustomer(0);
        assertEquals("Alice", updated.getName());
        assertEquals("91234567", updated.getPhone());
        assertEquals("456 New Rd", updated.getAddress());
    }

    @Test
    public void execute_updateAllFields_allFieldsChanged() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(1, "Bob", "88888888", "789 Other Ave")
                .execute(inventory, ui, customerList);

        Customer updated = customerList.getCustomer(0);
        assertEquals("Bob", updated.getName());
        assertEquals("88888888", updated.getPhone());
        assertEquals("789 Other Ave", updated.getAddress());
    }

    // -------------------------------------------------------------------------
    // Success output
    // -------------------------------------------------------------------------

    @Test
    public void execute_validUpdate_printsConfirmationMessage() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(1, "Bob", null, null).execute(inventory, ui, customerList);

        assertTrue(outContent.toString().contains("Customer updated:"));
    }

    // -------------------------------------------------------------------------
    // No fields provided
    // -------------------------------------------------------------------------

    @Test
    public void execute_noFieldsProvided_printsNoFieldsMessage() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(1, null, null, null).execute(inventory, ui, customerList);

        assertTrue(outContent.toString().contains("No fields provided to update"));
    }

    @Test
    public void execute_noFieldsProvided_customerUnchanged() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(1, null, null, null).execute(inventory, ui, customerList);

        Customer unchanged = customerList.getCustomer(0);
        assertEquals("Alice", unchanged.getName());
        assertEquals("91234567", unchanged.getPhone());
        assertEquals("123 Main St", unchanged.getAddress());
    }

    // -------------------------------------------------------------------------
    // Invalid index
    // -------------------------------------------------------------------------

    @Test
    public void execute_indexTooHigh_printsInvalidIndexMessage() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(99, "Bob", null, null).execute(inventory, ui, customerList);

        assertTrue(outContent.toString().contains("Invalid index"));
    }

    @Test
    public void execute_indexZero_printsInvalidIndexMessage() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(0, "Bob", null, null).execute(inventory, ui, customerList);

        assertTrue(outContent.toString().contains("Invalid index"));
    }

    @Test
    public void execute_negativeIndex_printsInvalidIndexMessage() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));

        new UpdateCustomerCommand(-1, "Bob", null, null).execute(inventory, ui, customerList);

        assertTrue(outContent.toString().contains("Invalid index"));
    }

    @Test
    public void execute_emptyCustomerList_printsInvalidIndexMessage() {
        new UpdateCustomerCommand(1, "Bob", null, null).execute(inventory, ui, customerList);

        assertTrue(outContent.toString().contains("Invalid index"));
    }

    // -------------------------------------------------------------------------
    // Second customer in list
    // -------------------------------------------------------------------------

    @Test
    public void execute_updateSecondCustomer_correctCustomerUpdated() {
        customerList.addCustomer(new Customer("C001", "Alice", "91234567", "123 Main St"));
        customerList.addCustomer(new Customer("C002", "Bob", "87654321", "456 Other St"));

        new UpdateCustomerCommand(2, "Charlie", null, null).execute(inventory, ui, customerList);

        assertEquals("Alice", customerList.getCustomer(0).getName());
        assertEquals("Charlie", customerList.getCustomer(1).getName());
    }
}
