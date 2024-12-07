package com.app.web.servicio;

import java.util.*;

public class Graph {
    private final Map<Integer, Map<Integer, Integer>> adjacencyList = new HashMap<>();

    public void addEdge(Integer from, Integer to, Integer weight) {
        adjacencyList.putIfAbsent(from, new HashMap<>());
        adjacencyList.get(from).put(to, weight);
    }

    public Map<Integer, Integer> getEdges(Integer node) {
        return adjacencyList.getOrDefault(node, Collections.emptyMap());
    }
}
