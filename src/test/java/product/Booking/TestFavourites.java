package product.Booking;

import common.setup.Hooks;
import org.testng.annotations.Test;

import static product.Booking.steps.SpecSteps.*;

 /*
 * Tests Favourites feature
 */

public class TestFavourites {

    @Test
    public void testListOfBooks() {
        Hooks.setup("BookingServiceFavourites", "QA");
        getlistOfBooksAvailable();
        get1stBookAvailable();
        Hooks.tearDown();
    }

    /*
    @Test
    public void testMyFavourite(){
        Hooks.setup("BookingServiceFavourites", "QA");
        getlistOfBooksAvailable();
        get1stBookAvailable();
        getTokenForAutorizedUser();
        addBookToList();
        verifyBookAdded();
        removeBookFromList();
        verifyBookRemoved();
        Hooks.tearDown();
    }
    */

}
