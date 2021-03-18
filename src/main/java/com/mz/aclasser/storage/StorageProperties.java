package com.mz.aclasser.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "/root/aclasser/images";
    private String thumLocation = location + "/thumbs";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getThumLocation() {
        return thumLocation;
    }

    public void setThumLocation(String thumLocation) {
        this.thumLocation = thumLocation;
    }
}
