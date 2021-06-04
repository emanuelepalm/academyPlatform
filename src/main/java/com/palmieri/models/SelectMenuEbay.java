package com.palmieri.models;

import java.util.Map;
import java.util.Set;

public class SelectMenuEbay {
    private Map<String, String> optionMap;
    private String name;
    private String className;
    private String id;

    public SelectMenuEbay(Map<String, String> optionMap, String name, String className, String id) {
        this.optionMap = optionMap;
        this.name = name;
        this.className = className;
        this.id = id;
    }

    public Map<String, String> getOptionMap() {
        return optionMap;
    }

    public void setOptionMap(Map<String, String> optionMap) {
        this.optionMap = optionMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void print() {
        System.out.println("Name : " + this.name);
        System.out.println("Class : " + this.className);
        System.out.println("Id : " + this.id);

        for (String key : optionMap.keySet()) {
            System.out.println("Value : " + key);
            System.out.println("Testo : " + optionMap.get(key));
        }
    }
}
