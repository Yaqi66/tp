package seedu.pharmatracker.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import seedu.pharmatracker.customer.Customer;
import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.exceptions.PharmaTrackerException;
import seedu.pharmatracker.ui.Ui;

public class DeleteCustomerCommandTest {

    private Inventory inventory;
    private Ui ui;
    private CustomerList customerList;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        ui = new Ui();
        customerList = new CustomerList();

        Customer dummyCustomer = new Customer("C001", "John Doe", "91234567", "123 Clementi Road");
        customerList.addCustomer(dummyCustomer);
    }

    @Test
    void constructor_validDescription_success() {
        assertDoesNotThrow(() -> new DeleteCustomerCommand("1"));
    }

    @Test
    void execute_validIndex_customerDeleted() throws PharmaTrackerException {
        assertEquals(1, customerList.getCustomerCount());

        DeleteCustomerCommand command = new DeleteCustomerCommand("1");

        command.execute(inventory, ui, customerList);

        assertEquals(0, customerList.getCustomerCount());
    }

    @Test
    void execute_nonNumericIndex_throwsPharmaTrackerException() {
        DeleteCustomerCommand command = new DeleteCustomerCommand("abc");

        PharmaTrackerException thrown = assertThrows(PharmaTrackerException.class,
                () -> command.execute(inventory, ui, customerList));

        assertEquals("Invalid format! Please enter a valid number for the customer index.",
                thrown.getMessage());
    }

    @Test
    void execute_nullCustomerList_throwsAssertionError() {
        DeleteCustomerCommand command = new DeleteCustomerCommand("1");

        assertThrows(AssertionError.class,
                () -> command.execute(inventory, ui, null));
    }

    @Test
    void execute_nullUi_throwsAssertionError() {
        DeleteCustomerCommand command = new DeleteCustomerCommand("1");

        assertThrows(AssertionError.class,
                () -> command.execute(inventory, null, customerList));
    }
}
