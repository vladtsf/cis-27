package Maze;

import java.util.ArrayList;
import java.util.Random;

public class Maze {
    private UnionFind union;
    private final int width, height;
    private final int start, finish;
    private ArrayList<Integer> knockedWalls;
    
    public Maze(int w, int h) {
        Random r = new Random();
        
        width = w;
        height = h;
        union = new UnionFind(w * h);
        knockedWalls = new ArrayList<>();
        
        start = positionToID(r.nextInt(width), 0);
        finish = positionToID(r.nextInt(width), height - 1);
    }
    
    public void generate() {
        knockedWalls = new ArrayList<>();
        union =  new UnionFind(width * height);
        
        while(!union.connected(start, finish) || !allCellsReachable()) {
            int[] cells = pickCellPair();
            
            if(!union.connected(cells[0], cells[1])) {
                union.union(cells[0], cells[1]);
                knockedWalls.add(wallID(cells[0], cells[1]));
            }
        }
    }
    
    // I'm sure there's a better way to check it,
    // but oh well...
    private boolean allCellsReachable() {
        int c1, c2, c3;
        
        for(int x = 0; x < width - 1; x++) {
            for(int y = 0; y < height - 1; y++) {
                c1 = positionToID(x, y);
                c2 = positionToID(x + 1, y);
                c3 = positionToID(x, y + 1);
                
                if(!(union.connected(c1, c2) && union.connected(c1, c3))) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private int[] pickCellPair() {
        Random r = new Random();
        
        int c1 = positionToID(r.nextInt(width), r.nextInt(height));
        int c2;
        
        switch(r.nextInt(3)) {
            case 0:
                c2 = c1 + 1;
                break;
            case 1:
                c2 = c1 - 1;
                break;
            case 2:
                c2 = c1 + width;
                break;
            default:
                c2 = c1 - width;
                break;
        }
        
        int wall = wallID(c1, c2);
        
        if(wall == -1) {
            return pickCellPair();
        }
        
        if(knockedWalls.contains(wall)) {
            return pickCellPair();
        }
        
        return new int[] {c1, c2};
    }
    
    // every cell has a unique id.
    // this id can be obtained from the cells (x,y) coordinates
    private int positionToID(int x, int y) {
        return y * width + x;
    }
    
    // and an id can be converted to coordinates
    private int[] IDtoPosition(int id) {
        int x = id % width;
        int y = id / width;
        
        return new int[] {x, y};
    }
    
    // as cells, every wall has an id
    private int wallID(int c1, int c2) {
        int[] _c1 = IDtoPosition(c1);
        int[] _c2 = IDtoPosition(c2);
        return wallID(_c1[0], _c1[1], _c2[0], _c2[1]);
    }
    
    private int wallID(int x1, int y1, int x2, int y2) {
        // the wall between cells #1 and #2 must be
        // the same as between #2 and #1
        if(x1 > x2) {
            int tmp = x2;
            x2 = x1;
            x1 = tmp;
        }
        
        if(y1 > y2) {
            int tmp = y2;
            y2 = y1;
            y1 = tmp;
        }
        
        if(y2 - y1 + x2 - x1 != 1) {
            // if there's no wall between the cells            
            return -1;
        }
        
        // if the cells don't have a mutual wall
        if(x1 < 0 || x2 >= width) {
            return -1;
        } else if (y1 < 0 || y2 >= height) {
            return -1;
        }
        
        if(y1 == y2) {
            // left -> right   
            return y2 * width + y2 * (width - 1) + (x2 - 1);
        } else {
            // top -> bottom
            return (y2 - 1) * width + y2 * (width - 1) + x2;
        }
    }

    @Override
    public String toString() {
        ArrayList<String> lines = new ArrayList<>();
        
        // top border
        String line = " ";
        
        for(int x = 0; x < width; x++) {
            line += start != positionToID(x, 0) ? "_ " : "  ";        
        }
        
        lines.add(line);
        
        for(int y = 0; y < height; y++) {
            line = "|";
            
            for(int x = 0; x < width; x++) {
                // right wall ID
                int rID = wallID(x, y, x+1, y);
                
                // bottom wall ID
                int bID = wallID(x, y, x, y + 1);
                
                if(knockedWalls.contains(bID) || (y == height - 1 && finish == positionToID(x, y))) {
                    line += " ";
                } else {
                    line += "_";
                }
                
                if(knockedWalls.contains(rID)) {
                    line += " ";
                } else {
                    line += "|";
                }
            }
            
            lines.add(line);
        }
        
        return String.join("\n", lines);
    }
    
    
}


// Output
/*
3. Union-Find - Maze
 _ _ _   _ _ _ _ _ _ 
|_ _ _ _ _ _     _ _|
|_ _  | |  _ _|_ _ _|
|_   _ _|  _ _ _ _ _|
|_   _ _ _|   |    _|
|  _  |    _|_| |_ _|
|_|_  | |_ _ _|  _  |
|_  |_ _  |  _|  _|_|
|_   _ _  |  _ _|  _|
|  _|  _      |  _  |
|_|_ _|  _|_|_ _|_ _|
*/