package org.mql.java.classes;
//une interface qui implemente des methodes abstraites et qui peut etre utilise par d'autre classe
public interface List<T> {
	public void add(T e);
	public T remove(int index);
	public T get(int index);
	public void set(int index,T e);
	public int size();
	public boolean isEmpty();
	public boolean contains(T e);
	public void clear();
	public int indexOf(T e);
	
	
	
	
	
	

}
