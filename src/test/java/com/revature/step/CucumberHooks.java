package com.revature.step;

import io.cucumber.java.Before;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
    Cucumber provides tools for executing some before/after
    scenarios are run. They are similar to the Junit setup
    and tear down options in that they can before/after a scenario
    or before/after each individual step
 */
public class CucumberHooks {

    // using the Cucumber Before annotation we can make our method run before each scenario
    @Before
    public void resetDatabase() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/reset"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpClient client = HttpClient.newHttpClient();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }



}
