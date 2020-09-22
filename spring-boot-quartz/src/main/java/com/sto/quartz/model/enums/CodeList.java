package com.sto.quartz.model.enums;

import java.util.Map;

public interface CodeList {

    Map<String, String> getMap(String bizType);
    
    Map<String, String> toMap();    
}
