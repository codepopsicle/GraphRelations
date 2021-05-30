package com.exb.springboot;

import org.json.JSONArray;
import org.json.JSONObject;

public class ControllerUtils {

    public static int[][] getInputGraph(String inputJSON){
        JSONArray jsonArray = new JSONObject(inputJSON).getJSONObject("input_graph").getJSONArray("edge");
        int[][] inputArr = new int[jsonArray.length()][2];
        for(int i=0; i< jsonArray.length(); i++){
            inputArr[i][0] = Integer.parseInt(jsonArray.getJSONObject(i).get("v1").toString());
            inputArr[i][1] = Integer.parseInt(jsonArray.getJSONObject(i).get("v2").toString());
        }
        return inputArr;
    }
}
