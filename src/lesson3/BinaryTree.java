package lesson3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> parent = null;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
            newNode.parent = closest;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
            newNode.parent = closest;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private Node<T> minimum(Node<T> root) {
        while (root.left != null) root = root.left;
        return root;
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     *
     * O(lgn) - трудоёмкость
     * O(1) - ресурсоемкость
     *
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        T value = (T) o;
        Node<T> node = find(value);
        if (node != null && node.value != value) throw new IllegalArgumentException();
        return remove(node);
    }

    private boolean remove(Node<T> node) {
        if (node == null) return false;
        if (node.left == null) editTo(node, node.right);
        else if (node.right == null) editTo(node, node.left);
        else {
            Node<T> temp = minimum(node.right);
            if (temp.parent != node) {
                editTo(temp, temp.right);
                temp.right = node.right;
                temp.right.parent = temp;
            }
            editTo(node, temp);
            temp.left = node.left;
            temp.left.parent = temp;
        }
        size--;
        return true;
    }

    private void editTo(Node<T> to, Node<T> from) {
        if (to.parent == null) root = from;
        else if (to.equals(to.parent.left)) to.parent.left = from;
        else to.parent.right = from;
        if (from != null) from.parent = to.parent;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    private Node<T> findNext(Node<T> temp) {
        if (temp == null) return minimum(root);
        if (temp.right != null) return minimum(temp.right);
        Node<T> temp2 = temp.parent;
        while (temp2 != null && temp == temp2.right) {
            temp = temp2;
            temp2 = temp2.parent;
        }
        return temp2;
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current;
        private Node<T> prev = null;

        private BinaryTreeIterator() {
            if (root == null) current = null;
            else current = minimum(root);
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         *
         * O(1) - трудоёмкость
         * O(1) - ресурсоемкость
         *
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Поиск следующего элемента
         * Средняя
         *
         * O(lgn) - трудоёмкость
         * O(1) - ресурсоемкость
         *
         */
        @Override
        public T next() {
            prev = current;
            current = findNext(current);
            if (prev == null) throw new NoSuchElementException();
            return prev.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         *
         * O(lgn) - трудоёмкость
         * O(1) - ресурсоемкость
         *
         */
        @Override
        public void remove() {
            BinaryTree.this.remove(prev);
            prev = null;
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     *
     * O(1) - трудоёмкость
     * O(1) - ресурсоемкость
     *
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
       return new Sets(this, fromElement, toElement);
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     *
     * O(1) - трудоёмкость
     * O(1) - ресурсоемкость
     *
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        return new Sets(this, null, toElement);
    }
    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     *
     * O(1) - трудоёмкость
     * O(1) - ресурсоемкость
     *
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return new Sets(this, fromElement, null);
    }

    class Sets extends BinaryTree<T> {
        private final T from, to;
        private BinaryTree<T> root;

        Sets(BinaryTree<T> root, T from, T to) {
            this.root = root;
            this.from = from;
            this.to = to;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean contains(Object o) {
            if (!inRange((T) o)) return false;
            return root.contains(o);
        }

        @Override
        public boolean add(T t) {
            if (!inRange(t)) throw new IllegalArgumentException();
            return root.add(t);
        }

        @Override
        public int size() {
            return size(root.root);
        }

        private int size(Node<T> node) {
            int size = 0;
            if (node == null) return size;
            if (inRange(node.value)) size++;
            return size + size(node.left) + size(node.right);
        }

        private boolean inRange(T key) {
            return (from == null || key.compareTo(from) >= 0) && (to == null || key.compareTo(to) < 0);
        }

    }
    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
