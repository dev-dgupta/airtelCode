package com.divya.ip.address.service;

import com.divya.ip.address.bean.IpAddressBean;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
public interface AllocationService {

    IpAddressBean allocate(String macAddress);

}
