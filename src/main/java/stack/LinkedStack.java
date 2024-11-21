package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 * <p></p>
 * The {@code isEmpty} and {@code size} methods are provided,
 * on the house.
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {

    Node<T> first;
    int size;

    /**
     * {@inheritDoc}.
     */
    @Override
    public T pop() throws StackUnderflowException {
        // Throws exception if stack is empty
        if (first == null) throw new StackUnderflowException();

        // Holds value to be returned in memory
        T returnValue = first.getElement();

        // Gets rid of first element
        first = first.getNext();
        size--;
        return returnValue;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public T top() throws StackUnderflowException {
        // Throws exception if Stack is empty
        if (first == null) throw new StackUnderflowException();

        // Returns first Element
        return first.getElement();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void push(T elem) throws NullPointerException {
        // Throws exception if argument is null
        if (elem == null) throw new NullPointerException();

        // Makes node to be added
        Node<T> newNode = new Node<>(elem);

        // resets pointers
        newNode.setNext(first);
        first = newNode;

        size++;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int size() {
        return size;
    }
}
