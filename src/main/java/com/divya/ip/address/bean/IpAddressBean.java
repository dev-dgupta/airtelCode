package com.divya.ip.address.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpAddressBean implements Serializable {

    private String macAddress;
    private String ipAddress;
    private boolean macAddressFound;  // will take less memory, hence no wrapper taken
    private int status;

    public IpAddressBean() {
    }

    public IpAddressBean(String macAddress, String ipAddress, boolean macAddressFound, int status) {
        this.macAddress = macAddress;
        this.ipAddress = ipAddress;
        this.macAddressFound = macAddressFound;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isMacAddressFound() {
        return macAddressFound;
    }

    public void setMacAddressFound(boolean macAddressFound) {
        this.macAddressFound = macAddressFound;
    }
}
