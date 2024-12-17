package _01_array_list_visualizer.data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

@SuppressWarnings("unchecked")
public class ArrayList<T> extends java.util.ArrayList<T> {
	public ArrayListDisplayPanel<T> displayPanel = new ArrayListDisplayPanel<T>(this);
	
	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index - index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size())
			throw new IndexOutOfBoundsException();
		return super.get(index);
	}

	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param e - element to be appended to this list
	 * @return true (as specified by Collection.add(E))
	 */
	@Override
	public boolean add(T e) {
		super.add(e);

		// Display list
		Action<T> action = new Action<T>(Action.ADD, -1, e);
		action.x = ArrayListDisplayPanel.WIDTH + 50;
		action.y = displayPanel.listGraphicsHeight;
		return displayPanel.actionQueue.add(action);
	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts
	 * the element currently at that position (if any) and any subsequent elements
	 * to the right (adds one to their indices).
	 * 
	 * @param index   - index at which the specified element is to be inserted
	 * @param element - element to be inserted
	 * @throws IndexOutOfBoundsException
	 * @return true (as specified by Collection.add(E))
	 */
	public void add(int index, T element) throws IndexOutOfBoundsException {
		super.add(index, element);

		// Display list
		Action<T> action = new Action<T>(Action.INSERT, index, element);
		action.x = ArrayListDisplayPanel.WIDTH + 50;
		action.y = displayPanel.listGraphicsHeight - 100;
		if (!displayPanel.actionQueue.add(action)) {
			throw new RuntimeException("Queue.add() should always return true");
		}
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 * 
	 * @param index   - index of the element to replace
	 * @param element - element to be stored at the specified position
	 * @return The element previously at the specified index
	 * @throws IndexOutOfBoundsException - if the index is out of range(index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public T set(int index, T element) throws IndexOutOfBoundsException {
		T e = super.set(index, element);
		
		// Display list
		Action<T> action = new Action<T>(Action.SET, index, element);
		action.x = ArrayListDisplayPanel.WIDTH + 50;
		action.y = displayPanel.listGraphicsHeight - 100;
		displayPanel.actionQueue.add(action);
		return e;
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * 
	 * @param index - the index of the element to be removed
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException if the index is out of range(index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		T e = super.remove(index);

		// Display list
		Action<T> action = new Action<T>(Action.REMOVE, index, null);
		action.x = 20 + action.index * 50;
		action.y = displayPanel.listGraphicsHeight;
		displayPanel.actionQueue.add(action);
		return e;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it
	 * is present. If the list does not contain the element, it is unchanged. More
	 * formally, removes the element with the lowest index i such that (o==null ?
	 * get(i)==null : o.equals(get(i)))(if such an element exists). Returns true if
	 * this list contained the specified element (or equivalently, if this list
	 * changed as a result of the call).
	 * 
	 * @param o - element to be removed from this list, if present
	 * @return true if this list contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index == -1) {
			return false;
		} else {
			remove(index);
			return true;
		}
	}

	public void draw() {
		// TODO Auto-generated method stub
		displayPanel.repaint();
	}

	private void printExpected() {
		String exList = "";
		for (T t : this) {
			exList += t.toString() + ", ";
		}
		System.out.println(exList.substring(0, exList.length() - 2) + " => Size: " + this.size());
	}

	// @Override
	// public Object clone() {
	// 	ArrayList<T> clone = (ArrayList<T>)super.clone();
	// 	assert this.displayPanel != null;
	// 	clone.displayPanel = this.displayPanel;
	// 	return clone;
	// }
}
