package com.cdancy.jenkins.rest.features;


import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.fallbacks.JenkinsFallbacks;
import com.cdancy.jenkins.rest.filters.JenkinsAuthenticationFilter;
import com.cdancy.jenkins.rest.parsers.OptionalFolderPathParser;
import com.cdancy.jenkins.rest.parsers.RequestStatusParser;
import org.jclouds.Fallbacks;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.rest.annotations.*;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestFilters(JenkinsAuthenticationFilter.class)
@Path("/credentials")
public interface CredentialsApi {

    @Named("credentials:create")
    @Path("/store/system/domain/{domain}/createCredentials")
    @Fallback(JenkinsFallbacks.RequestStatusOnError.class)
    @ResponseParser(RequestStatusParser.class)
    @Consumes(MediaType.TEXT_HTML)
    @POST
    RequestStatus create(@PathParam("domain") String domainName,
                         @FormParam("json") String credentials);

    @Named("credentials:create")
    @Path("/store/system/domain/{domain}/credential/{name}/")
    @Fallback(JenkinsFallbacks.RequestStatusOnError.class)
    @ResponseParser(RequestStatusParser.class)
    @Consumes(MediaType.TEXT_HTML)
    @GET
    RequestStatus credentialInfo(@PathParam("domain") String domainName,
                         @PathParam("name") String credentialName);
}
