
package pathfindingusingAastandDFS;


public class Node {
        private final int stepCost = 1;
    private Node parNode;
    private int xPlace;
    private int yPlace;
    private int g, h;
    private boolean wall;

    public Node(int xPlace, int yPlace, boolean wall) {

        this.xPlace = xPlace;
        this.yPlace = yPlace;
        this.wall = wall;
    }

    public int getXPlace() {
        return xPlace;
    }

    public int getYPlace() {
        return yPlace;
    }

    public boolean isWall() {
        return wall;
    }

    public Node getParNode() {
        return parNode;
    }

    public void setParNode(Node parNode) {
        this.parNode = parNode;
    }

    public int getG() {
        return g;
    }

    public int computeG(Node parNode) {
        return (parNode.getG() + stepCost);
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setG(Node parNode) {
        setG(parNode.getG() + stepCost);
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }


    public void setH(Node goalNode) {
        this.setH((Math.abs(this.getXPlace() - goalNode.getXPlace())
                + Math.abs(this.yPlace - goalNode.yPlace)));
    }

    public int getF() {
        return g + h;
    }

    @Override 
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Node n = (Node) obj;
        if (this.getXPlace() != n.getXPlace()) {
            return false;
        }
        if (this.getYPlace() != n.getYPlace()) {
            return false;
        }
        return true;

    }

    @Override 
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.xPlace;
        hash = 59 * hash + this.yPlace;
        return hash;
    }

}
