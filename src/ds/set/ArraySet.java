package ds.set;

import ds.bag.ArrayBag;

/**
 * Implementation of Static-Size Set using Bag Inheritance
 * @author Gretchen Bonilla
 *
 * @param <E>
 */

public class ArraySet<E> extends ArrayBag<E> {

    public ArraySet(int initialSize){
        super(initialSize);
    }
    public ArraySet(){
        super();
    }
  
    @Override
    public boolean add(E obj) {
        if (this.contains(obj)){
            return false;
        }
        else {
           super.add(obj);
           return true;
        }

    }
  
    /*
     *  Implement union(ArraySet S2). (S1 U S2) (Member method)
     * 
     *  The union operation consists in joining together all the elements of 2 sets into a new one.
     *  
     *  Example:
     *  S1 = {A, B, C}
     *  S2 = {C, D, E}
     *  If we call the following:
     *  S3 = S1.union(S2) 
     *  
     *  Then S3 should have:
     *  {A, B, C, D, E}
     *  
     */
    public ArraySet<E> union(ArraySet<E> S2) {
        // S3 = S1(this) U S2
    	ArraySet<E> S3 = new ArraySet<E>(this.size() + S2.size());
    	// Add all elements of S1 (this)
    	for(E e: this) {
    		S3.add(e);
    	}
    	// Add all elements of S2
    	for (E e: S2) {
    		// Add will take care of duplicates
    		S3.add(e);
    	}
        
        return S3;
    }

    /*
     * Implement difference(ArraySet S2)
     * 
     * The difference method consists of removing the element from one set from the other. 
     * Difference can be represented as such: S1 - S2. In this case we are removing the elements of S2 from S1.
     * The difference removes the elements shared by the two sets.
     * 
     * Example:
     *  S1 = {A, B, C}
     *  S2 = {C, D, E}
     *  Both S1 and S2 share the element C.
     *  If we call the following:
     *  S3 = S1.difference(S2) 
     *  S4 = S2.difference(S1)
     *  
     *  Then S3 and S4 should have:
     *  S3 = {A, B}
     *  S4 = {D, E}
     *  
     * NOTES:
     * - Notice that S1 - S2 does not produce the same result as S2 - S1
     */
    public ArraySet<E> difference(ArraySet<E> S2) {
       ArraySet<E> S3 = new ArraySet<E>();
       for(E e: this ) {
    	   if(!S2.contains(e))
    		   S3.add(e);
       }
       return S3;
    }

    /*
     * Implement intersection.
     * 
     * The intersection operation consists in returning a set that contains only the elements that the 2 sets share.
     * 
     * Example:
     * S1 = {A, B, C, D}
     * S2 = (C, D, E, F}
     * 
     * If we call:
     * S3 = S1.intersection(S2)
     * 
     * Then S3 should be:
     * S3 = {C, D}
     */
    public ArraySet<E> intersection(ArraySet<E> S2) {

        return this.difference(this.difference(S2));
    }

    /*
     * Implement isSubset()
     * 
     * A subSet is a set whose all its elements also belong in another set.
     * 
     * Example:
     * S1 = {A, B, C, D, E}
     * S2 = {B, E}
     * 
     * From observing S1 and S2, we can say that S2 is a subSet of S1 since both B and E appear in S1.
     * We can also say that S1 isn't a subSet of S2 since A, C, and D aren't part of S2.
     * 
     * For the method, if we call S1.isSubset(S2) it should be understood as: Is S1 a subSet of S2?
     * With that in mind then if we call:
     * S1.isSubset(S2) it should return false
     * S2.isSubset(S1) it should return true
     * 
     */
    public boolean isSubset(ArraySet<E> S2) {

        return this.difference(S2).isEmpty();
    }

}