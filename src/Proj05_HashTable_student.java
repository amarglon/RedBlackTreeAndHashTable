import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Proj05_HashTable_student implements Proj04_Dictionary {
	private Proj05_HashTableEntry[] hashtable = new Proj05_HashTableEntry[4];
	private int count = 0;
	public void Proj_05_HashTable_student(){
		//this.hashtable = new Proj05_HashTableEntry[4];
		//this.count = 0;
	}
	
	@Override
	public void insert(int key, String value) {
		//need to remember to resize hashtable
		//System.out.println("insert(): before search");
		if (search(key) != null) {
			throw new IllegalArgumentException("Attempt to insert a duplicate key '" + key + "'");
		}
		Proj05_HashTableEntry entry = new Proj05_HashTableEntry(key, value);
		int hashValue = hash(entry.key);
		if (hashtable[hashValue] == null) {
			hashtable[hashValue] = entry;
		} else {
			Proj05_HashTableEntry temp = hashtable[hashValue];
			hashtable[hashValue] = entry;
			entry.next = temp;
		}
		count++;
		if (count >= hashtable.length/2) {
			ArrayList<Proj05_HashTableEntry> array = extract();
			hashtable = new Proj05_HashTableEntry[(hashtable.length) * 2];
			for (Proj05_HashTableEntry i : array) {
				if (i != null) {
					insert(i.key, i.value);
				}
			}
		}
	}
	private ArrayList<Proj05_HashTableEntry> extract() {
		ArrayList<Proj05_HashTableEntry> tableArray = new ArrayList<>();
		Proj05_HashTableEntry search;
		for (int i = 0; i < hashtable.length; i ++) {
			search = hashtable[i];
			while (search != null) {
				tableArray.add(search);
				search = search.next;
			}
		}
		return tableArray;
	}
	//implement hash function
	private int hash(int key) {
		int hash;
		//System.out.println("hash(): before hashing");
		//System.out.println(hashtable.length);
		hash = (key * 3721) % hashtable.length;
		return hash;
	}
	
	@Override
	public String search(int key) {
		
		int i = hash(key);
		Proj05_HashTableEntry search = hashtable[i];
		while (search != null) {
			if (search.key == key) {
				return search.value;
			} else {
				search = search.next;
			}
		}
		return null;
	}

	@Override
	public void delete(int key) {
		if (search(key) == null) {
			throw new IllegalArgumentException("Attempt to delete a nonexistant key '" + key + "'");
		}
		int i = hash(key);
		//System.out.println(i);
		hashtable[i] = deleteHelper(hashtable[i], null, key);
	}
	
	//recursive delete function
	//not sure if I need to involve the parent pointer to keep everything linked
	private Proj05_HashTableEntry deleteHelper(Proj05_HashTableEntry entry, Proj05_HashTableEntry parent, int key){
		//System.out.println("in delete helper");
		if (entry.key == key) {
			if (parent == null) {
				Proj05_HashTableEntry temp = entry.next;
				if (temp == null) {
					//System.out.println("no parent, no child, entry is null");
					entry = null;
					return entry;
				}
				//System.out.println("no parent, entry becomes child");
				entry.next = null;
				entry = temp;
				//entry.key = temp.key;
				//entry.value = temp.value;
				//entry.next = entry.next.next;
			} else {
			//	System.out.println("has parent, parent.next becomes child, entry null");
				Proj05_HashTableEntry temp = entry.next;
				entry = null;
				parent.next = temp;
			}
		} else {
			deleteHelper(entry.next, entry, key);
		}
		return entry;
	}

	@Override
	public void printInOrder() {
		printPostOrder();
		
	}

	@Override
	public void printPreOrder() {
		printPostOrder();
		
	}

	@Override
	public void printPostOrder() {
		ArrayList<Proj05_HashTableEntry> array = extract();
		array.sort((Proj05_HashTableEntry entry1, Proj05_HashTableEntry entry2) -> (entry1.key) - (entry2.key));
		for (Proj05_HashTableEntry search : array) {
			if (search != null) {
				System.out.print(" " + search.key + ":'" + search.value + "'");
			}
		}
		/*
		Proj05_HashTableEntry search;
		for (int i = 0; i < hashtable.length; i++) {
			search = hashtable[i];
			while (search != null) {
				System.out.print(" " + search.key + ":'" + search.value + "'");
				search = search.next;
			} 
		}
		*/
	}

	@Override
	public void genDebugDot(String filename) {
		//Method stub
		return;
	}

}
