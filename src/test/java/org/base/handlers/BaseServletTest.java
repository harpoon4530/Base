package org.base.handlers;

import com.google.common.base.Objects;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseServletTest {

    public class Node {
        private int value;
        private Node prev, next;

        public Node(int x) {
            this.value = x;
            this.prev = null;
            this.next = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value && Objects.equal(prev, node.prev) && Objects.equal(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value, prev, next);
        }
    }

    @Test
    public void testEquality() {

        Node n1 = new Node(1);
        Node n2 = new Node(1);

        System.err.println(n1.equals(n2));

    }

}