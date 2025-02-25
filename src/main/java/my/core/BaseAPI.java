package my.core;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {
    public Response getRequest(String url) {
        return RestAssured.get(url);
    }
}
