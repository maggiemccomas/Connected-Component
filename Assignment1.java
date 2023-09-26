
import java.util.*;

/**
 * Programming Assigment 1 for Algorithms
 * Maggie McComas
 * The purpose of this program is to generate a random undirected graph and compute its 
 * largest connected component to see if it is greater than a given number. By using the data produced from the 
 * hardcoded test for the following functions, a graph was produced that was turned in along with the code. 
 */
public class Assignment1 {
    
    /**
     * Input: an int n that is greater than 1 and a float p in range [0,1]
     * Output: an adjacency list for an undirected graph G
     * Purpose: The purpose of this function is to generate an undirected graph 
     * with a random number of generated edges
     */
    public ArrayList<ArrayList<Integer>> generatingGraph(int n, float p){
        
        ArrayList<ArrayList<Integer>> adjArrayList = new ArrayList<>();

        for (int i = 0; i < n; i++){

            adjArrayList.add(i, new ArrayList<>());

        }
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i < j){
                    float q = (float)Math.random();

                    if (q < p){
                        adjArrayList.get(i).add(j);
                        adjArrayList.get(j).add(i);
                    }
                }
            }
        }

        return adjArrayList;
    }

    /**
     * Input: int i that is the index, boolean array visited that keeps track of vertices visited, an integer arraylist that keeps track of 
     * the current connected components, and an arraylist of integer arraylists that is the adjacency list for graph G
     * Output: None method is void
     * Purpose: This is a helper method for the connectedComp function and performs a Depth First Search starting at vertex i.
     */
    public void DFS(int i, boolean[] visited, ArrayList<Integer> components, ArrayList<ArrayList<Integer>> G){
        visited[i] = true;
        components.add(i);

        for(int val: G.get(i)){
            if(visited[val] == false){
                DFS(val, visited, components, G);
            }
        }
    }

    /**
     * Dependent: DFS()
     * Input: an ArrayList<ArrayList<Integer>> G (graph) and an int t
     * Output: 1 if the graph G contains a connected component with >= t verticies.
     * Else it returns 0 
     * Purpose: The purpose of this function is to check wether the largest connected component 
     * of a graph is larger than a given value.
     */
    public int connectedComp(ArrayList<ArrayList<Integer>> G, int t){
        
        boolean[] visited = new boolean[G.size()];
        ArrayList<Integer> components = new ArrayList<>();

        for (int i = 0; i < G.size(); i++){
            components.clear();

            if(visited[i] == false){
                DFS(i, visited, components, G);

                if (components.size() >= t){
                    return 1;
                }
            }
        }
        return 0;
    }

     /**
     * Input: None
     * Output: It prints out the c and the percent of graphs who's largest connected component is greater then t.
     * Purpose: The main method of the class used to test the above functions.
     * Where n = 40, c is in range [0.2, 3.0] with a step size of 0.2, p = c/n, and t = 30
     * 500 graphs are generated and the percent of graphs who's largest compenent is greater than t is calculated.
     */
    public static void main(String[] args) {
        Assignment1 test = new Assignment1();
        int n = 40;
        int t = 30;
        float[] range = {0.2f, 0.4f, 0.6f, 0.8f, 1.0f, 1.2f, 1.4f, 1.6f, 1.8f, 2.0f, 2.2f, 2.4f, 2.6f, 2.8f, 3.0f};
        float p;
        int count;
        float percentage;


        for(float c: range){
            p = c/ (float)n;
            count = 0;

            for (int i = 0; i < 500; i++){
                
                ArrayList<ArrayList<Integer>> G = test.generatingGraph(n, p);

                if(test.connectedComp(G, t)>0){
                    count++;
                }

            }

            percentage = (float)count / 500f; 

            System.out.println("x-axis (c) = " + c + " and y-axis (percentage) = " + percentage * 100);

        }

    }
    
}