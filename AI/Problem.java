package AI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

    public class Problem {
        public int[][] map;
        public int initialState;
        public int[] initStateIdx;
        public int goalState;
        public int[] goalStateIdx;
        public int noGoalStates;
        public int wall;

        public Problem(String file, int[][] map, int initialState, int goalState, int wall) throws FileNotFoundException{
            this.map = createMap(file, map, initialState, goalState);
            this.initialState = initialState;
            this.goalState = goalState;
            this.wall = wall;
        }

        private int[][] createMap(String file, int[][] map, int initialState, int goalState) throws FileNotFoundException{
            Scanner inFile = new Scanner(new FileInputStream(file));
            initStateIdx = new int[2];
            noGoalStates = 0;

            for(int i=0; i < map.length; i++){
                for(int j=0; j < map.length; j++){
                    map[j][i] = inFile.nextInt();
                    if (map[j][i] == initialState){
                        initStateIdx[0] = j;
                        initStateIdx[1] = i;
                    }
                    else if(map[j][i] == goalState){
                        noGoalStates++;
                    }
                }
                if (!inFile.hasNext()) break;
                inFile.nextLine();
            }
            inFile.close();
            goalStateIdx = findStateIndexes(map, goalState, noGoalStates);
            return map;
        }

        private int[] findStateIndexes(int[][] map, int state, int noStates){
            int index = 0;
            int[] arr = new int[2 * noStates]; //Each state has two coordinates

            for(int i=0; i < map.length; i++){
                for(int j=0; j < map.length; j++){
                    if(map[j][i] == state){
                        arr[index] = j;
                        arr[++index] = i;
                        index++;
                    }
                }
            }
            return arr;
        }

        public boolean goalTest(int[] nodeState){
            for(int i = 0; i < goalStateIdx.length - 1; i+= 2){
                if (nodeState[0] == goalStateIdx[i] && nodeState[1] == goalStateIdx[i+1]){
                    return true;
                }
            }
            return false;
        }

        public int[] result(int[] parentState, String action){
            int column = parentState[0];
            int row = parentState[1];
            int[] result = new int[2];

            switch(action){
                case "Left":
                    if (column >= 1){ //Check for map boundaries
                        if (map[column-1][row] != wall){ //Check for walls
                            result[0] = column - 1;
                            result[1] = row;
                            return result;
                        }
                    }
                    return parentState;
                case "Right":
                    if (column < map[0].length-1){
                        if (map[column+1][row] != wall){
                            result[0] = column + 1;
                            result[1] = row;
                            return result;
                        }
                    }
                    return parentState;
                case "Up":
                    if (row >= 1){
                        if (map[column][row-1] != wall){
                            result[0] = column;
                            result[1] = row - 1;
                            return result;
                        }
                    }
                    return parentState;
                case "Down":
                    if (row < map.length-1){
                        if (map[column][row+1] != wall){
                            result[0] = column;
                            result[1] = row + 1;
                            return result;
                        }
                    }
                    return parentState;
                default:
                    return parentState;
            }
        }

        public int stepCost(int[] parentState, String action){
            int[] state = result(parentState, action);

            if(state != parentState){ //Check if both variables reference the same memory location (parent's action result was allowed)
                return 1;
            }
            else{
                return 0;
            }
        }

        public int getNoGoalStates(){
            return noGoalStates;
        }

        public void printMap(){
            for(int i=0; i < map.length; i++){
                for(int j=0; j < map.length; j++){
                    System.out.print(map[j][i] + " ");
                }
                System.out.print("\n");
            }
        }
    }