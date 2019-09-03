package com.oranfish.sushiutil.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;


public class ConfigUtil {
    private static Properties properties = new Properties();
    private static Logger log = LoggerFactory.getLogger(ConfigUtil.class);
    private static final String CONFIG_DIR = "/data/config";

    private ConfigUtil() {
    }

    static{
        try{
            File directory = new File(CONFIG_DIR);
            if(directory.isDirectory()){
                File[] files = directory.listFiles();
                if(files.length>0){
                    for(File file : files){
                        if(file.getName().endsWith(".properties")){
                            properties.load(new FileReader(file));
                        }
                    }
                }
            }
        }catch (Exception e){
            log.error("ConfigUtil error : " + e.getMessage(),e);
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }
}
