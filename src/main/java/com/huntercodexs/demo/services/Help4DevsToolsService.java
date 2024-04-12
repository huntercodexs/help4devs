package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Slf4j
@Service
public class Help4DevsToolsService {

    public static String md5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    public static String guide(String tcn) {
        if (tcn == null || tcn.equals("")) {
            return UUID.randomUUID().toString();
        }
        return tcn;
    }

    public static void infoLog(String... inputs) {
        if (log.isInfoEnabled()) {
            for (String text : inputs) {
                log.info(text);
            }
        }
    }

    public static void errLog(String... inputs) {
        for (String text : inputs) {
            log.error(text);
        }
    }

    public static void debugLog(String... inputs) {
        if (log.isDebugEnabled()) {
            for (String text : inputs) {
                log.debug(text);
            }
        }
    }

    public static void stdout(String... inputs) {
        for (String text : inputs) {
            System.out.println(text);
        }
    }
}
