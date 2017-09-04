package com.wdg.stream;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class Main {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name", "wdg");
        map.put("age", 123);
        map.put("id",2);

        Object name = map.get("name");
        Object age =map.get("age");
        System.out.println(name instanceof String);
        System.out.println(age instanceof String);

        String str = "name:wdg;age:123;id:2";
        Map<String, String> strMap = dictToMap(str, ":", ";");
        System.out.println(strMap);
        Stream.of(strMap).forEach(new Consumer<Map<String, String>>() {
            @Override
            public void accept(Map<String, String> map) {

            }
        });



        System.out.println("==========================================================================");
        Map<String, Object> strMap2 = dictToMap2(str, ":", ";");
        System.out.println(strMap2);
        System.out.println(strMap2.get("id") instanceof Integer);
        Object id = strMap2.get("id");
        strMap2.put("id",Integer.parseInt(id.toString()));
        System.out.println(strMap2);
        System.out.println(strMap2.get("id") instanceof Integer);

    }

    public static Map<String, String> dictToMap(String target, String kvSep, String sectionSep) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isBlank(target)) {
            return map;
        }
        String[] strList = target.split(sectionSep);
        for (String line : strList) {
            if (StringUtils.isBlank(line))
                continue;
            StringBuilder sb = new StringBuilder(line);
            int index = sb.indexOf(kvSep);
            if (index == -1)
                continue;
            String key = sb.substring(0, index);
            String value = sb.substring(index + 1);
            map.put(key, value);
        }
        return map;
    }
    public static Map<String, Object> dictToMap2(String target, String kvSep, String sectionSep) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(target)) {
            return map;
        }
        String[] strList = target.split(sectionSep);
        for (String line : strList) {
            if (StringUtils.isBlank(line))
                continue;
            StringBuilder sb = new StringBuilder(line);
            int index = sb.indexOf(kvSep);
            if (index == -1)
                continue;
            String key = sb.substring(0, index);
            Object value = sb.substring(index + 1);
            map.put(key, value);
        }
        return map;
    }
}
