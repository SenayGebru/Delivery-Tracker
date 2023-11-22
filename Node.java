/**
 * Represents a node in a doubly linked list. 
 * The type parameter T must implement the Comparable interface to enable sorting.
 * @param <T> The type of elements held in this node.
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    // The data held by this node
    private T payload;
    // Pointers to the next and previous nodes in the list
    private Node<T> next;
    private Node<T> prev;

    /**
     * Constructs a node with the given payload.
     * Initializes next and previous pointers as null.
     * @param payload The data to store in this node.
     */
    public Node(T payload) {
        this.payload = payload;
        this.next = null;
        this.prev = null;
    }

    /**
     * Returns the data held by this node.
     * @return The node's payload.
     */
    public T getPayload() {
        return payload;
    }

    /**
     * Sets the data held by this node.
     * @param payload The data to set.
     */
    public void setPayload(T payload) {
        this.payload = payload;
    }

    /**
     * Returns the next node this node points to.
     * @return The next node.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next node this node should point to.
     * @param next The node to set as the next node.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Returns the previous node this node points to.
     * @return The previous node.
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * Sets the previous node this node should point to.
     * @param prev The node to set as the previous node.
     */
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    /**
     * Compares this node's payload to another node's payload.
     * @param other The other node to compare to.
     * @return A negative integer, zero, or a positive integer as this node's payload 
     * is less than, equal to, or greater than the other node's payload.
     */
    @Override
    public int compareTo(Node<T> other) {
        return this.payload.compareTo(other.getPayload());
    }

    /**
     * Returns a string representation of this node's payload.
     * @return A string representation of the payload.
     */
    @Override
    public String toString() {
        return payload.toString();
    }
}
