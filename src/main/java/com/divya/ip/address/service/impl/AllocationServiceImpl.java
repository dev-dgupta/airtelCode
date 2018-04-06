package com.divya.ip.address.service.impl;

import com.divya.ip.address.bean.IpAddressBean;
import com.divya.ip.address.bean.IpStorageBean;
import com.divya.ip.address.service.AllocationService;
import com.divya.ip.address.service.CacheService;
import com.divya.ip.address.utils.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import org.springframework.stereotype.Component;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
@Service
@PropertySource("classpath:ipAllocation.properties")
public class AllocationServiceImpl implements AllocationService {

    @Autowired
    private Environment env;

    //    @Override
//    @HystrixCommand(fallbackMethod = "allocateEmpty")
    public IpAddressBean allocate(String macAddress) {
        if (CommonUtility.chkNull(macAddress)) {
            return new IpAddressBean(macAddress, "", false, 0);
        }
        String ipAddress = buildNewIpAddress();

        // to check the uniqueness of the IP, we have maintained a map that consists of all Ip's allocated uptill now,
        //along with their storage bean which consists of the allocation, refresh and expiry times of the same.
        // this will enable to keep the ällocated IP's leased and not assigned to a particular macAddres permanently.
        boolean foundInCache = CacheService.foundInIpCacheMap(ipAddress);
        IpStorageBean ipStorageBean;
        if (foundInCache) {
            // here, we will check whether the IP address has been expired or not.
            // if expired, the new mac Address starts serving this IP and
            // the previous one is assumed to be in inactive state, as any request to refresh the IP has yet not been received.
            // the previous mac address will receive new IP address on new request
            ipStorageBean = (IpStorageBean) CacheService.getFromIpCacheMap(ipAddress);
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
        } else {
            ipStorageBean = getIpStorageBean(ipAddress, false);
        }
        CacheService.putInIpCacheMap(ipAddress, ipStorageBean);
        return getIpAddressBean(macAddress, ipAddress);
    }

//    @SuppressWarnings("unused")
//    public IpAddressBean allocateEmpty(String macAddress) {
//        return new IpAddressBean(macAddress, "", false, 0);
//    }

    private IpStorageBean getIpStorageBean(String ipAddress, boolean foundInCache) {
        IpStorageBean ipStorageBean = new IpStorageBean();
        GregorianCalendar calendar = new GregorianCalendar();
        if (foundInCache) {
            ipStorageBean.setIpAddress(ipAddress);
            ipStorageBean.setRefreshTime(System.currentTimeMillis());
            ipStorageBean.setAllocatedTime(calendar.getTimeInMillis());
            ipStorageBean.setExpiryTime(Long.parseLong(env.getProperty("expiryTime")));
        } else {
            ipStorageBean.setIpAddress(ipAddress);
            ipStorageBean.setRefreshTime(0);
            ipStorageBean.setAllocatedTime(calendar.getTimeInMillis());
            ipStorageBean.setExpiryTime(Long.parseLong(env.getProperty("expiryTime")));
        }


        return ipStorageBean;
    }

    /**
     * generates the new IP Address for the given mac address
     * <p>
     * Ideally, there should be a DB object consisting of all these values and the number of DB table rows will specify
     * the length of the IP number. Whether it will be IPv4 or IPv6.
     * <p>
     * Currently taking IPv6
     *
     * @return IP Address
     */
    private String buildNewIpAddress() {
        StringBuffer str = new StringBuffer();
        try {
            int startNumber = Integer.parseInt(env.getProperty("firstIndexStartNumber"));
            int endNumber = Integer.parseInt(env.getProperty("firstIndexEndNumber"));
            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
            startNumber = Integer.parseInt(env.getProperty("secondIndexStartNumber"));
            endNumber = Integer.parseInt(env.getProperty("secondIndexEndNumber"));
            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
            startNumber = Integer.parseInt(env.getProperty("thirdIndexStartNumber"));
            endNumber = Integer.parseInt(env.getProperty("thirdIndexEndNumber"));
            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
            startNumber = Integer.parseInt(env.getProperty("fourthIndexStartNumber"));
            endNumber = Integer.parseInt(env.getProperty("fourthIndexEndNumber"));
            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber))/*.append(".")*/;
//            startNumber = Integer.parseInt(env.getProperty("fifthIndexStartNumber"));
//            endNumber = Integer.parseInt(env.getProperty("fifthIndexEndNumber"));
//            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
//            startNumber = Integer.parseInt(env.getProperty("sixthIndexStartNumber"));
//            endNumber = Integer.parseInt(env.getProperty("sixthIndexEndNumber"));
//            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber));
        } catch (NumberFormatException ne) {

        }

        return str.toString();
    }

    /**
     * generates the response bean
     *
     * @return the object containing the response
     */
    private IpAddressBean getIpAddressBean(String macAddress, String ipAddress) {
        IpAddressBean ipAddressBean = new IpAddressBean();
        ipAddressBean.setMacAddress(macAddress);
        ipAddressBean.setIpAddress(ipAddress);
        ipAddressBean.setStatus(1);
        ipAddressBean.setMacAddressFound(true);
        return ipAddressBean;
    }
}
