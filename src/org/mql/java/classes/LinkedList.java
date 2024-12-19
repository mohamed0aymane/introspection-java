package org.mql.java.classes;

import java.util.Iterator;



public class LinkedList<T> implements List<T>,Iterable<T> {
	public T value;//stocke la valeur actuel du type generique  T
	public LinkedList<T> next;//next pour reference vers le noeud suivant  dans la liste chaine
	
	//un constructeur sans parametre
	/*En définissant value et next à null, 
	 *il crée un nœud vide, qui servira généralement de point de départ de la liste chaînée. En pratique, cela permet de créer une instance de LinkedList sans fournir de valeur initiale, laissant ainsi
	 *la possibilité d'ajouter des éléments plus tard avec la méthode add*/
	public LinkedList() {
		value=null;
		next=null;
	}
	public LinkedList(T value) {
		this.value=value;
		next=null;//un singleton = c 'est un noeud isole sans lien 
	}

	@Override
	public boolean isEmpty() {
		return (value==null && next== null);
	}
	


	public void add(T e) {
			if(value==null && next ==null)	value = e;
			//cree un noeud et pose en next
			//ajouter un element ala fin
			else if(next==null) next =new LinkedList<T>(e);
			else next.add(e);//parcourir la liste
			//Si le nœud actuel a déjà une valeur et que next n'est pas null,
			//cela signifie qu'il y a un nœud suivant.
			//On appelle alors récursivement add(e) sur next pour parcourir la liste 
			//jusqu'à atteindre un nœud où next est null, 
			//ajoutant e à la fin de la liste.
	}
	
	public void addLinkList(LinkedList<T> l1) {
	    if (l1 == null || l1.isEmpty()) {
	        return; // Si la liste ajouter est vide ou null rien  faire
	    }
	    
	    LinkedList<T> current = this; // Commence avec l'objet actuel

	    // Trouver  dernier nœud de la liste actuelle
	    while (current.next != null) {
	        current = current.next;
	    }

	    // Ajouter les nœuds de l1 un par un
	    LinkedList<T> l1Current = l1;
	    while (l1Current != null && l1Current.value != null) {
	        current.next = new LinkedList<>(l1Current.value); // Cree un nouveau nœud pour chaque valeur
	        current = current.next; // Avance vers le nouveau dernier nœud
	        l1Current = l1Current.next; // Passe au prochain nœud dans l1
	    }
	}


	@Override
	public T remove(int index) {
		if (index == 0) {//on va supprimer l'element courant
			T removed = value;
			if(next==null) {
				value=null;
		        next=null;
			}else {
				value=next.value;
				next=next.next;
			}
	        return removed;
	    }
		if(next ==null) {
			return null;
		}
		
		if (index==1){//car on veut supprimer que le suivant
			T removed=next.value;//on  va supprimer l'element suivant
			next=next.next;
			return removed;
		}
	    
	    return next.remove(index - 1);  // Continue de parcourir jusqu'à l'index souhaité
	}

	@Override
	public T get(int index) {
	
		if(value == null && next == null) return null;
	    if (index == 0) return value;  // On est arrivé à l'index souhaité
	    if (next == null) return null;//"Index hors limite"
		 return next.get(index - 1); // On passe au nœud suivant et on décrémente l'index
	
	}
	@Override
	public void set(int index, T e) {
	    if (index == 0) {
	        value = e;  // On est à l'index souhaité, on remplace la valeur
	    } else if (next == null) {
	        return;//index hors limite
	    } else {
	        next.set(index - 1, e);  // On continue à chercher l'index en décrémentant
	    }
	}

	
	@Override
	public int size() {
		if(isEmpty())	return 0;
		else if(next==null) return 1;
		return 1+next.size();
	}
	
	@Override
	public boolean contains(T e) {
		 LinkedList<T> current = this; // On commence par le premier nœud
		    while (current != null && current.value != null) { // Tant qu'il y a des éléments dans la liste
		        if (current.value.equals(e)) { // Si l'élément courant est égal à l'élément recherché
		            return true;
		        }
		        current = current.next; // On passe au nœud suivant
		    }
		    return false; // Si l'élément n'est pas trouvé
	}
	
	
	@Override
	public void clear() {
		this.value=null;
		this.next=null;
		
	}
	 @Override
		public int indexOf(T e)  {
			if (isEmpty()) {
				System.out.println("Linkedlist is empty. ");
				return -1; // L'element n'existe pas
			}	
			
			if (this.value !=null && e.equals(this.value)) return 0;
			
			if (this.value !=null && !e.equals(this.value)) {
				if (this.next != null) {
					int index = 1 + this.next.indexOf(e);
	// When reaching the last node, if the element we're looking for doesn't exist,
	// "this.next.indexOf(e)" will return -2, thus index = 1 - 2 = -1; thus it doesn't exist
					if (index > 0) { 
		                return index;
		            }
				}
				else {
					System.out.println("Reached end of linkedlist. Value doesn't exist.");
				} return -2; // End of LinkedList
		} 
			return -3; // This line will never be reached
		} 
	
	
	@Override
	public synchronized String toString() {
	    StringBuilder sb = new StringBuilder("LinkedList [data=[");
	    LinkedList<T> current = this;
	    while (current != null && current.value != null) {
	        sb.append(current.value);
	        if (current.next != null && current.next.value != null) {
	            sb.append(", "); // Ajouter une virgule entre les éléments
	        }
	        current = current.next; // Passer au nœud suivant
	    }
	    sb.append("], size=").append(size()).append("]");
	    return sb.toString();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iter(this);
	}
	
	class Iter implements Iterator<T>{
		private LinkedList<T> pointer;
		
		public Iter(LinkedList<T> pointer) {
			this.pointer=pointer;
			
		}
		
		@Override
		public boolean hasNext() {
			//System.out.println(">> hasNext() invoked");
			return (pointer !=null);
		}

		@Override
		public T next() {
			//System.out.println(">> next() invoked");
			T element=pointer.value;
			pointer =pointer.next;
			return element;
		}
		
	}



}
