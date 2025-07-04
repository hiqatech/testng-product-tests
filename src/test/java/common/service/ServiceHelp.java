package common.service;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServiceHelp {

  public static String path = "";
  public static Response response;
  public static String token ;
  public static String jsonString ;


  public static String setRequestFullURL(String url){
    try{
      path = System.getProperty("baseURL") + url;
      return "PASS";
      }
    catch(Exception ex)
      {return  ex.toString();}
  }

  public static String sendRequestTo(String type, String path) {
    try {
          RestAssured.baseURI = System.getProperty("baseURL");
          RequestSpecification request = RestAssured.given();
          if(type.equals("GET")) {
            response = request.get(path);
          }
          System.out.println("Status received => " + response.getStatusLine());
          return "PASS";
    }
    catch(Exception ex)
      {return  ex.toString();}
  }

  public static String  getAuthTokenBy(String userName, String password) {
    try{
      RestAssured.baseURI = System.getProperty("baseURL");
      RequestSpecification request = RestAssured.given();

      request.header("Content-Type", "application/json");
      response = request.body("{ \"userName\":\"" + userName + "\", \"password\":\"" + password + "\"}")
            .post("/Account/v1/GenerateToken");

      String jsonString = response.asString();
      token = JsonPath.from(jsonString).get("token");
      System.out.println("Token received => " + token);
      return "PASS";
    }
    catch(Exception ex)
      {return  ex.toString();}
  }

  public static String getResponseStatusCode()
  {
    try{
     return String.valueOf(response.getStatusCode());
    }
    catch(Exception ex)
    {return  ex.toString();}
  }


}
