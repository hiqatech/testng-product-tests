package product.Booking;

import common.setup.Hooks;
import org.testng.annotations.Test;

import static product.Booking.steps.SpecSteps.*;

/***
 * Tests Favourites feature
 */

public class TestFavourites {

    @Test
    public void testFavourites(){

        Hooks.setup("BookingServiceFavourites","QA");
        getTokenForAutorizedUser();
        getlistOfBooksAvailable();
        get1stBookFromTheStoreAvailable();
        addBookToList();
        verifyBookAdded();
        removeBookFromList();
        verifyBookRemoved();

        Hooks.tearDown();
    }

}
