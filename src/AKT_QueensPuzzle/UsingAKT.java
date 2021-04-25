package AKT_QueensPuzzle;

/* 
    Huynh Tuan Khoa - DKP1181 - 3118412028
    Faculty of Information Technology, Saigon University
    htk14.pdr@gmail.com
*/

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.PriorityQueue;
 
public class UsingAKT {
    public static int QUEENSGOAL;
    
    public static PriorityCondition checkCondition = new PriorityCondition();
    public static PriorityQueue<Vertex> queue;
    
    public static Set<Vertex> visited = new HashSet<Vertex>(){
        public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v : this) {
                if (vertex.getState().getCurrent() == v.getState().getCurrent()){
                    return true;
                }
            }  
            return false;
        }
    };
   
    public static void main(String[] args){
        QUEENSGOAL = 8;
        char[][] Start = {{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'}};
        int depth = 0;
        int heuristic = 0;
        int step = -1;
        
        queue = new PriorityQueue<>(10,checkCondition){
            public boolean contains(Object obj) {
                Vertex vertex = (Vertex) obj;

                for (Vertex v : this) {
                    if ((vertex.getState().getCurrent() == v.getState().getCurrent())){
                        return true;
                    }
                }  
                return false;
            }      
        };
        
	Vertex initialVertex = new Vertex(new State(Start,depth,heuristic));
        queue.add(initialVertex); 
        
	while(!queue.isEmpty()){             
            Vertex currentVertex = queue.poll();
            currentVertex.addToPath();
            
            if(visited.contains(currentVertex))
                continue;
            
            char[][] ProcessSt = currentVertex.getState().getCurrent();
                
            System.out.println("------------------------");  
            System.out.println("Processing:");
            System.out.println("State Level: " + currentVertex.getState().getDepth());
            System.out.println("Total Cost: " + currentVertex.getState().getTotalCost());
            System.out.println("------------------------");     
            for(int i=0; i<8; i++){
                System.out.print("| ");
                for(int j=0; j<8; j++){
                    System.out.print(ProcessSt[i][j] + " ");
                }
                System.out.print("|\n");
            }
            System.out.println("------------------------");

            step++;
            currentVertex.getState().setStep(step);             
                             
            visited.add(currentVertex);
            
            if(currentVertex.getState().getQueens() == QUEENSGOAL){
                System.out.println("------------------------");
                System.out.println("         RESULT         ");
                System.out.println("------------------------");
                currentVertex.printPath(); 
                System.out.print("Total Steps: " + step + "\n");
                break;
            }            
            
            ArrayList<Vertex> newVertices = new ArrayList<>();
            ArrayList<char[][]> childList = new ArrayList<>();
            char[][] Current = currentVertex.getState().getCurrent();
            char[][] C = {{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'}};

            for(int i=0; i<8; i++)
                for(int j=0; j<8; j++)
                    C[i][j] = Current[i][j];
            
            childList.addAll(State.getChild(C));
            depth = currentVertex.getPath().size()-1;
            
            for(char[][] S : childList){
               heuristic = getHeuristic(S,depth);
               newVertices.add(new Vertex(new State(S,depth,heuristic)));
            }

            for (Vertex newVertex : newVertices){
                char[][] InitSt = newVertex.getState().getCurrent();
                
                System.out.println("------------------------");  
                System.out.println("Initializing:");
                System.out.println("State Level: " + newVertex.getState().getDepth());
                System.out.println("Total Cost: " + newVertex.getState().getTotalCost());
                System.out.println("------------------------");
                
                for(int i=0; i<8; i++){
                    System.out.print("| ");
                    for(int j=0; j<8; j++){
                        System.out.print(InitSt[i][j] + " ");
                    }
                    System.out.print("|\n");
                }
                System.out.println("------------------------");                
                
                if(!queue.contains(newVertex)){
                    newVertex.setPath(currentVertex.getPath());
                    queue.add(newVertex);   
                }
            }                       
	}
    }
    
    public static int getHeuristic(char[][] Current, int depth){
        int h = 0;
        String note = "x12345678";
        
        char x = note.charAt(depth);
        for(int i=0; i<8; i++){
            if(Current[depth][i] == 'Q'){
                for(int j=0; j<8; j++){
                    if(Current[depth][j] == x)
                        h++;
                }
                int temp = 1;
                
                if(depth < 7){
                    for(int k=depth+1; k<8; k++){
                        if(Current[k][i] == x)
                            h++;

                        if((i - temp) >= 0 && Current[k][i-temp] == x)
                            h++;

                        if((i + temp) <= 7 && Current[k][i+temp] == x)
                            h++;

                        temp++;
                    }
                }
               break; 
            }
        }

        return h;
    }    
}