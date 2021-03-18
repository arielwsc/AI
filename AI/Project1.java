package AI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Project1{
    boolean cutoff_occurred;

    /*
    public void DepthLimitedSearch(Problem problem, int limit){
        return RecursiveDLS()
    }

    public void RecursiveDLS(Node node, Problem problem, int limit){
        if (problem.goalTest(node.getState())){
            node.solution(node);
        }
        else if (limit == 0){
            System.out.println("Cutoff exceeded!");
        }
        else{
            cutoff_occurred = false;
        }
    }
    */
    public static void main(String[] args) throws FileNotFoundException{
        int[][] map = new int[10][10];
        
        Problem maze = new Problem(args[0], map, 1, 2, 3);

        maze.printMap();
    }

    private static void mapIO(String file, int[][] map, char mode) throws FileNotFoundException{
        Scanner inFile = new Scanner(new FileInputStream(file));

        if(mode == 'I'){
            for(int i=0; i < map.length; i++){
                for(int j=0; j < map.length; j++){
                    map[j][i] = inFile.nextInt();
                }
                if (!inFile.hasNext()) break;
                inFile.nextLine();
            }
            inFile.close();
        }
        else if(mode == 'O'){
            for(int i=0; i < map.length; i++){
                for(int j=0; j < map.length; j++){
                    System.out.print(map[j][i] + " ");
                }
                System.out.print("\n");
            }
        }
    }

}