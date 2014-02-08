package com.alipay.hbaseviewer.main;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

import com.alipay.hbaseviewer.helper.AppConstant;

public class Main {

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
        tomcat.getServer().await();
    }

}
