package com.sy.jgrapht;

import com.sy.jgrapht.model.Task;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphSorter {

    public static void main(String[] arg){

        Graph<Task, DefaultEdge> testGraph  = new SimpleDirectedGraph<>(DefaultEdge.class);
        Task task1 = new Task("task1");
        Task task2 = new Task("task2");
        Task task3 = new Task("task3");
        Task task4 = new Task("task4");
        Task task5 = new Task("task5");
        testGraph.addVertex(task1);
        testGraph.addVertex(task2);
        testGraph.addVertex(task3);
        testGraph.addVertex(task4);
        testGraph.addVertex(task5);

        DefaultEdge edge = new DefaultEdge();

        testGraph.addEdge(task1,task4);
        testGraph.addEdge(task1,task3);
        testGraph.addEdge(task2,task3);
        testGraph.addEdge(task3,task5);

        System.out.println(testGraph.toString());

        Set<DefaultEdge> defaultEdges = testGraph.edgeSet();

        // Finding Root Tasks
        List<Task> sourceNodes = defaultEdges.stream().map(testGraph::getEdgeSource).collect(Collectors.toList());
        List<Task> targetNodes = defaultEdges.stream().map(testGraph::getEdgeTarget).collect(Collectors.toList());

        Set<Task> roots = sourceNodes.stream().filter(e -> !targetNodes.contains(e)).collect(Collectors.toSet());
        System.out.println("Roots " + roots);

        int i =0, j;

        List<Task> masterList = new LinkedList<>();

        for(Task root: roots){
            i++; j = 0;

            DepthFirstIterator<Task, DefaultEdge> depthFirstIterator = new DepthFirstIterator<>(testGraph, root);

            List<Task> list = new LinkedList<>();

            // If a task already appeared in previous graphs, add the previous tasks of this graph to the earlier graph at the position of this task in the earlier graph
            while (depthFirstIterator.hasNext()) {
                j++;
                Task next = depthFirstIterator.next();
                int s1 = masterList.indexOf(next);
                System.out.println("Task: " + next);
                if(s1 >= 0){
                    System.out.println("Index found: " + s1);
                    masterList.addAll(s1,list);
                    next.updateOrder(","+i+"."+j);
                    list.clear();
                } else {
                    list.add(next);
                    next.setOrder(i+"."+j);
                }
            }
            masterList.addAll(list);
            list.clear();
            System.out.println(masterList);
        }
        System.out.println(masterList);
    }

}
