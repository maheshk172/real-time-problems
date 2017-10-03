package org.refresher;

public class CircularLinkedListTraversal  implements CircularTraversal {

    public int removeNextAndGiveKnifeToNext(int[] elements) {
        Node survivor = findLastSurvivor(createNodes(elements));
        return survivor.data;
    }

    public Node createNodes(int[] elements) {
        int i = 0;
        Node root = new Node(elements[i]);
        Node current = root;
        for (i = 1; i < elements.length; i++) {
            current.next = new Node(elements[i]);
            current = current.next;
        }

        current.next = root;
        return root;
    }

    /*
     * Traverse using LinkedList
     */
    public Node findLastSurvivor(Node current) {
        System.out.println("Current is: " + current.data);
        if (current.next == null) {
            return current;
        }

        if (current.next.next == current) {
            current.next = null;
        } else {

            // delete the current's next node
            Node tempNode = current.next;
            current.next = current.next.next;
            tempNode.next = null;
            tempNode = null;

            current = current.next;
        }

        // recurse the findLastSurvivor
        return findLastSurvivor(current);
    }


}

