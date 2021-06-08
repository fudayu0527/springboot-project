package com.xiaojiangtun.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 *
 */
@Slf4j
public class HostUtil {

    public static boolean canExecuteJob(List<String> hostNameList) {
        if (CollectionUtils.isEmpty(hostNameList)) {
            return false;
        }
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            log.info("该机器hostName为: {}", hostName);
            if (hostNameList.contains(hostName)) {
                return true;
            }
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

}
