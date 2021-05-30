package com.exb.springboot;

import com.exb.springboot.exception.InvalidFormatException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Task2 extends AbstractTask{

    public boolean shareCommonAncestor(int[][] inputGraph, int[][] nodes) throws InvalidFormatException {

        try{
            Map<Integer, List<Integer>> adjacencyMap = new HashMap<>(generateAdjacencyPair(inputGraph).getLeft());

            /* Using a stack to do a DFS */

            if(!adjacencyMap.containsKey(nodes[0][0])){
                /* nodes isn't present in the adjacency map */
                return false;
            }
            if(!adjacencyMap.containsKey(nodes[0][1])){
                /* nodes isn't present in the adjacency map */
                return false;
            }

            List<Integer> connectedNodesList1 = getTraversedVertexList(nodes[0][0], adjacencyMap);
            List<Integer> connectedNodesList2 = getTraversedVertexList(nodes[0][1], adjacencyMap);

            /* Storing common elements from the two lists in connectedNodesList1 */
            connectedNodesList1.retainAll(connectedNodesList2);

            if(connectedNodesList1.size() >= 1){
                return true;
            }

            return false;
        }catch(Exception e){
            throw new InvalidFormatException(e.getMessage());
        }
    }

    private List<Integer> getTraversedVertexList(int node, Map<Integer, List<Integer>> map){
        List<Integer> traversedVertexList = new ArrayList<>();
        Stack<Integer> dfsStack = new Stack<>();
        for(Integer vertex : map.get(node)){
            dfsStack.push(vertex);
        }
        while(!dfsStack.isEmpty()){
            int vertexToBeTraversed = dfsStack.pop();
            traversedVertexList.add(vertexToBeTraversed);
            if(map.containsKey(vertexToBeTraversed)){
                List<Integer> vertexListFromStack = map.get(vertexToBeTraversed);
                for(Integer vertex : vertexListFromStack){
                    dfsStack.push(vertex);
                }
            }
        }
        return traversedVertexList;
    }
}
