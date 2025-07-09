package product.Booking;

import common.selenium.WebHelp;
import common.setup.Hooks;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static product.Booking.steps.SpecSteps.*;

/***
 * Tests Favourites feature
 */

public class TestFavourites {

    @BeforeTest
    public void start(){
        Hooks.setup("BookingServiceFavourites", "QA");
        getTokenForAutorizedUser();
    }

    @Test
    public void testListOfBooks() {
        getlistOfBooksAvailable();
        get1stBookFromTheStoreAvailable();
    }

    /*
    @Test
    public void testMyFavourite(){
        testListOfBooks();
        addBookToList();
        verifyBookAdded();
        removeBookFromList();
        verifyBookRemoved();
    }
    */

    @AfterTest
    public void finish(){
        Hooks.tearDown();
    }

}
