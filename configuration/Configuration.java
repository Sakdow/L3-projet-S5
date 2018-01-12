/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Nathan Roche
 */
public class Configuration {
    
    public static Properties load (String fileName) throws IOException{
        try (InputStream input = new FileInputStream(fileName)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }
    }
    
    
}
