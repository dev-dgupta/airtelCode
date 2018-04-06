//package com.divya.ip.address;
//
//import com.divya.ip.address.utils.CommonUtility;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//
///**
// * Created by Divya.Gupta on 06-04-2018.
// */
//@Configuration
//@PropertySource("ipAllocation.properties")
//@ComponentScan({"com.divya.ip.address"})
//public class IpAllocationConfiguration {
//
//
//
//    public String buildNewIpAddress() {
//        StringBuffer str = new StringBuffer();
//        try {
//            int startNumber = Integer.parseInt(env.getProperty("firstIndexStartNumber"));
//            int endNumber = Integer.parseInt(env.getProperty("firstIndexEndNumber"));
//            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
//            startNumber = Integer.parseInt(env.getProperty("secondIndexStartNumber"));
//            endNumber = Integer.parseInt(env.getProperty("secondIndexEndNumber"));
//            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
//            startNumber = Integer.parseInt(env.getProperty("thirdIndexStartNumber"));
//            endNumber = Integer.parseInt(env.getProperty("thirdIndexEndNumber"));
//            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
//            startNumber = Integer.parseInt(env.getProperty("fourthIndexStartNumber"));
//            endNumber = Integer.parseInt(env.getProperty("fourthIndexEndNumber"));
//            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
//            startNumber = Integer.parseInt(env.getProperty("fifthIndexStartNumber"));
//            endNumber = Integer.parseInt(env.getProperty("fifthIndexEndNumber"));
//            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
//            startNumber = Integer.parseInt(env.getProperty("sixthIndexStartNumber"));
//            endNumber = Integer.parseInt(env.getProperty("sixthIndexEndNumber"));
//            str.append(CommonUtility.randomNumberInRange(startNumber, endNumber)).append(".");
//        } catch (NumberFormatException ne) {
//
//        }
//
//        return str.toString();
//    }
//}
