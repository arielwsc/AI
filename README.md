## Problem Formulation
The objective is to create a **rational agent** that is capable of search through the grid map (provided via input file) from the initial position marked as the starting cell and find the path way to the cell marked as the goal cell. 
Each cell is denoted by an int value:
0: The agent can enter this space. 1: This cell is the starting cell (Initial state). 2: This cell is a goal cell (Goal state). 3: This cell is a wall. The agent is unable to enter this space.
The agent stops its search as soon as it enters a goal cell. The environment is observable, so the agent always knows its current state. Also, the pathway is recorded and printed from the start to end cell. As we are dealing with cells, we want to find the shortest path. The path cost for each action is the same. Therefore, the smaller path cost is desired.
Actions that can be performed: Move left, right, up, and down.
It is important to mention we are using a 10x10 grid map in this case, so each state (position) of the agent is denoted by a column and a row and unique (deterministic). As example, if the agent is located on (8, 0) and moves one state to the left, its new state will be (7, 0). Movement into a wall cell or out of boundaries of the map will be ignored (agent remains on the same cell).

## The Agent
The agent is **complete**. If the shallowest goal node is at some finite depth d, the agent will eventually find it after generating all shallower nodes (provided the branching factor b is finite).
The agent is also **optimal**. The path cost is a nondecreasing function of the depth of the node. In this case the path cost is the same for any action.

## Implementation Explanation
I used the **Breadth First** for the agent’s search through the map. There were two reasons why I decided to apply this search strategy:
1- The algorithm is fairly simple and easy to implement.
2- As the shortest path is the most desired for this scenario, we want to find the goal state in the shallowest node in the graph. 
The breakdown of the breadth First algorithm is shown in the following figure:
![alt text](https://github.com/arielwsc/AI/blob/master/BreadthFirstImg.jpg?raw=true)
The shallowest nodes are explored first before expanding further to the next level. When the goal state is reached, the search stops and return the actions that followed to find this goal node.
