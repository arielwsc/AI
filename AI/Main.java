package AI;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

class Main{
    public static int BreadthFirstSearch(Problem problem){
        Node node = new Node(null, problem.initStateIdx, "Start", 0);
        Node child;
        if (problem.goalTest(node.getState())){ //Check if initial state is indeed goal state
            node.solution(node);
            return 0;
        }
        Queue<Node> frontier = new LinkedList<>(); //a FIFO queue with node as the only element
        frontier.add(node);
        Queue<String> explored = new LinkedList<>(); //an empty set for states that have been explored

        while (!frontier.isEmpty()){
            node = frontier.poll(); //chooses the shallowest node in frontier
            explored.add(node.getStateString()); //add node's state to explored set

            //Explore each action as defined in the Problem class
            child = node.childNode(problem, node, "Left");
            if (!explored.contains(child.getStateString())){
                if (problem.goalTest(child.getState())){
                    child.solution(child);
                    return 0;
                }
                frontier.add(child);
                explored.add(child.getStateString());
            }
            child = node.childNode(problem, node, "Right");
            if (!explored.contains(child.getStateString())){
                if (problem.goalTest(child.getState())){
                    child.solution(child);
                    return 0;
                }
                frontier.add(child);
                explored.add(child.getStateString());
            }
            child = node.childNode(problem, node, "Up");
            if (!explored.contains(child.getStateString())){
                if (problem.goalTest(child.getState())){
                    child.solution(child);
                    return 0;
                }
                frontier.add(child);
                explored.add(child.getStateString());
            }
            child = node.childNode(problem, node, "Down");
            if (!explored.contains(child.getStateString())){
                if (problem.goalTest(child.getState())){
                    child.solution(child);
                    return 0;
                }
                frontier.add(child);
                explored.add(child.getStateString());
            }
        }
        return 1;
    }
    
    public static void main(String[] args) throws FileNotFoundException{
        int[][] map = new int[10][10];
        
        Problem maze = new Problem(args[0], map, 1, 2, 3);
        /*
        System.out.print("Starting point: (" + maze.initStateIdx[0] + ", " + maze.initStateIdx[1] + ")" + "\n");
        System.out.println("Goal state on coord: (" + maze.goalStateIdx[0] + ", " + maze.goalStateIdx[1] + ")");
        */
        int pathWay = BreadthFirstSearch(maze);

        if(pathWay == 0){
            System.out.println("Intelligent Agent found path with success!");
        }
        else{
            System.out.println("Intelligent Agent failed to find pathway!");
        }

    }
}