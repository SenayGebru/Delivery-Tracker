public class LinkedList<T extends Comparable<T>> {
    private Node<T> head; // First node in the list
    private Node<T> tail; // Last node in the list

    /**
     * Default constructor. Initializes an empty linked list.
     */
    public LinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Constructs a linked list with a single node.
     * @param node The node to initialize the linked list with.
     */
    public LinkedList(Node<T> node) {
        head = node;
        tail = node;
    }

    /**
     * Returns the head (first node) of the linked list.
     * @return The head node.
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Returns a string representation of the linked list.
     * Each node's data is represented on a new line.
     * @return A string representation of the linked list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            result.append(current.toString()).append("\n");// calls Node.java's toString method, not recursion 
            current = current.getNext();
        }
        return result.toString();
    }

    /**
     * Sorts the linked list of Driver objects by name.
     * Uses bubble sort logic to perform the sorting.
     * @param order Determines if the list should be sorted in ascending or descending order.
     */
    public void sortByName(String order) {
        if (head == null || head.getNext() == null) {
            return; // If list is empty or has only one element, no sorting is needed
        }
        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> next = current.getNext();
            Node<T> prev = null;

            while (next != null) {
                int comparison = ((Driver) current.getPayload()).getName().compareTo(((Driver) next.getPayload()).getName());
                if (("dsc".equalsIgnoreCase(order) && comparison < 0) || (!"dsc".equalsIgnoreCase(order) && comparison > 0)) {
                    if (prev != null) {
                        prev.setNext(next);
                    } else {
                        head = next;
                    }
                    current.setNext(next.getNext());
                    next.setNext(current);
                    prev = next;
                    next = current.getNext();
                } else {
                    prev = current;
                    current = next;
                    next = next.getNext();
                }
            }
        } while (swapped);
    }

    /**
     * Sorts the linked list of Driver objects by area.
     * Uses bubble sort logic to perform the sorting.
     * @param order Determines if the list should be sorted in ascending or descending order.
     */
    public void sortByArea(String order) {
        if (head == null || head.getNext() == null) {
            return; // If list is empty or has only one element, no sorting is needed
        }
        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> next = current.getNext();
            Node<T> prev = null;

            while (next != null) {
                double currentArea = ((Driver) current.getPayload()).getArea();
                double nextArea = ((Driver) next.getPayload()).getArea();
                boolean shouldSwap = "dsc".equalsIgnoreCase(order) ? currentArea < nextArea : currentArea > nextArea;

                if (shouldSwap) {
                    if (prev != null) {
                        prev.setNext(next);
                    } else {
                        head = next;
                    }
                    current.setNext(next.getNext());
                    next.setNext(current);
                    prev = next;
                    next = current.getNext();
                } else {
                    prev = current;
                    current = next;
                    next = next.getNext();
                }
            }
        } while (swapped);
    }

    /**
     * Sorts the linked list in ascending order using the bubble sort algorithm.
     */
    public void sortAscending() {
        boolean swapped;
        Node<T> current;
        Node<T> next = null;
        do {
            swapped = false;
            current = head;
            while (current.getNext() != next) {
                if (current.getPayload().compareTo(current.getNext().getPayload()) > 0) {
                    T temp = current.getPayload();
                    current.setPayload(current.getNext().getPayload());
                    current.getNext().setPayload(temp);
                    swapped = true;
                }
                current = current.getNext();
            }
            next = current;
        } while (swapped);
    }

    /**
     * Sorts the linked list in descending order using the bubble sort algorithm.
     */
    public void sortDescending() {
        boolean swapped;
        Node<T> current;
        Node<T> next = null;
        do {
            swapped = false;
            current = head;
            while (current.getNext() != next) {
                if (current.getPayload().compareTo(current.getNext().getPayload()) < 0) {
                    T temp = current.getPayload();
                    current.setPayload(current.getNext().getPayload());
                    current.getNext().setPayload(temp);
                    swapped = true;
                }
                current = current.getNext();
            }
            next = current;
        } while (swapped);
    }

/**
 * Searches for a Driver in the linked list by the provided name.
 * @param name The name of the driver to search for.
 * @return The found Driver object or null if not found.
 */
public Driver searchByName(String nameToSearch) {
    Node<T> current = head;
    
    // Iterate through each node in the linked list.
    while (current != null) {
        Driver driver = (Driver) current.getPayload();
        if (driver.getName().trim().equalsIgnoreCase(nameToSearch.trim())) {
            return driver; // If found, return the driver.
        }
        current = current.getNext();
    }
    
    return null; // Return null if not found.
}


    /**
     * Adds a new node with the given data to the end of the linked list.
     * @param data The data to add to the linked list.
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
    }

    /**
     * Returns the number of elements in the linked list.
     * @return The number of elements.
     */
    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
