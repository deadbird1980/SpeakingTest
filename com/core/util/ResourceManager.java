package com.core.util;

import java.util.*;

public class ResourceManager {

    public static Object getTestResource(String key) {
        try {

            PropertyResourceBundle resources = (PropertyResourceBundle)
                ResourceBundle.getBundle("com.core.test.test");

            return (String)(resources.getObject(key));
        } catch (MissingResourceException mre) {
            System.out.println(mre);
            System.exit(1);
        }

        return null;
    }
}

