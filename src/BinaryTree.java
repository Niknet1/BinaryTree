public class BinaryTree {
    private BinaryNode root;

    public BinaryTree() {
        root = null;
    }

    public void add(BinaryNode x) {
        if (root == null) {
            root = x;
        } else {
            add(root, x);
        }
    }

    private void add(BinaryNode parent, BinaryNode x) {
        if (x.getValue().compareTo(parent.getValue()) < 0) {
            if (parent.getLeft() == null) {
                parent.setLeft(x);
            } else {
                add(parent.getLeft(), x);
            }
        } else {
            if (parent.getRight() == null) {
                parent.setRight(x);
            } else {
                add(parent.getRight(), x);
            }
        }
    }

    public String inOrder() {
        return inOrder(root);
    }

    private String inOrder(BinaryNode x) {
        String temp = "";
        if (x != null) {


            temp += inOrder(x.getLeft());
            temp += x.getValue();
            temp += inOrder(x.getRight());
        }
        return temp;
    }

    //deletion

    public BinaryNode delete(Comparable target) {
        if (root == null) return null;
        BinaryNode temp = root;
        BinaryNode inorderSuccessor;
        if(root.getValue().equals(target)) {
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return temp;
            }
//remove root degree 1 – right child
            else if (root.getLeft() == null) {
                root = root.getRight();
                temp.setRight(null);
                ;
                return temp;
            }
//remove root degree 1 – left child
            else if (root.getRight() == null) {
                root = root.getLeft();
                temp.setLeft(null);
                return temp;
            }
//remove root degree 2
            else {
                inorderSuccessor = successor(root);
                swap(root, inorderSuccessor);
                if (root.getRight() == inorderSuccessor) {
                    root.setRight(inorderSuccessor.getRight());
                    inorderSuccessor.setRight(null);
                    return inorderSuccessor;
                }
                return remove(root.getRight(), target);
            }
        }
        //if root is not removed call remove helper method
        return remove(root, target);


    }



        private BinaryNode remove(BinaryNode startNode, Comparable target)
        {
            BinaryNode nodeToRemove, inorderSuccessor;
            BinaryNode parent = search(startNode,target);
            if(parent == null) return null;
            //decide if it is a left or right child
            boolean isLeft = parent.getLeft()!=null && parent.getLeft().getValue().equals(target);
            nodeToRemove = isLeft ? parent.getLeft() : parent.getRight();
            //degree 0
            if(nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null)
            {
                if(isLeft)
                    parent.setLeft(null);
                else
                    parent.setRight(null);
                return nodeToRemove;
            }
            //degree 1
            else if(nodeToRemove.getLeft() == null)
            {
                if(isLeft)
                    parent.setLeft(nodeToRemove.getRight());
                else
                    parent.setRight(nodeToRemove.getRight());
                nodeToRemove.setRight(null);
                return nodeToRemove;
            }
            else if(nodeToRemove.getRight() == null)
            {
                if(isLeft)
                    parent.setLeft(nodeToRemove.getLeft());
                else
                    parent.setRight(nodeToRemove.getLeft());
                nodeToRemove.setLeft(null);
                return nodeToRemove;
            }
            //degree 2
            else
            {
                inorderSuccessor = successor(nodeToRemove);
                swap(inorderSuccessor, nodeToRemove);
                if(nodeToRemove.getRight()==inorderSuccessor)
                {
                    nodeToRemove.setRight(inorderSuccessor.getLeft());
                    inorderSuccessor.setRight(null);
                    return inorderSuccessor;
                }
                return remove(nodeToRemove.getRight(), target);
            }

        }



    private void swap(BinaryNode k, BinaryNode i) {
        Comparable temp = k.getValue();
        k.setMyVal(i.getValue());
        i.setMyVal(temp);
    }

    private BinaryNode successor(BinaryNode k) {
        BinaryNode temp = k;
        temp = temp.getRight();
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        return temp;
    }

    private BinaryNode search(BinaryNode parent, Comparable target) {
        if (parent == null) return null;
        if (parent.getLeft() != null && parent.getLeft().getValue().equals(target) ||
                parent.getRight() != null && parent.getRight().getValue().equals(target)) {
            return parent;
        } else if (target.compareTo(parent.getValue()) < 0) {
            return search(parent.getLeft(), target);
        } else {
            return search(parent.getRight(), target);
        }
    }
}

