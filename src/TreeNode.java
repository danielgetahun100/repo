import org.w3c.dom.Node;

/**
 * @author DTekabe
 * @param <T>
 */
public class TreeNode<T> {
	T dataNode;
	TreeNode<T> leftNode;
	TreeNode<T> rightNode;

	public TreeNode(T dataNode) {
	leftNode=null;
	rightNode=null;
	this.dataNode=dataNode;
		
	}
	public TreeNode(TreeNode<T> node) {
		
		this.dataNode=node.dataNode;
		this.leftNode=new TreeNode<>(node.leftNode);
		this.rightNode=new TreeNode<>(node.rightNode);
		
	}
	public T getData() {
		return this.dataNode;
	}
	public TreeNode<T> getRightNode() {
		return rightNode;
	}
	public TreeNode<T> getLeftNode() {
		return leftNode;
	}
}
