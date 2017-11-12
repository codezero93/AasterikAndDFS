/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfindingusingAastandDFS;

/**
 *
 * @author Sam
 */
public class PathFindingAasterik {

  
    public static void main(String[] args) {
      System.out.println("Pathfinding using A*:");
        Maze m = new Maze("file.txt");
        Search s = new Search(m.getOutArr(), m.getMazeWidth(), m.getMazeHeight());
        m.putPathOnMaze(s.searchPathByAstar());
        m.printMaze();
        s.printNoOfExpandedNodes();

        System.out.println("");
        System.out.println("");
        System.out.println("Pathfinding using DFS:");
        Maze m1 = new Maze("file.txt");
        m1.putPathOnMaze(s.searchPathByDFS());
        m1.printMazeWithoutF();
        s.printNoOfExpandedNodes();

    }
    
}
