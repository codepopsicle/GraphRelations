package com.exb.springboot;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class AbstractTask {

    public static Pair<Map<Integer, List<Integer>>, List<Integer>> generateAdjacencyPair(int[][] inputGraph){
        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        List<Integer> allVertices = new ArrayList<>();

        /* create map of edges to list of vertices */

        for (int i = 0; i < inputGraph.length; i++) {
            /* add vertices to list of all vertices */
            if (!allVertices.contains(inputGraph[i][0])) {
                allVertices.add(inputGraph[i][0]);
            }
            if (!allVertices.contains(inputGraph[i][1])) {
                allVertices.add(inputGraph[i][1]);
            }
            if (adjacencyMap.containsKey(inputGraph[i][1])) {
                List<Integer> vertexList = adjacencyMap.get(inputGraph[i][1]);
                vertexList.add(inputGraph[i][0]);
                adjacencyMap.put(inputGraph[i][1], vertexList);
            } else {
                List<Integer> vertexList = new ArrayList<>();
                vertexList.add(inputGraph[i][0]);
                adjacencyMap.put(inputGraph[i][1], vertexList);
            }
        }
        Pair<Map<Integer, List<Integer>>, List<Integer>> resultPair = new ImmutablePair<>(
                adjacencyMap, allVertices);
        return resultPair;
    }
}
