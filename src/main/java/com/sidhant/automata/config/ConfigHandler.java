package com.sidhant.automata.config;

import com.sidhant.automata.core.logger.MyLogger;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.files.FilesConfigurationSource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class ConfigHandler {

    private static final String CONFIG_PATH = System.getProperty("user.dir") + "/src/main/resources/";

    private ConfigHandler() {
    }

    public static String getConfig(String fileName) {
        return CONFIG_PATH + fileName;
    }

    private static Path getPathToConfig(String fileName) {
        return Paths.get(new File(getConfig(fileName)).toURI());
    }

    public static CommonConfig buildConfig(String fileName) {
        ConfigurationSource source = new FilesConfigurationSource(() -> Collections.singletonList(getPathToConfig(fileName)));
        ConfigurationProvider configProvider = new ConfigurationProviderBuilder().withConfigurationSource(source).build();
        App1ServerConfig server = configProvider.bind("server", App1ServerConfig.class);
        App1Config app1 = configProvider.bind("app1", App1Config.class);
        return new CommonConfig(app1, server);
    }

    public static void main(String[] args) {
        MyLogger.logList(ConfigHandler.buildConfig("config.app1.properties").getAppConfig().users(), "Users List", "main");
        MyLogger.logTable(ConfigHandler.buildConfig("config.app1.properties").getAppConfig().usernames(), "User Names", "main");
    }

}
