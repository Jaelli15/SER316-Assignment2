import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Sample White-Box tests for the Checkout system.
 * This class demonstrates how to write white-box tests using:
 * - Control Flow Graph (CFG) analysis
 * - Statement coverage
 * - Branch coverage
 * - Path coverage
 *
 * White-box testing focuses on testing the IMPLEMENTATION by
 * examining the code structure and ensuring all paths are tested.
 */
public class CheckoutWhiteBoxSample {

    private Checkout checkout;

    @BeforeEach
    public void setUp() {
        checkout = new Checkout();
    }

    @Test
    @DisplayName("WB Test: countBooksByType - null type branch")
    public void testCountBooksByType_NullType() {
        // Branch: type == null → TRUE
        int result = checkout.countBooksByType(null, false);
        assertEquals(0, result, "Should return 0 for null type");
    }

    @Test 
    @DisplayName ("WB Test1: countBooksByType - null type returns 0")
    public void testCountBooksByType_nullType_returnsZero() {
        // Branch: type == null → TRUE
        int result = checkout.countBooksByType(null, true);
        assertEquals(0, result, "Should return 0 for null type");
    }

    @Test 
    @DisplayName("WB Test2: countBooksByType - empty inventory returns 0")
    public void testCountBooksByType_emptyInv_returnsZero() {
        // Branch: inventory is empty → TRUE
        int result = checkout.countBooksByType(Book.BookType.FICTION, true);
        assertEquals(0, result, "Should return 0 for empty inventory");
    }

    @Test 
    @DisplayName ("WB Test3: countBooksByType - onlyAvailable=true counts only available matches")
    public void testCountBooksByType_onlyAvail_mixedInv_countsAvailMatches() {
        addMixedInventory();
        assertEquals(1, checkout.countBooksByType(Book.BookType.FICTION, true), "Only fictionA is Fiction and available");
    }

    @Test 
    @DisplayName ("WB Test4: countBooksByType - onlyAvailable=false counts all matches")
    public void testCountBooksByType_onlyAvailableFalse_countsAll() {
        addMixedInventory();
        assertEquals(2, checkout.countBooksByType(Book.BookType.FICTION, false), "Both fictionA and fictionB count regardless of availability");
    }

    // Helper method to add a mix of books to the inventory
    private void addMixedInventory() {
        Book fictionA = new Book("978-0-1234-5678-9", "Fiction A", "Author A", Book.BookType.FICTION, 1);
        Book fictionB = new Book("978-0-9876-5432-1", "Fiction B", "Author B", Book.BookType.FICTION, 1);
        fictionB.checkout(); // Make fictionB unavailable
        Book nonFiction = new Book("978-0-1111-2222-3", "Non-Fiction", "Author C", Book.BookType.NONFICTION, 1);

        checkout.addBook(fictionA);
        checkout.addBook(fictionB);
        checkout.addBook(nonFiction);
        checkout.getInventory().put("NULL", null);
    }
}