package edu.iastate.cs228.hw4;

/**
 *  
 * @author Steve Kautz 
 *
 */


import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Sample implementation of a stack using an expandable array as the
 * backing data structure.  Elements are added and removed at the
 * end of the array.
 */
public class ArrayBasedStack<E> implements PureStack<E>
{
  private static final int DEFAULT_SIZE = 10;
  
  /**
   * Index of next available cell in array.
   */
  private int top;
  
  /**
   * The backing array to store the data
   */
  private E[] data;
  
  /**
   * Constructs an empty array.
   */
  public ArrayBasedStack()
  {
    // Unchecked warning is unavoidable
    data = (E[]) new Object[DEFAULT_SIZE];
  }
  
  
  /**
   * @return - true if backing array is empty, false otherwise
   */
  @Override
  public boolean isEmpty()
  {
    return top == 0;
  }

  /**
   * @return - the last value placed in the array, if top > 0, otherwise throws NoSuchElementException
   */
  @Override
  public E peek()
  {
    if (top == 0) throw new NoSuchElementException();
    return data[top - 1];
  }

  /**
   * @return - remove the last item placed on the array
   */
  @Override
  public E pop()
  {
    if (top == 0) throw new NoSuchElementException();
    E ret = data[--top];
    data[top] = null;
    return ret;
  }

  /**
   * Push an item onto the backing array
   * @param item - the item to put on the backing array
   */
  @Override
  public void push(E item)
  {
    checkCapacity();
    data[top++] = item;
  }

  /**
   * Accessor method to return the size of the backing array
   * @return the size of the array
   */
  @Override
  public int size()
  {
    return top;
  }

  /**
   * Ensures that the backing array has space to store at least 
   * one additional element.
   */
  private void checkCapacity()
  {
    if (top == data.length)
    {
      // create a copy of the data array with double the capacity
      data = Arrays.copyOf(data, data.length * 2);
    }
  }

}
