package com.cdancy.jenkins.rest.features;

import com.cdancy.jenkins.rest.BaseJenkinsApiLiveTest;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test(groups = "live", testName = "CredentailsApiLiveTest", singleThreaded = true)
public class CredentialsApiLiveTest extends BaseJenkinsApiLiveTest {
    @Test
    public void testCredentialsCreate() {
        RequestStatus status = api.credentialsApi().create("_","{ \"credentials\": {\"scope\": \"GLOBAL\", \"username\": \"admin\", \"password\": \"admin\", \"id\": \"hbbaaa\", \"description\": \"aaaaaaahahahahahahaha\", \"stapler-class\": \"com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl\"}}");
        assertNotNull(status);
        assertTrue(status.errors().size()==0);
    }

    @Test
    public void testCredentialInfo() {
        RequestStatus status = api.credentialsApi().credentialInfo("_","hbbaaa");
        assertNotNull(status);
        assertTrue(status.errors().size()==0);
    }

    @Test
    public void testCredentialInfoFails() {
        RequestStatus status = api.credentialsApi().credentialInfo("_","hbbaaaa");
        assertNotNull(status);
        assertTrue(status.errors().size()>0);
    }
}
