package com.divya.ip.address.service;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
public interface HeartBeatService {

    Boolean refresh(String macAddress,String allocatedIpAddr);


}
