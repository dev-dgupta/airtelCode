package com.divya.ip.address.bean;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
public class IpStorageBean {

    String ipAddress;
    String macAddress;
    long allocatedTime;
    long refreshTime;
    long expiryTime;

    public IpStorageBean() {
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

    public long getAllocatedTime() {
        return allocatedTime;
    }

    public void setAllocatedTime(long allocatedTime) {
        this.allocatedTime = allocatedTime;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
}
