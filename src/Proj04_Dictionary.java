/* Proj04_Dictionary
 *
 * Author: Russ Lewis
 *
 * Represents an entire dictionary.  This is a small variant on the
 * BST interface from Project 3; I've removed the rotate*() functions,
 * and added support for pre-order traversals.
 */

public interface Proj04_Dictionary
{
	/* void insert(int,String)
	 *
	 * Inserts a given (key,value) pair into the tree.
	 *
	 * This call may assume that the key is non-negative, and that the
	 * value is not null.  (It may, but is not required to, enforce
	 * these assumptions.)
	 *
	 * Throws IllegalArgumentException if the key already exists in
	 * the tree.
	 */
	void   insert(int key, String value);


	/* String search(int)
	 *
	 * Searches the tree for a given key; returns the associated String
	 * if the key is found, or null if it is not found.
	 */
	String search(int key);


	/* void delete(int)
	 *
	 * Removes a given node from the tree, found by the key.
	 *
	 * Throws IllegalArgumentException if the key does not exist.
	 */
	void   delete(int key);


	/* void printInOrder()
	 *
	 * Performs an in-order traversal of the tree, and prints out all of
	 * the nodes.  It does *not* print out a trailing newline.
	 *
	 * For each node, it prints both the key and the value, separated by
	 * a colon; the various nodes are separated by a space.  (There is
	 * also a space before the first node.)
	 *
	 * If the tree is empty, then it will not print out anything.
	 */
	void printInOrder();


	/* void printPreOrder()
	 *
	 * Same as printInOrder(), except that it performs a pre-order
	 * traversal.
	 */
	void printPreOrder();


	/* void printPostOrder()
	 *
	 * Same as printInOrder(), except that it performs a post-order
	 * traversal.
	 */
	void printPostOrder();


	/* void genDebugDot(String)
	 *
	 * Generates a \texttt{.dot} file which represents the tree; the
	 * String is the name of the file to generate.
	 */
	void genDebugDot(String filename);
}

