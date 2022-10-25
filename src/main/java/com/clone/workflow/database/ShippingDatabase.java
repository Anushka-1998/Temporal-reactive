package com.clone.workflow.database;

import com.clone.workflow.domain.RouteDetails;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class ShippingDatabase {


    public static List<String> spaceCheck(List<String> routeDetails, Double containerSize){

        HashMap<String,Double> map=new HashMap<String,Double>();
        map.put("Rajasthan",30.0);
        map.put("Gujarat",40.0);
        map.put("Delhi",80.0);
        map.put("Maharashtra",90.0);
        map.put("Madhya Pradesh",20.0);
        map.put("Karnataka",80.0);
        map.put("Uttar Pradesh",20.0);

        List<String> routeList= new ArrayList<>();

       for(int i=0;i<routeDetails.size();i++){
           System.out.println("map.get(routeDetails.get(i) : "+map.get(routeDetails.get(i)));
           if(map.get(routeDetails.get(i)) >= containerSize){
               routeList.add(routeDetails.get(i));
           }
       }
       log.info("routeList : {}",routeList);
        return routeList;
    }

    public static RouteDetails getAvailableRoutes(String source, String destination){
        log.info("source: {} , destination : {}",source,destination);
        if(source.equals("Rajasthan") && destination.equals("Karnataka")){
            return   RouteDetails.builder()
                    .availableRoutes(Arrays.asList("Gujarat","Maharashtra","Madhya Pradesh"))
                    .build();
        }
        if(source.equals("Gujarat") && destination.equals("Karnataka")){
           return RouteDetails.builder()
                    .availableRoutes(Arrays.asList("Rajasthan","Maharashtra","Madhya Pradesh"))
                    .build();
        }
        if(source.equals("Delhi") && destination.equals("Karnataka")){
           return RouteDetails.builder()
                    .availableRoutes(Arrays.asList("Rajasthan","Maharashtra","Madhya Pradesh","Uttar Pradesh"))
                    .build();
        }

     return RouteDetails.builder()
        .availableRoutes(Arrays.asList())
        .build();
    }

    public static boolean getEquipmentAvailability(String containerType){
        List<String> containers = Arrays.asList("High Cube Dry","High Cube Reefer","Open Top",
                "Flat Standard","Flat High","Open Top High","Flat");
        return containers.contains(containerType) ? true: false;
    }

}
