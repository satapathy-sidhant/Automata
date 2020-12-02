package com.sidhant.automata.config;

public class CommonConfig {

    private final App1Config app1Config;
    private final App1ServerConfig serverConfig;

    public CommonConfig(App1Config config, App1ServerConfig sever) {
        this.app1Config = config;
        this.serverConfig = sever;
    }

    public App1Config getAppConfig(){
        return app1Config;
    }

    public App1ServerConfig getAppServerConfig() {
        return serverConfig;
    }
}
