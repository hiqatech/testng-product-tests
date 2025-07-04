package product.Booking.steps;

import common.service.ServiceHelp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @Given("I am an authorized user")
    public void iAmAnAuthorizedUser() {
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

    @Given("A list of books are available")
    public void listOfBooksAreAvailable() {
        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();
        ServiceHelp.response = request.get("/BookStore/v1/Books");
        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
        ServiceHelp.jsonString = ServiceHelp.response.asString();
        List<Map<String, String>> books = JsonPath.from(ServiceHelp.jsonString).get("books");
        Assert.assertTrue(books.size() > 0);
        bookId = books.get(0).get("isbn");
        System.out.println("BookID : " + bookId);
    }

    @Given("I get the 1st book from the store available")
    public void IGetThe1stBookFromTheStoreAvailable() {
        ServiceHelp.jsonString = ServiceHelp.response.asString();
        List<Map<String, String>> books = JsonPath.from(ServiceHelp.jsonString).get("books");
        Assert.assertTrue(books.size() > 0);
        bookId = books.get(0).get("isbn");
        System.out.println("BookID : " + bookId);
    }

    @When("I add a book to my reading list")
    public void addBookInList() {
        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + ServiceHelp.token)
                .header("Content-Type", "application/json");

        ServiceHelp.response = request.body("{ \"userId\": \"" + USER_ID + "\", " +
                        "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
                .post("/BookStore/v1/Books");
        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
    }

    @Then("The book is added")
    public void bookIsAdded() {
        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
        Assert.assertEquals(201, ServiceHelp.response.getStatusCode());
    }

    @When("I remove a book from my reading list")
    public void removeBookFromList() {
        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + ServiceHelp.token)
                .header("Content-Type", "application/json");

        ServiceHelp.response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + USER_ID + "\"}")
                .delete("/BookStore/v1/Book");

        System.out.println("Status received => " + ServiceHelp.response.getStatusLine());
    }

    @Then("The book is removed")
    public void bookIsRemoved() {
        Assert.assertEquals(204, ServiceHelp.response.getStatusCode());

        RestAssured.baseURI = System.getProperty("baseURL");
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + ServiceHelp.token)
                .header("Content-Type", "application/json");

        ServiceHelp.response = request.get("/Account/v1/User/" + USER_ID);
        Assert.assertEquals(200, ServiceHelp.response.getStatusCode());

        ServiceHelp.jsonString = ServiceHelp.response.asString();
        List<Map<String, String>> booksOfUser = JsonPath.from(ServiceHelp.jsonString).get("books");
        Assert.assertEquals(0, booksOfUser.size());

    }
}
