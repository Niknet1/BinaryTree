public class BinaryNode {
    private BinaryNode left, right;
    private Comparable myVal;

    public BinaryNode(Comparable c, BinaryNode l, BinaryNode r){
        myVal = c;
        left = l;
        right = r;
    }

    public Comparable getValue(){
        return myVal;
    }

    public BinaryNode getLeft(){
        return left;
    }

    public BinaryNode getRight() {
        return right;
    }
    public void setLeft(BinaryNode x){
        left = x;
    }
    public void setRight(BinaryNode y){
        right = y;
    }

    public void setMyVal(Comparable x){
        myVal=x;
    }
}
