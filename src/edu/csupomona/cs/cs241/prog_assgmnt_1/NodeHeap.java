/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #1
 *
 * The objective of this assignment was to create a heap in
 * its node representation, and then to create a program on
 * top which would provide a system for a priority waiting list for
 * guests at a restaurant.
 *
 *
 * Eric Rensel - 5/1/2015
 */

package edu.csupomona.cs.cs241.prog_assgmnt_1;

import java.util.ArrayList;

/**
 * This class creates a heap data structure using nodes created with the BinaryNode object.
 * Created by Eric on 4/20/2015.
 */
public class NodeHeap<V extends Comparable<V>> implements Heap<V>{
    /**
     * root holds the first node in the binaryheap
     */
    protected BinaryNode<V> root;
    /**
     * count records the number of elements in the heap
     */
    protected int count;

    /**
     * The constructor always sets the root to null and count to 0
     */
    public NodeHeap(){
        root = null;
        count = 0;
    }

    /**
     * Finds the node that is the parent of the index entered. This method uses the structure
     * of binary trees to find the node requested in logN time, assuming that you know the
     * index of the node. "Index" in this case refers to what the index of the node would be
     * if the node were in a binary heap. It is most useful when adding nodes to the heap
     * @param pos refers to the index of the node being searched for
     * @return a reference to the node at the index
     */
    public BinaryNode<V> getParentOfIndex(int pos){
        pos+=1;
        String binaryPos = Integer.toString(pos,2);
        if(binaryPos.length() <= 1)
            return null;
        binaryPos = binaryPos.substring(1, (binaryPos.length()-1));
        BinaryNode<V> ptr = root;
        for(int i=0; i<binaryPos.length(); i++) {
            if (binaryPos.charAt(i) == '0')
                ptr = ptr.getLeftChild();
            if (binaryPos.charAt(i) == '1')
                ptr = ptr.getRightChild();
        }
        return ptr;
    }

    /**
     * Finds the node at the position of the index entered. This method uses the structure
     * of binary trees to find the node requested in logN time, assuming that you know the
     * index of the node. "Index" in this case refers to what the index of the node would be
     * if the node were in a binary heap. It is useful when removing nodes from the heap.
     * @param pos refers to  the index where the node will be
     * @return a reference to the node at the specified index
     */
    public BinaryNode<V> getNodeAtIndex(int pos){
        if(pos>count)
            return null;
        pos+=1;
        String binaryPos = Integer.toString(pos,2);
        if(binaryPos.length() <= 1)
            return root;
        binaryPos = binaryPos.substring(1, (binaryPos.length()));
        BinaryNode<V> ptr = root;
        for(int i=0; i<binaryPos.length(); i++) {
            if (binaryPos.charAt(i) == '0')
                ptr = ptr.getLeftChild();
            if (binaryPos.charAt(i) == '1')
                ptr = ptr.getRightChild();
        }
        return ptr;
    }

    /**
     * Bubbles up a node through the heap until it reaches a parent that is larger than it
     * @param nodeRef The node to be sifted up
     */
    public void siftUp(BinaryNode<V> nodeRef){
        while(nodeRef.data.compareTo(nodeRef.getParent().data) > 0){
            V temp = nodeRef.data;
            nodeRef.data = (nodeRef.getParent().data);
            nodeRef.getParent().data = temp;
            nodeRef = nodeRef.getParent();
            if(nodeRef == root){
                break;
            }
        }
    }

    /**
     * Pushes a node down through the heap until it's children are both smaller than it
     * @param nodeRef The node to be sifted down
     */
    public void siftDown(BinaryNode<V> nodeRef){
        BinaryNode<V> nextNode = root;
        while(nodeRef.hasLargerChild()){
            if(nodeRef.getRightChild() != null){
                if((nodeRef.getLeftChild().data.compareTo(nodeRef.getRightChild().data)) > 0){
                    nextNode = nodeRef.getLeftChild();
                }
                else
                    nextNode = nodeRef.getRightChild();
            }
            else if(nodeRef.getLeftChild() != null){
                nextNode = nodeRef.getLeftChild();
            }
            V temp = nextNode.data;
            nextNode.data = nodeRef.data;
            nodeRef.data = temp;
            nodeRef = nextNode;
        }
    }

    /**
     * Adds a new node into the heap. This method relies heavily on the
     * getParentOfIndex() method to find out where the new node should go.
     * @param in the object to be added
     */
    public void add(V in){
        BinaryNode<V> newNode = new BinaryNode<>(in);
        BinaryNode<V> insertionParent = root;
        //empty case
        if(root==null){
            root = newNode;
            count++;
            return;
        }
        //else if(count == 1)
        //   insertionParent = root;
        else
            insertionParent = getParentOfIndex(count);
        //insertionParent should now be on the node above where we can insert a new node
        if(insertionParent.getLeftChild() == null) {
            insertionParent.setLeftChild(newNode);
            newNode.setParent(insertionParent);
            siftUp(insertionParent.getLeftChild());
        }
        else if(insertionParent.getRightChild() == null) {
            insertionParent.setRightChild(newNode);
            newNode.setParent(insertionParent);
            siftUp(insertionParent.getRightChild());
        }
        count++;
    }

