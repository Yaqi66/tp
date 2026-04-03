package seedu.pharmatracker.command;

import org.junit.jupiter.api.Test;
import seedu.pharmatracker.customer.Customer;
import seedu.pharmatracker.customer.CustomerList;
import seedu.pharmatracker.data.Inventory;
import seedu.pharmatracker.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit tests for {@link FindCustomerCommand}.
 * Verifies correct output when searching for customers by name keyword.
 */
public class FindCustomerCommandTest {

    /**
     * Creates a default Inventory and Ui for use in tests.
     */
    private Inventory newInventory() {
        return new Inventory();
    }

    private Ui newUi() {
        return new Ui();
    }

    /**
     * Tests that a customer whose name exactly matches the keyword is found and displayed.
     */
    @Test
    public void execute_exactMatch_printsCustomer() {
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(new Customer("C001", "Alice Tan", "91234567", ""));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new FindCustomerCommand("Alice Tan").execute(newInventory(), newUi(), customerList);
        System.setOut(System.out);

        assertTrue(out.toString().contains("Alice Tan"));
    }

    /**
     * Tests that keyword matching is case-insensitive.
     */
    @Test
    public void execute_caseInsensitiveKeyword_printsCustomer() {
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(new Customer("C001", "Alice Tan", "91234567", ""));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new FindCustomerCommand("alice").execute(newInventory(), newUi(), customerList);
        System.setOut(System.out);

        assertTrue(out.toString().contains("Alice Tan"));
    }

    /**
     * Tests that a partial keyword still matches the correct customer.
     */
    @Test
    public void execute_partialKeyword_printsMatchingCustomer() {
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(new Customer("C001", "Alice Tan", "91234567", ""));
        customerList.addCustomer(new Customer("C002", "Bob Lim", "98765432", ""));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new FindCustomerCommand("Ali").execute(newInventory(), newUi(), customerList);
        System.setOut(System.out);

        String output = out.toString();
        assertTrue(output.contains("Alice Tan"));
        assertFalse(output.contains("Bob Lim"));
    }

    /**
     * Tests that multiple customers matching the keyword are all displayed.
     */
    @Test
    public void execute_multipleMatches_printsAllMatches() {
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(new Customer("C001", "Alice Tan", "91234567", ""));
        customerList.addCustomer(new Customer("C002", "Alice Lim", "98765432", ""));
        customerList.addCustomer(new Customer("C003", "Bob Koh", "81234567", ""));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new FindCustomerCommand("Alice").execute(newInventory(), newUi(), customerList);
        System.setOut(System.out);

        String output = out.toString();
        assertTrue(output.contains("Alice Tan"));
        assertTrue(output.contains("Alice Lim"));
        assertFalse(output.contains("Bob Koh"));
    }

    /**
     * Tests that searching with no matches prints the no-results message.
     */
    @Test
    public void execute_noMatch_printsNoMatchMessage() {
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(new Customer("C001", "Alice Tan", "91234567", ""));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new FindCustomerCommand("Zorro").execute(newInventory(), newUi(), customerList);
        System.setOut(System.out);

        assertTrue(out.toString().contains("Zorro"));
    }

    /**
     * Tests that searching an empty customer list prints the no-results message.
     */
    @Test
    public void execute_emptyCustomerList_printsNoMatchMessage() {
        CustomerList customerList = new CustomerList();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new FindCustomerCommand("Alice").execute(newInventory(), newUi(), customerList);
        System.setOut(System.out);

        assertTrue(out.toString().contains("Alice"));
    }

}
