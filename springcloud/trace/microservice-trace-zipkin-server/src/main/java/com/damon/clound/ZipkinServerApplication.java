package com.damon.clound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * 
 * FileName: ZipkinServerApplication
 *
 * @author caobo
 * @create 2018-11-5 21:21
 * @since 1.0.0
 * Description:
 */
@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class,args);
    }
}
