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
				//rebalanceTree(parent.left, parent);
			} else {
				insertHelper(child, parent.left);
			}
		} else {
			if (parent.right == null) {
				parent.right = child;
				//rebalanceTree(parent.right, parent);
			} else {
				insertHelper(child, parent.right);
			}
		}
		if (parent.isRed == false) {
			rebalanceTree(parent);
		}
	}
	private void rebalanceTree(Proj05_RedBlackNode grandparent) {
		//case 1: if parent is black, return
		//case 2: if parent is red and uncle is black
		//case 3: if parent is red and uncle is red
		
		//check both children, if both are black just return
		if (colorChecker(grandparent.left) == false && colorChecker(grandparent.right) == false) {
			return;
		}
		
		//seeking red-red problems
		if (colorChecker(grandparent.left) == true) {
			if (colorChecker(grandparent.right) == false) {
				if (colorChecker(grandparent.left.left) == true) {
					//single right rotation, recolor nodes
					singleRightRotation(grandparent.left.left, grandparent.left);
					//recolor(grandparent
				} else if (colorChecker(grandparent.left.right) == true) {
					//single rotate left, single rotate right, recolor nodes
					leftRightRotation(grandparent.left, grandparent);
					//recolor(grandparent
				}
			} else {
				recolor(grandparent, true, false);
			}
		}
		
		if (colorChecker(grandparent.right) == true) {
			if (colorChecker(grandparent.left) == false) {
				if (colorChecker(grandparent.right.left) == true) {
					//single right rotation, single left rotation, recolor nodes
					rightLeftRotation(grandparent.right, grandparent);
					//recolor(grandparent, 
				} else if (colorChecker(grandparent.right.right) == true) {
					//single left rotation, recolor nodes
					singleLeftRotation(grandparent.right.right, grandparent.right);
					//recolor(grandparent
				}
			} else {
				recolor(grandparent, true, false);
			}
		}
	}
	
	private void resolveRedsManager(Proj05_RedBlackNode grandparent) {
		
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
	
	private void singleLeftRotation(Proj05_RedBlackNode parent, Proj05_RedBlackNode grandparent) {
		Proj05_RedBlackNode rightNode = grandparent.right;
		if (grandparent == root) {
			root = rightNode;
		} else {
			if (grandparent.left == grandparent) {
				grandparent.left = rightNode;
			} else {
				grandparent.right = rightNode;
			}
		}
		grandparent.right = rightNode.left;
		rightNode.left = grandparent;
	}
	private void leftRightRotation(Proj05_RedBlackNode parent, Proj05_RedBlackNode grandparent) {
		// the node's left child, and the left child's right child (grand child)
		Proj05_RedBlackNode leftNode = parent.left;
		Proj05_RedBlackNode rightChild = leftNode.right;

		if (parent == root) {
			root = rightChild;
		} else {
			if (grandparent.left == parent) {
				grandparent.left = rightChild;
			} else {
				grandparent.right = rightChild;
			}
		}

		parent.left = rightChild.right;
		leftNode.right = rightChild.left;
		rightChild.left = leftNode;
		rightChild.right = parent;
	}

	private void singleRightRotation(Proj05_RedBlackNode parent, Proj05_RedBlackNode grandparent) {
		Proj05_RedBlackNode leftNode = parent.left;
		if (parent == root) {
			root = leftNode;
		} else {
			if (grandparent.left == parent) {
				grandparent.left = leftNode;
			} else {
				grandparent.right = leftNode;
			}
		}
		parent.left = leftNode.right;
		leftNode.right = parent;
	}
	
	private void rightLeftRotation(Proj05_RedBlackNode parent, Proj05_RedBlackNode grandparent) {
		Proj05_RedBlackNode rightNode = grandparent.right;
		Proj05_RedBlackNode leftChild = rightNode.left;

		if (grandparent == root) {
			root = leftChild;
		} else {
			if (grandparent.left == grandparent) {
				grandparent.left = leftChild;
			} else {
				grandparent.right = leftChild;
			}
		}

		grandparent.right = leftChild.left;
		rightNode.left = leftChild.right;
		leftChild.left = grandparent;
		leftChild.right = rightNode;

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
	/*
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
			if (parent == grandparent.left && grandparent.right.isRed) {
				recolor(grandparent, true, false);
				child = grandparent;
				//resolveReds();
			} else if (parent == grandparent.right && grandparent.left.isRed) {
				recolor(grandparent, true, false);
				child = grandparent;
				//resolveReds();
			}
			//if parent is red and uncle is black
			if (parent == grandparent.left && !grandparent.right.isRed) {
				if (child == parent.right) {
					child = parent;
					parent.isRed = false;
					grandparent.isRed = true;
					singleLeftRotation(child, grandparent);
				//case three ??	
				}
			} else if (parent == grandparent.right && !grandparent.right.isRed) {
				if (child == parent.right) {
					child = parent;
					parent.isRed = false;
					grandparent.isRed = true;
					singleLeftRotation(child, grandparent);
				//case three ??
				}
			}
			
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
	*/

}
