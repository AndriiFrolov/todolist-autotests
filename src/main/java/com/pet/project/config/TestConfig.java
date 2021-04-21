package com.pet.project.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:environments/${env.file}.properties"})
public interface TestConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("app.key")
    String appKeyValue();

    @Key("symphony.user")
    String symphonyUser();

    @Key("client.id.secret.base64")
    String clientSecretBase64();

    @Key("use.gateway.path")
    boolean useGatewayPath();

}

