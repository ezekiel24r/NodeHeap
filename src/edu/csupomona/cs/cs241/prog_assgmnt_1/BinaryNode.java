package edu.csupomona.cs.cs241.prog_assgmnt_1;

/**
 * This class creates a node that is meant to be used in a binary tree structure.
 * It has a pointer to the parent of the node which allows for traversal of the tree upwards.
 * It is written to hold any type of object as the data of the node.
 *
 * @author Eric Rensel
 * Created by Eric on 4/20/2015.
 */
public class BinaryNode <V extends Comparable<V>> {
    
	/**
	 * data holds a generic object in the node
	 */
	protected V data;
	/**
	 * these three fields hold pointers to the children and parent of the current node
	 */
    private BinaryNode<V> rightChild;
    private BinaryNode<V> leftChild;
    private BinaryNode<V> parent;

    /**
     * The constructor takes in the data to be held as a parameter and stores it in the node.
     * All pointers are set to null
     * @param in of type V
     */
    public BinaryNode(V in){
        data = in;
        rightChild = null;
        leftChild = null;
        parent = null;
    }

    /**
     * Returns the right child of the node
     * @return of type {@link BinaryNode}
     */
    public BinaryNode<V> getRightChild(){
        return rightChild;
    }

    /**
     * Returns the left child of the node
     * @return of type {@link BinaryNode}
     */
    public BinaryNode<V> getLeftChild(){
        return leftChild;
    }

    /**
     * Returns the parent of the node
     * @return of type {@link BinaryNode}
     */
    public BinaryNode<V> getParent(){
        return parent;
    }

    /**
     * Sets the right child of the node
     * @param in of type {@link BinaryNode}
     */
    public void setRightChild(BinaryNode<V> in){
        rightChild = in;
    }

    /**
     * Sets the left child of the node
     * @param in of type {@link BinaryNode}
     */
    public void setLeftChild(BinaryNode<V> in){
        leftChild = in;
    }

    /**
     * Sets the parent of the node
     * @param in of type {@link BinaryNode}
     */
    public void setParent(BinaryNode<V> in){
        parent = in;
    }

    /**
     * Returns if the binaryNode has a larger child
     * @return boolean
     */
    public boolean hasLargerChild(){
        boolean result = false;
        if(this.getRightChild() != null) {
            if ((this.data.compareTo(getRightChild().data)) < 0) {
                result = true;
            }
        }
        if(this.getLeftChild() != null){
            if ((this.data.compareTo(getLeftChild().data)) < 0) {
                result = true;
            }
        }
        return result;
    }
}
