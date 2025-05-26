package net.dotevolve.base.data;

import lombok.Data;

@Data
public class UiClientInfo {
    private String browserVersion;
    private String operatingSystem;
    private String browserName;
    private String deviceType;
    private boolean mobileDevice;
    private String clientUrl;
    private String time;
    private String ipAddress;
}
