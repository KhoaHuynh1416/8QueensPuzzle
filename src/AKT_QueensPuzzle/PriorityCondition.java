/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AKT_QueensPuzzle;

import java.util.Comparator;

/**
 *
 * @author PC
 */
public class PriorityCondition implements Comparator<Vertex> {
    @Override
    public int compare(Vertex x, Vertex y) {        
        if (x.getState().getTotalCost() < y.getState().getTotalCost()) {
            return -1;
        }
        if (x.getState().getTotalCost() > y.getState().getTotalCost()) {
            return 1;
        }
        return 0;
    }
    
}
