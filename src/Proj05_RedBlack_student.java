import java.util.ArrayList;

public class Proj05_RedBlack_student implements Proj04_Dictionary {
	private Proj05_RedBlackNode root;
	
	@Override
	public void insert(int key, String value) {
		Proj05_RedBlackNode node = new Proj05_RedBlackNode(key, value);
		//Proj05_RedBlackNode current = root;
		if (root == null) {
			root = node; 
			root.isRed = false;
		} else { 
			if (search(key) != null) {
				throw new IllegalArgumentException("Attempt to insert a duplicate key '" + key + "'");
			} else {
				//insert the node
				insertHelper(node, root);
				//rebalance the tree
				//rebalanceTree(node, root);
				//recolor the root if red
				if (root.isRed) {
					root.isRed = false;
				}
			}
		}
	}

	//recursive insertHelper, standard BST insert
	private void insertHelper(Proj05_RedBlackNode child, Proj05_RedBlackNode parent) {
		if (child.key < parent.key) {
			if (parent.left == null) {
				parent.left = child;
				rebalanceTree(parent.left, parent);
			} else {
				insertHelper(child, parent.left);
			}
		} else {
			if (parent.right == null) {
				parent.right = child;
				rebalanceTree(parent.right, parent);
			} else {
				insertHelper(child, parent.right);
			}
		}
	}

	private void rebalanceTree(Proj05_RedBlackNode child, Proj05_RedBlackNode parent) {
		Proj05_RedBlackNode grandparent;
		//case 1: parent is black, return
		//case 2: parent is red, rotation and color change (uncle is red, uncle is black)
		ArrayList<Proj05_RedBlackNode> traversal = pathFinder(parent);
		for (int current = traversal.size() - 1; current > -1; current--) {
			if (traversal.get(current) == root) {
				grandparent = null;
			} else {
				grandparent = traversal.get(current - 1);
			}
			if(!parent.isRed) {
				//NOP
				return;
			} 
			//if parent is red and uncle is red
			if (parent.isRed && grandparent.left.isRed) {
				resolveReds();
			}
			//if parent is red and uncle is black
			if (parent.isRed && !parent.isRed) {
				
			}
			//if parent is red and uncle is black
		}
	}
	
	private ArrayList<Proj05_RedBlackNode> pathFinder(Proj05_RedBlackNode node) {
		Proj05_RedBlackNode presentNode = root;
		ArrayList<Proj05_RedBlackNode> traversal = new ArrayList<>();
		while (node != null) {
			traversal.add(presentNode);
			if (node.key < presentNode.key) {
				presentNode = presentNode.left;
			} else if (node.key > presentNode.key) {
				presentNode = presentNode.right;
			} else {
				break;
			}
		}
		return traversal;
	}
	
	private void resolveReds() {
		
	}
	private static boolean colorChecker(Proj05_RedBlackNode node) {
		if (node == null) {
			return false;
		}
		return node.isRed;
	}
	private void recolor(Proj05_RedBlackNode node, boolean parentValue, boolean childValue) {
		//recolor node and children
		node.isRed = parentValue;
		node.left.isRed = childValue;
		node.right.isRed = childValue;
	}
	
	private void singleLeftRotation(Proj05_RedBlackNode node, Proj05_RedBlackNode parent) {
		Proj05_RedBlackNode rightNode = node.right;
		if (node == root) {
			root = rightNode;
		} else {
			if (parent.left == node) {
				parent.left = rightNode;
			} else {
				parent.right = rightNode;
			}
		}
		node.right = rightNode.left;
		rightNode.left = node;
	}

	private void singleRightRotation(Proj05_RedBlackNode node, Proj05_RedBlackNode parent) {
		Proj05_RedBlackNode leftNode = node.left;
		if (node == root) {
			root = leftNode;
		} else {
			if (parent.left == node) {
				parent.left = leftNode;
			} else {
				parent.right = leftNode;
			}
		}
		node.left = leftNode.right;
		leftNode.right = node;
	}
	
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
		// Delete function throws exception 
		// as delete is not required
		throw new IllegalArgumentException("ERROR: delete() is not implemented in the red-black tree.");
	}
	
	@Override
	public void genDebugDot(String filename) {
		// generate dot files to help with debug

	}

}
