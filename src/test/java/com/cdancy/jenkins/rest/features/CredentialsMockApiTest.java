package com.cdancy.jenkins.rest.features;

import com.cdancy.jenkins.rest.BaseJenkinsMockTest;
import com.cdancy.jenkins.rest.JenkinsApi;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Mock tests for the {@link com.cdancy.jenkins.rest.features.CrumbIssuerApi} class.
 */
@Test(groups = "unit", testName = "CredentialsMockApiTest")
public class CredentialsMockApiTest extends BaseJenkinsMockTest {
    public void testCreateCredentialsSuccess() throws Exception {
        MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setResponseCode(200));
        String credentialContext = "{ \"credentials\": {\"scope\": \"GLOBAL\", \"username\": \"admin\", \"password\": \"admin\", \"id\": \"haaaaaa\", \"description\": \"aaaaaaahahahahahahaha\", \"stapler-class\": \"com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl\"}}";
        JenkinsApi jenkinsApi = api(server.getUrl("/"));
        CredentialsApi api = jenkinsApi.credentialsApi();
        try {
            RequestStatus status = api.create("_",credentialContext);
            assertNotNull(status);
            assertTrue(status.errors().size() == 0);
            assertSentWithFormData(server, "POST"
                , "/credentials/store/system/domain/_/createCredentials"
                ,"json="+credentialContext, MediaType.TEXT_HTML);
        } finally {
            jenkinsApi.close();
            server.shutdown();
        }
    }
}
