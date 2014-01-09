package FMIndex;

//mozda pretjerujem s interfaceovima
//al ako cemo mjenjat cvor da nam je lakse
public interface Node {
	public Node getLeft();
	public Node getRight();
	public Byte getPivot();
	public int getContentLength();
}
