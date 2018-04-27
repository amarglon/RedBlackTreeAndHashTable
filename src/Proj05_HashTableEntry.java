/* Proj05_HashTableEntry
 *
 * Author: Russ Lewis
 */

public class Proj05_HashTableEntry
{
	public int    key;
	public String value;

	public Proj05_HashTableEntry next;


	public Proj05_HashTableEntry(int key, String value)
	{
		this.key   = key;
		this.value = value;
		  // next defaults to null
	}
}

