package com.example.mobipubsub.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * Created by vlad on 01.06.2015.
 */
@Api(name = "news", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.mobipubsub.example.com", ownerName = "backend.mobipubsub.example.com", packagePath = ""))
public class NewsEndpoint {
    private static final Logger log = Logger.getLogger(RegistrationEndpoint.class.getName());



}

