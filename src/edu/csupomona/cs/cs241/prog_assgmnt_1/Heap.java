package edu.csupomona.cs.cs241.prog_assgmnt_1;

/**
 * Created by Eric on 4/20/2015.
 */
public interface Heap<V extends Comparable<V>> {

    public void add(V value);

    public V[] toArray();

    public V remove();

    public void fromArray(V[] array);

    public V[] getSortedContents();

}
