package com.alipay.hbaseviewer.main;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alipay.hbaseviewer.helper.AppConstant;

/**
 * simplehbase viewer's main entry.
 * 
 * <pre>
 * URL: http://localhost:4040/hbaseviewer/
 * </pre>
 * 
 * @author xinzhi.zhang
 * */
public class Main {

    private static Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) throws Exception {

        String currentDir = new File(".").getCanonicalPath();
        String tomcatDir = currentDir + File.separatorChar + "tomcat";
        String webRoot = currentDir + File.separatorChar
                + AppConstant.WebAppName;

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(tomcatDir);
        tomcat.setPort(4040);
        tomcat.addWebapp("/" + AppConstant.WebAppName, webRoot);

        tomcat.start();
        log.info("----------------------------------");
        log.info("simplehbaseviewer URL:");
        log.info("http://localhost:4040/hbaseviewer/");
        log.info("----------------------------------");
        tomcat.getServer().await();

    }

}
