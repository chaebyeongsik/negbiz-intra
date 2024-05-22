/*
 * Copyright (c) 2010 ZES Inc. All rights reserved. This software is the
 * confidential and proprietary information of ZES Inc. You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with ZES Inc.
 * (http://www.zesinc.co.kr/)
 */
package zesinc.intra.accessCtrl;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import zesinc.core.lang.Validate;

public class AccesCtrlSupport {
    /**
     * IP 주소변환
     *
     * @param ipAddr IP ADDRESS
     * @return
     */
    public final static long getExchgIp(String ipAddr) {

        if(Validate.isEmpty(ipAddr)) {
            return -1;
        }

        String[] ipAddrs = StringUtils.split(ipAddr, ".");

        if(Validate.isEmpty(ipAddrs)) {
            return -1;
        }

        String[] ipArr = ipAddr.split("\\.");
        long ipAddress;
        long ipAddressLong = 0;

        if(ipAddrs.length == 1) {
            ipAddressLong = (Long.parseLong(ipArr[0]) << 24);
        }else if(ipAddrs.length == 2) {
            ipAddressLong = (Long.parseLong(ipArr[0]) << 24) + (Long.parseLong(ipArr[1]) << 16);
        }else if(ipAddrs.length == 3) {
            ipAddressLong = (Long.parseLong(ipArr[0]) << 24) + (Long.parseLong(ipArr[1]) << 16) + (Long.parseLong(ipArr[2]) << 8);
        }else if(ipAddrs.length == 4) {
            ipAddressLong = (Long.parseLong(ipArr[0]) << 24) + (Long.parseLong(ipArr[1]) << 16) + (Long.parseLong(ipArr[2]) << 8) + (Long.parseLong(ipArr[3]));
        }

        ipAddress = ipAddressLong;

        return ipAddress;
    }
}
