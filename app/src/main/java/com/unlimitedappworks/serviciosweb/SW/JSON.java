/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unlimitedappworks.serviciosweb.SW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Luis Fernando
 */
public class JSON {

    public static List decodificar(String datos) {
        List<Object> list1 = new StringDivider(datos, "{", "}").getLista();
        List<HashMap<Object, Object>> list2 = new ArrayList<>();
        List<Object> list3;
        HashMap<Object, Object> hashMap;
        StringTokenizer st;
        for (Object listv : list1) {
            hashMap = new HashMap<>();
            if (listv.toString().contains("[")) {
                hashMap.put(new StringTokenizer(listv.toString().replaceAll("\\{", "")
                        .trim().replaceAll(String.valueOf('"'), ""), ":")
                        .nextToken(), decodificar(new StringDivider(listv.toString(), "[", "]")
                        .getLista().get(0).toString()));
            } else {
                st = new StringTokenizer(listv.toString().replaceAll("\\{", "").replaceAll("\\}", ""), ",");
                while (st.hasMoreElements()) {
                    String tmp = st.nextToken().trim();
                    list3 = new StringDivider(tmp, String.valueOf('"'), String.valueOf('"')).getLista();
                    String k = list3.get(0).toString().replaceAll(String.valueOf('"'), "").trim();
                    String v;
                    if (list3.size() > 1) {
                        v = list3.get(1).toString().replaceAll(String.valueOf('"'), "").trim();
                    } else {
                        v = tmp.substring(list3.get(0).toString().length() + 1).replaceAll(String.valueOf('"'), "").trim();
                    }
                    hashMap.put(k, v);
                }
            }
            list2.add(hashMap);
        }
        return list2;
    }

    public static boolean isNum(Object object) {
        try {
            Double.valueOf(object.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