    /**
     * removes the root node of the heap, places the last element in the heap in its place,
     * and then sifts down that node so that the heap will again satisfy the heap property.
     * @return the object that was at the top of the heap (the largest object)
     */
    public V remove(){
        if(count == 0)
            return null;
        V result = root.data;
        if(count == 1){
            root.setLeftChild(null);
            root.setRightChild(null);
            root.data = null;
            count = 0;
            return result;
        }
        BinaryNode<V> nodePtr = getNodeAtIndex(count-1);
        BinaryNode<V> parentNode = nodePtr.getParent();
        root.data = nodePtr.data;
        if(nodePtr == parentNode.getRightChild()){
            parentNode.setRightChild(null);
        }
        else {
            parentNode.setLeftChild(null);
        }
        siftDown(root);
        count--;
        return result;
    }

    /**
     * This method uses a bredth first traversal method to go through the heap recording values
     * from left to right, level by level. These values are stored in an array.
     * @return an array of the values in the heap in the order of a breadth first traversal.
     */
    public V[] toArray(){
    	V[] array = (V[]) java.lang.reflect.Array.newInstance(root.data.getClass(), count);
        if(count == 0)
            return array;
        ArrayList<BinaryNode<V>> queue = new ArrayList<>();
        queue.add(root);
        for(int i=0; !queue.isEmpty(); i++)
        {
            BinaryNode<V> ptr = queue.get(0);
            if(ptr.getLeftChild() != null)
                queue.add(ptr.getLeftChild());
            if(ptr.getRightChild() != null)
                queue.add(ptr.getRightChild());
            array[i] = queue.remove(0).data;
        }
        return array;
    }

    /**
     * Takes any array and inserts it into the heap. This could be performed on a heap that
     * is empty or already has data inside of it.
     * @param array the array to be entered into the heap
     */
    public void fromArray(V[] array){
        for(int i=0; i<array.length; i++){
            add(array[i]);
        }
    }

    /**
     * Turns the heap into its array representation and then uses the heapsort method to sort it.
     * This method originally had a parameter for an array, but I took it off because of the description
     * on the assignment page which states:
     * "The getSortedContents method should, internally, transform the heap into its array representation,
     * and the perform Heap-Sort on it, then return the array as the result."
     * Since it is internally transforming the heap into its array representation, there is no reason to
     * pass the array representation in as a parameter.
     * @return The sorted array representation of the heap.
     */
    public V[] getSortedContents(){
        V[] array = this.toArray();
        heapSort(array);
        return array;
    }

    /**
     * Manages the shrinking size of the unsorted portion of the array as elements are being sifted down
     * @param array the sorted representation of the heap
     */
    private void heapSort(V[] array){
        int tail = array.length-1;
        while(tail > 0){
            swap(array, 0, tail);
            tail--;
            siftArrayHeap(array, tail+1);
        }
    }

    /**
     * Performs the array version of sift down on the first element of the array.
     * Used in conjunction with the shrinking size of the unsorted portion of the array.
     * @param array the array being sorted
     * @param length the length of the unsorted portion of the array, so as to not sift past these bounds
     */
    private void siftArrayHeap(V[] array, int length){
        int currIndex = 0;
        int swapIndex;
        while(leftIndex(currIndex) < length || rightIndex(currIndex) < length) {
            if(rightIndex(currIndex) < length) {
                if (array[leftIndex(currIndex)].compareTo(array[rightIndex(currIndex)]) >= 0) {
                    swapIndex = leftIndex(currIndex);
                } else
                    swapIndex = rightIndex(currIndex);
            }
            else
                swapIndex = leftIndex(currIndex);
            if(array[currIndex].compareTo(array[swapIndex]) < 0){
                swap(array, currIndex, swapIndex);
                currIndex = swapIndex;
            }
            else
                break;
        }
    }
    /**
     * Finds the index of the left child of a node in an array representation.
     * @param n the index of the node to calculate from
     * @return the left child index of the node index passed as a parameter
     */
    private int leftIndex(int n){
        return ((n*2)+1);
    }
    /**
     * Finds the index of the right child of a node in an array representation.
     * @param n the index of the node to calculate from
     * @return the left child index of the node index passed as a parameter
     */
    private int rightIndex(int n){
        return ((n*2)+2);
    }

    /**
     * swaps two elements in an array
     * @param array
     * @param i the first index to be swapped.
     * @param j the second index to be swapped.
     */
    private void swap(V [] array, int i, int j){
        V temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /**
     * Checks if any elements are in the heap
     * @return a boolean that is true if the heap is empty.
     */
    public boolean isEmpty(){
        if(count == 0)
            return true;
        else
            return false;
    }
}