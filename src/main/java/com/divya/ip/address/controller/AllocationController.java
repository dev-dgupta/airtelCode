package com.divya.ip.address.controller;

import com.divya.ip.address.bean.IpAddressBean;
import com.divya.ip.address.service.AllocationService;
import com.divya.ip.address.service.HeartBeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Divya.Gupta on 06-04-2018.
 */
@RestController
public class AllocationController {


    @Autowired
    AllocationService allocationService;

    @Autowired
    HeartBeatService heartBeatService;

    @RequestMapping(value = "/getIpAddr", method = RequestMethod.GET, produces = "application/json")
    public IpAddressBean getIpAddr(HttpServletRequest request, HttpServletResponse response) {
        String macAddress = request.getParameter("macAddress");
        return allocationService.allocate(macAddress);
    }

    @RequestMapping(value = "/refreshIpAddr", method = RequestMethod.GET, produces = "application/json")
    public Boolean refreshIpAddr(HttpServletRequest request, HttpServletResponse response) {
        String macAddress = request.getParameter("macAddress");
        String allocatedIpAddr = request.getParameter("allocatedIpAddr");
        return heartBeatService.refresh(macAddress, allocatedIpAddr);
    }
}
