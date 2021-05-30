package com.exb.springboot;

import com.exb.springboot.exception.InvalidFormatException;
import org.apache.commons.lang3.tuple.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Task1 extends AbstractTask{

    public Pair baseAlgorithm(int[][] inputGraph) throws InvalidFormatException {
        try{
            Map<Integer, List<Integer>> adjacencyMap = new HashMap<>(generateAdjacencyPair(inputGraph).getLeft());
            List<Integer> allVertices = new ArrayList<>(generateAdjacencyPair(inputGraph).getRight());

            /* all keys which aren't present in the allVertices list have zero parents */
            Set<Integer> keySet = adjacencyMap.keySet();
            List<Integer> zeroParents = new ArrayList<>();
            Iterator<Integer> allVerticesIterator = allVertices.iterator();
            while (allVerticesIterator.hasNext()) {
                int vertex = allVerticesIterator.next();
                if (!keySet.contains(vertex)) {
                    zeroParents.add(vertex);
                }
            }

            /* all keys which have only one item in their respective vertex list have one parent */
            Set<Map.Entry<Integer, List<Integer>>> entries = adjacencyMap.entrySet();
            List<Integer> oneParent = entries.stream().filter(entry -> entry.getValue().size() < 2).map(
                    entry -> entry.getKey()).collect(Collectors.toList());
            Pair<List<Integer>, List<Integer>> returnPair = new ImmutablePair<>(zeroParents, oneParent);

            return returnPair;
        }
        catch(Exception e){
            throw new InvalidFormatException(e.getMessage());
        }
    }
}
