package product.Booking.steps;

import common.service.ServiceHelp;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

public class SpecSteps {

    private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
    private static final String USERNAME = "TOOLSQA-Test";
    private static final String PASSWORD = "Test@@123";
    private static String bookId;

    public static void getTokenForAutorizedUser() {
        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        ServiceHelp.response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .post("/Account/v1/GenerateToken");
        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
        String jsonString = ServiceHelp.response.asString();
        ServiceHelp.token = JsonPath.from(jsonString).get("token");
        System.out.println("Token : " + ServiceHelp.token);
    }

    public static void getlistOfBooksAvailable() {
        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();
        ServiceHelp.response = request.get("/BookStore/v1/Books");
        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
        ServiceHelp.jsonString = ServiceHelp.response.asString();
        List<Map<String, String>> books = JsonPath.from(ServiceHelp.jsonString).get("books");
        Assert.assertTrue(books.size() > 0);
        System.out.println("Books : " + books);
    }

    public static void get1stBookAvailable() {
        ServiceHelp.jsonString = ServiceHelp.response.asString();
        List<Map<String, String>> books = JsonPath.from(ServiceHelp.jsonString).get("books");
        Assert.assertFalse(books.isEmpty());
        bookId = books.get(0).get("isbn");
        System.out.println("BookID : " + bookId);
    }

    public static void addBookToList() {
        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + ServiceHelp.token)
                .header("Content-Type", "application/json");

        ServiceHelp.response = request.body("{ \"userId\": \"" + USER_ID + "\", " +
                        "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
                .post("/BookStore/v1/Books");
        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
    }

    public static void verifyBookAdded() {
        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
        Assert.assertEquals(201, ServiceHelp.response.getStatusCode());
    }

    public static void removeBookFromList() {
        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + ServiceHelp.token)
                .header("Content-Type", "application/json");

        ServiceHelp.response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + USER_ID + "\"}")
                .delete("/BookStore/v1/Book");

        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
    }

    public static void verifyBookRemoved() {
        Assert.assertEquals(ServiceHelp.response.getStatusCode(), 204);

        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + ServiceHelp.token)
                .header("Content-Type", "application/json");

        ServiceHelp.response = request.get("/Account/v1/User/" + USER_ID);
        Assert.assertEquals(ServiceHelp.response.getStatusCode(), 200);

        ServiceHelp.jsonString = ServiceHelp.response.asString();
        List<Map<String, String>> booksOfUser = JsonPath.from(ServiceHelp.jsonString).get("books");
        Assert.assertEquals(booksOfUser.size(), 0);
    }

}
