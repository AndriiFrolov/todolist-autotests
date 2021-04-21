package com.pet.project.db;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:environments/${env.file}.properties"})
public interface DatabaseConfiguration extends Config {

    @Key("db.url")
    String getDbUrl();

    @Key("db.user")
    String getDbUsername();

    @Key("db.password")
    String getDbPassword();

    @Key("db.driver.name")
    String getDriverName();

    @Key("db.name")
    String getDbName();
}

