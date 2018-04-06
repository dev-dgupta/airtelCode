package com.divya.ip.address.service.impl;

import com.divya.ip.address.bean.IpStorageBean;
import com.divya.ip.address.service.CacheService;
import com.divya.ip.address.service.HeartBeatService;
import com.divya.ip.address.utils.CommonUtility;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
@Service
@Component
public class HeartBeatServiceImpl implements HeartBeatService {
    //    @Override
    public Boolean refresh(String macAddress, String allocatedIpAddr) {
        if (CommonUtility.chkNull(macAddress) || CommonUtility.chkNull(allocatedIpAddr)) {
            return false;
        }

        if (CacheService.foundInIpCacheMap(allocatedIpAddr)) {
            // here, we will check whether the IP address has been expired or not.
            // if expired, the new mac Address starts serving this IP and
            // the previous one is assumed to be in inactive state, as any request to refresh the IP has yet not been received.
            // the previous mac address will receive new IP address on new request
            IpStorageBean ipStorageBean = (IpStorageBean) CacheService.getFromIpCacheMap(allocatedIpAddr);
            // if the difference between the system's current time and the refresh time is greater than the expiry time,
            // then the IP has been expired for the previous client.
            //else a new refresh time

            if (CommonUtility.ipHasBeenExpired(ipStorageBean.getRefreshTime(), ipStorageBean.getExpiryTime())) {
                ipStorageBean.setRefreshTime(System.currentTimeMillis());
                ipStorageBean.setAllocatedTime(System.currentTimeMillis());
                ipStorageBean.setMacAddress(macAddress);
            } else {
                ipStorageBean.setRefreshTime(System.currentTimeMillis());
            }
            CacheService.putInIpCacheMap(allocatedIpAddr, ipStorageBean);
            return true;
        }
        return false;
    }
}
