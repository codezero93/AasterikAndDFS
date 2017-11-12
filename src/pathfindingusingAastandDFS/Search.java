/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfindingusingAastandDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Search {
     private Node rootNode;
    private Node goalNode;
    private List<Node> allNodes;
    private List<Node> opList;
    private List<Node> clList;
    private int expandedNodes = 0;
    private int mazeWidth;
    private int mazeHeight;

    public Search(char[][] outArr, int mazeWidth, int mazeHeight) {
        allNodes = new ArrayList<>();
        this.mazeHeight = mazeHeight;
        this.mazeWidth = mazeWidth;
        initGrid(outArr);

    }

    private void initGrid(char outArr[][]) {
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                char ch = outArr[i][j];
                if (ch == '%') {
                    allNodes.add(new Node(i, j, true));
                }
                if (ch == ' ') {
                    allNodes.add(new Node(i, j, false));
                }
                if (ch == 'P') {
                    rootNode = new Node(i, j, false);
                    allNodes.add(rootNode);
                }
                if (ch == '.') {
                    goalNode = new Node(i, j, false);
                    allNodes.add(goalNode);
                }
            }
        }
    }

    public List<Node> searchPathByAstar() {
        opList = new LinkedList<>();
        clList = new LinkedList<>();
        initRootNode(rootNode, goalNode);
        opList.add(rootNode);

        while (!opList.isEmpty()) {
            Node currNode = getSmallestFnode();
            opList.remove(currNode);
            clList.add(currNode);

            if (currNode.equals(goalNode)) {
                return computePath(rootNode, currNode);
            }

            List<Node> neighbourNodes = getNeighbours(currNode);
            for (int k = 0; k < neighbourNodes.size(); k++) {
                Node currNeighbour = neighbourNodes.get(k);
                if (!opList.contains(currNeighbour)) {
                    currNeighbour.setParNode(currNode);
                    currNeighbour.setH(goalNode);
                    currNeighbour.setG(currNode);
                    opList.add(currNeighbour);
                    expandedNodes = expandedNodes + 1;
                } else if (currNeighbour.getG() > currNeighbour.computeG(currNode)) {
                    currNeighbour.setParNode(currNode);
                    currNeighbour.setG(currNode);
                }
            }
        }

        return null;
    }

    public List<Node> searchPathByDFS() {
        expandedNodes = 0;
        rootNode.setParNode(null);
        Stack<Node> oList = new Stack<>();

        clList = new ArrayList<>();
        oList.push(rootNode);
        while (!oList.isEmpty()) {
            Node currNode = oList.pop();
            if (currNode.equals(goalNode)) {
                return computePath(rootNode, currNode);
            } else {
                clList.add(currNode);
                List<Node> neighbourNodes = getNeighbours(currNode);
                for (Node n : neighbourNodes) {
                    if (!oList.contains(n)) {
                        n.setParNode(currNode);
                        oList.push(n);
                        expandedNodes = expandedNodes + 1;
                    }
                }

            }
        }
        return null;
    }

    private Node getNeighbourNode(int xPlace1, int yPlace1) {

        for (Node n : allNodes) {
            if (n.getXPlace() == xPlace1 && n.getYPlace() == yPlace1) {
                return n;
            }
        }
        return null;
    }

    private List<Node> getNeighbours(Node n) {
        List<Node> neighbours = new LinkedList<>();
        int xPlace = n.getXPlace();
        int yPlace = n.getYPlace();
        Node tempNode;

        if (xPlace > 0) {
            tempNode = this.getNeighbourNode(xPlace - 1, yPlace);
            if (!(tempNode.isWall()) && !clList.contains(tempNode)) {
                neighbours.add(tempNode);
            }
        }

        if (yPlace > 0) {
            tempNode = this.getNeighbourNode(xPlace, yPlace - 1);
            if (!(tempNode.isWall()) && !clList.contains(tempNode)) {
                neighbours.add(tempNode);
            }
        }

        if (yPlace < mazeWidth) {
            tempNode = this.getNeighbourNode(xPlace, yPlace + 1);
            if (!(tempNode.isWall()) && !clList.contains(tempNode)) {
                neighbours.add(tempNode);
            }
        }

        if (xPlace < mazeHeight) {
            tempNode = this.getNeighbourNode(xPlace + 1, yPlace);
            if (!(tempNode.isWall()) && !clList.contains(tempNode)) {
                neighbours.add(tempNode);
            }
        }
        return neighbours;

    }

    private List<Node> computePath(Node root, Node goal) {

        LinkedList<Node> pathFromGoaltoRoot = new LinkedList<>();
        boolean flag = false;
        Node temp = goal;
        while (!flag) {
            pathFromGoaltoRoot.addFirst(temp);
            temp = temp.getParNode();
            if (temp.equals(root)) {
                flag = true;
                pathFromGoaltoRoot.addFirst(root);
            }
        }

        return pathFromGoaltoRoot;
    }

    private Node getSmallestFnode() {
        Node n = opList.get(0);
        for (int k = 0; k < opList.size(); k++) {
            if (opList.get(k).getF() < n.getF()) {
                n = opList.get(k);
            }
        }
        return n;
    }

    public void printNoOfExpandedNodes() {
        System.out.println("No of nodes expanded: " + expandedNodes);
    }

    private void initRootNode(Node rootNode1, Node goalNode1) {
        rootNode1.setG(0);
        rootNode1.setH(goalNode1);
        rootNode1.setParNode(null);
    }

}
