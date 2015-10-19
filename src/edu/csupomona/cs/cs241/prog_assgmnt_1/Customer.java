package edu.csupomona.cs.cs241.prog_assgmnt_1;

/**
 * Created by Eric on 4/21/2015.
 */
public class Customer implements Comparable<Customer>{
    protected String name;
    protected int partySize;
    private int priority;

    public Customer(String name,int partySize, int priority){
        this.name = name;
        this.partySize = partySize;
        this.priority = priority;
    }
    public int compareTo(Customer o) {
        if(o.priority == this.priority)
            return 0;
        else if(this.priority > o.priority)
            return 1;
        else
            return -1;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}


