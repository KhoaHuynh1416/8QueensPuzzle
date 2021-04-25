package AKT_QueensPuzzle;

import java.util.ArrayList;

/* 
    Huynh Tuan Khoa - DKP1181 - 3118412028
    Faculty of Information Technology, Saigon University
    htk14.pdr@gmail.com
*/

public class State {

    private char[][] Current;
    private int TotalCost;
    private int depth;
    private int queens;
    private int step;
    
    public State(){}
    
    public State(char[][] C, int d, int h){
        Current = C;
        depth = d;
        TotalCost = h;
        step = 0;
        
        queens = 0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(Current[i][j] == 'Q')
                    queens++;
            }
        } 
    }

    public char[][] getCurrent() {
        return Current;
    }
    
    public int getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(int TotalCost) {
        this.TotalCost = TotalCost;
    }    

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getQueens() {
        return queens;
    }

    public void setQueens(int queens) {
        this.queens = queens;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
    
    public static ArrayList<char[][]> getChild(char[][] state){
        ArrayList<char[][]> successors = new ArrayList<>();
        
        String note = "x12345678";
        
        int tdepth = 0;
        String s = cvString(state);       
        
        for(int i=0; i<8; i++){
            if(state[tdepth][i] == 'Q'){
                tdepth++;
                i=-1;
            }
        }
        
        char x = note.charAt(tdepth);
        for(int i=0; i<8; i++){
            if(!note.contains(""+state[tdepth][i])){
                state[tdepth][i] = 'Q';
                
                int temp = 1;
                for(int k=tdepth+1; k<8; k++){
                    if(!note.contains(""+state[k][i]))
                        state[k][i] = x;
                    
                    if((i-temp) >= 0 && !note.contains(""+state[k][i-temp]))
                        state[k][i-temp] = x;
                    
                    if((i+temp) <=7 && !note.contains(""+state[k][i+temp]))
                        state[k][i+temp] = x;
                    
                    temp++;
                }
                
                for(int q=0; q<8; q++){
                    if(!note.contains(""+state[tdepth][q]) && state[tdepth][q] != 'Q')
                        state[tdepth][q] = x;
                }
                
                successors.add(state);
                
                state = cvCharArray(s);
            }
        }
        
        return successors;
    }
    
    public static char[][] cvCharArray(String s){
        char[][] array = {{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'},{'0','0','0','0','0','0','0','0'}};
        
        int count = 0;
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++){
                array[i][j] = s.charAt(count);
                count++;
            }
        
        return array;
    }
    
    public static String cvString(char[][] s){
        String st ="";
        
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                st += s[i][j];
        
        return st;
    }    
    
    @Override
    public boolean equals(Object obj) {
        State s = (State) obj;        
        
	if (!(s instanceof State))
            return false;	

        return (s.getCurrent().equals(this.getCurrent()));
    }
    
    @Override
    public String toString(){
        return "Step: " + step + "\n" + "Queens: " + queens + " - "  + "Heuristic: " + TotalCost + " - " + "State Level: " + depth + "\n" + "|" + Current[0][0] + " " + Current[0][1] + " " + Current[0][2] + " " + Current[0][3] + " " + Current[0][4] + " " + Current[0][5] + " " + Current[0][6] + " " + Current[0][7] + "|\n" + "|" + Current[1][0] + " " + Current[1][1] + " " + Current[1][2] + " " + Current[1][3] + " " + Current[1][4] + " " + Current[1][5] + " " + Current[1][6] + " " + Current[1][7] + "|\n" + "|" + Current[2][0] + " " + Current[2][1] + " " + Current[2][2] + " " + Current[2][3] + " " + Current[2][4] + " " + Current[2][5] + " " + Current[2][6] + " " + Current[2][7] + "|\n" + "|" + Current[3][0] + " " + Current[3][1] + " " + Current[3][2] + " " + Current[0][3] + " " + Current[3][4] + " " + Current[3][5] + " " + Current[3][6] + " " + Current[3][7] + "|\n" + "|" + Current[4][0] + " " + Current[4][1] + " " + Current[4][2] + " " + Current[4][3] + " " + Current[4][4] + " " + Current[4][5] + " " + Current[4][6] + " " + Current[4][7] + "|\n" + "|" + Current[5][0] + " " + Current[5][1] + " " + Current[5][2] + " " + Current[5][3] + " " + Current[5][4] + " " + Current[5][5] + " " + Current[5][6] + " " + Current[5][7] + "|\n" + "|" + Current[6][0] + " " + Current[6][1] + " " + Current[6][2] + " " + Current[6][3] + " " + Current[6][4] + " " + Current[6][5] + " " + Current[6][6] + " " + Current[6][7] + "|\n" + "|" + Current[7][0] + " " + Current[7][1] + " " + Current[7][2] + " " + Current[7][3] + " " + Current[7][4] + " " + Current[7][5] + " " + Current[7][6] + " " + Current[7][7] + "|\n--------\n";
    }
}