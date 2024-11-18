import java.util.ArrayList;
/**
 * @author Dtekabe
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>{

	TreeNode<String> root;

	/**
	 * constructor calling buildtree
	 */
	public MorseCodeTree() {
		buildTree();		
	}


	/**
	 * return root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * set root
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		this.root=newNode;

	}

	@Override
	public void insert(String code, String result) {

		addNode(root,code,result);

	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {

		//base case
		if(code.length()==1) {

			if(code.equals(".")) {
				root.leftNode=new TreeNode<>(letter);
			}
			else if(code.equals("-")) {
				root.rightNode=new TreeNode<>(letter);
			}

			return;
		}

		char direction=code.charAt(0);
		String remaining=code.substring(1);

		if(direction=='.') {

			if(root.leftNode==null) {
				root.leftNode=new TreeNode<>(null);
			}

			addNode(root.leftNode,remaining,letter);

		}

		else if(direction=='-') {
			if (root.rightNode == null) {
				root.rightNode = new TreeNode<>(null); 
			}
			addNode(root.rightNode, remaining, letter);

		}
	}
	@Override
	public String fetch(String code) {

		return fetchNode(root,code);
	}

	/**
	 * a method that fetches the data in a node 
	 */

	@Override
	public String fetchNode(TreeNode<String> root, String code) {

		if(code.length()==0) {
			return root.getData();
		}

		char direction = code.charAt(0);
		String remaining= code.substring(1);

		if(direction=='.') {
			if(root.leftNode!=null) {
				return fetchNode(root.leftNode,remaining);
			}
		}
		else if(direction=='-') {

			if(root.rightNode!=null) {
				return fetchNode(root.rightNode,remaining);
			}
		}
		throw new IllegalArgumentException();

	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {

		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * builds the required tree
	 */
	@Override
	public void buildTree() {

		setRoot(new TreeNode<String>(""));

		//level 2
		insert(".","e");
		insert("-","t");

		//level 3
		insert("..","i");
		insert(".-","a");
		insert("-.","n");
		insert("--","m");

		//level 4
		insert("...","s");
		insert("..-","u");
		insert(".-.","r");
		insert(".--","w");

		insert("-..","d");
		insert("-.-","k");
		insert("--.","g");
		insert("---","o");

		//level 5

		insert("....","h");
		insert("...-","v");
		insert("..-.","f");

		insert(".-..","l");
		insert(".--.","p");
		insert(".---","j");

		insert("-...","b");
		insert("-..-","x");
		insert("-.-.","c");
		insert("-.--","y");

		insert("--..","z");
		insert("--.-","q");





	}

	/**
	 * prints the tree in a LNR traversal
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(root, list); // Start from the root
		return list;
	}

	/**
	 * performs the LNR traversal
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {

		if(root==null) {
			return;
		}

		LNRoutputTraversal(root.getLeftNode(),list);

		list.add(root.getData());

		LNRoutputTraversal(root.getRightNode(),list);

	}



}
