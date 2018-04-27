
public class Proj05_RedBlack_student implements Proj04_Dictionary {
	private Proj05_RedBlackNode root;
	
	@Override
	public void insert(int key, String value) {
		if (root == null) {
			root = new Proj05_RedBlackNode(key, value); 
		} else { 
			if (search(key) != null) {
				throw new IllegalArgumentException("Attempt to insert a duplicate key '" + key + "'");
			} else {
				Proj05_RedBlackNode node = new Proj05_RedBlackNode(key, value);
				Proj05_RedBlackNode current = root;
				while (current != null) {
					if (node.key < current.key) {
						current = current.left;
					} else if (node.key > current.key) {
						current = current.right;
					} else {
						current = node;
					}
				}
			}
		}
	}
	/* might use this implementation instead if above does not work
	private void insertHelper(Proj05_RedBlackNode key, Proj05_RedBlackNode parent) {
		if (key.key < parent.key) {
			if (parent.left == null) {
				parent.left = key;
			} else {
				insertHelper(key, parent.left);
			}
		} else {
			if (parent.right == null) {
				parent.right = key;
			} else {
				insertHelper(key, parent.right);
			}
		}
	}
	*/
	
	/* search method accepts an int parameter as the key of 
	 * the node being searched for. If the key is not in the
	 * tree, null is returned. Otherwise, it returns a string.
	 * search utilizes a while loop that traverses the tree 
	 * seeking the key value.
	 */
	@Override
	public String search(int key) {
		Proj05_RedBlackNode current = root;
		while (current != null) {
			if (key == current.key) {
				return current.value;
			} else if (key < current.key) {
				current = current.left;
			} else if (key > current.key) {
				current = current.right;
			}
		}
		return null;
	}

	@Override
	public void printInOrder() {
		// left root right
		// recursion
		if (root == null) {
			return;
		} else {
			inOrderHelper(root);
		}
	}

	private void inOrderHelper(Proj05_RedBlackNode node) {
		if (node == null) {
			return;
		} else {
			inOrderHelper(node.left);
			System.out.print(" " + node.key + ":'" + node.value + "'");
			inOrderHelper(node.right);
		}
	}

	@Override
	public void printPreOrder() {
		// root left right
		if (root == null) {
			return;
		} else {
			preOrderHelper(root);
		}
	}

	private void preOrderHelper(Proj05_RedBlackNode node) {
		if (node == null) {
			return;
		} else {
			System.out.print(" " + node.key + ":'" + node.value + "'");
			preOrderHelper(node.left);
			preOrderHelper(node.right);
		}
	}

	@Override
	public void printPostOrder() {
		// left right root
		// recursion
		if (root == null) {
			return;
		} else {
			postOrderHelper(root);
		}
	}

	private void postOrderHelper(Proj05_RedBlackNode node) {
		if (node == null) {
			return;
		} else {
			postOrderHelper(node.left);
			postOrderHelper(node.right);
			System.out.print(" " + node.key + ":'" + node.value + "'");
		}

	}
	
	//Unimplemented methods
	@Override
	public void delete(int key) {
		// Delete function is a stub function 
		// as delete is not required

	}
	
	@Override
	public void genDebugDot(String filename) {
		// generate dot files to help with debug

	}

}
