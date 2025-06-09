package com.juaracoding.fantastic4_rest_api.utils;


import com.juaracoding.fantastic4_rest_api.config.OtherConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingFile {
    private static StringBuilder sBuild = new StringBuilder();

    private static Logger logger = LogManager.getLogger(LoggingFile.class);

    public static void logException(String strClass, String strMethod,
                                    Exception e) {
//        if(OtherConfig.getEnableLogFile().equals("y")){
//            sBuild.setLength(0);
//            logger.error(sBuild.append(System.getProperty("line.separator")).
//                    append("ERROR IN CLASS ==> ").append(strClass).append(System.getProperty("line.separator")).
//                    append("METHOD ==> ").append(strMethod).append(System.getProperty("line.separator")).
//                    append("ERROR IS ==> ").append(e.getMessage()).append(System.getProperty("line.separator")));
//        }
    }

    public static void logException(String strClass, String strMethod) {
        if(OtherConfig.getEnableLogFile().equals("y")){
            sBuild.setLength(0);
            logger.error(sBuild.append(System.getProperty("line.separator")).
                    append("ERROR IN CLASS ==> ").append(strClass).append(System.getProperty("line.separator")).
                    append("METHOD ==> ").append(strMethod).append(System.getProperty("line.separator")).
                    append("ERROR IS ==> ").append(System.getProperty("line.separator")));
        }
    }
}