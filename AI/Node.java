package AI;

public class Node {
    private int[] state; //[0] stores column location and [1] stores row location
        private Node parent;
        private String action;
        private int pathCost;
        private String[] solution;
        private int totalSteps = 0;

        Node(Node parent, int[] state, String action, int pathCost){
                this.parent = parent;
                this.state = state;
                this.action = action;
                this.pathCost = pathCost;        
        }

        public Node childNode(Problem problem, Node parent, String action){
            return new Node(parent, problem.result(parent.state, action), action, 
            (parent.pathCost + problem.stepCost(parent.state, action)));
        }

        public int[] getState(){
            return this.state;
        }

        public String getStateString(){
            String stateString = String.valueOf(state[0]) + String.valueOf(state[1]);
            return stateString;
        }

        private Node findPathForSolution(Node node){ //Return the sequence of actions obtained by following parent pointers back to the root
            if (node.parent == null){ //Check if node is root
                solution[totalSteps] = node.action + "\n";
                return null;
            }
            solution[totalSteps] = node.action + "\n";
            totalSteps++;
            return findPathForSolution(node.parent);
        }

        public void solution(Node node){
            solution = new String[node.pathCost+1];
            findPathForSolution(node);
            System.out.println("Total path cost: " + node.pathCost);
            for(int i=solution.length-1; i>=0; i--){
                System.out.print(solution[i]);
            }
        }
}
