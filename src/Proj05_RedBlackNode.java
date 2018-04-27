/* Proj05_RedBlackNode
 *
 * Author: Russ Lewis
 *
 * Basically the same as the Proj03_BSTNode class, but expanded to include an
 * 'isRed' field.
 */

public class Proj05_RedBlackNode
{
	public int    key;
	public String value;

	public boolean isRed;

	public Proj05_RedBlackNode left,right;


	/* constructor
	 *
	 * Parameters: int,String
	 *
	 * Generates a single RedBlackNode (with no children), with the
	 * key/value pair given.
	 */
	public Proj05_RedBlackNode(int key, String value)
	{
		if (value == null)
			throw new IllegalArgumentException("BSTNode: The 'value' parameter was null");

		this.key    = key;
		this.value  = value;

		this.isRed  = true;

		// the left,right nodes default to 'null' (Java defaults)
	}
}

