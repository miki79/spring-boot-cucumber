package com.slalom.demo.app;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepDefs extends CucumberRoot {
    private ResponseEntity<String> response; // output
    private String remoteIp;

    @Before
    public void resetRemoteIp() {
        remoteIp = null;
    }

    @When("^the client set the remote IP as (.*)$")
    public void the_client_set_remote_ip(String remoteIp) throws Throwable {
        this.remoteIp = remoteIp;
    }

    @When("^the client calls (.*)$")
    public void the_client_issues_GET(String url) throws Throwable {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (remoteIp != null) {
            headers.set("X-FORWARDED-FOR", remoteIp);
        }

        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        response = template.exchange(url, HttpMethod.GET, httpEntity, String.class);
    }

    @Then("^the client receives response status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " + response.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @Then("^the client receives body \"(.*)\"$")
    public void the_client_receives_body(String body) throws Throwable {
        assertThat("status body is incorrect : " + response.getBody(), response.getBody(), is(body));
    }
}
