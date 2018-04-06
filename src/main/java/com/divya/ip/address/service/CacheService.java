package com.divya.ip.address.service;

import com.divya.ip.address.bean.IpStorageBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
@Component
public class CacheService {

    private static final Map<String, Object> ipCacheMap = new HashMap<String, Object>();

    public static boolean foundInIpCacheMap(String ipAddress) {
        return null != ipCacheMap.get(ipAddress);
    }

    public static void putInIpCacheMap(String ipAddress, IpStorageBean ipStorageBean) {
        ipCacheMap.put(ipAddress, ipStorageBean);
    }

    public static Object getFromIpCacheMap(String ipAddress) {
        return ipCacheMap.get(ipAddress);
    }
}
