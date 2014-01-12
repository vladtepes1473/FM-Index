package FMIndex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreePrinter {
	
	private static int maxDepth(Node node){
		if(node == null)
			return 0;
		int leftDepth = maxDepth(node.getLeft());
		int rightDepth = maxDepth(node.getRight());
		if(leftDepth > rightDepth)
			return leftDepth + 1;
		else
			return rightDepth + 1;
	}
	
	public static void print(Node root){
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(root);
		printInternal(nodes, 1, maxDepth(root));
	}

	private static void printInternal(List<Node> nodes, int level, int maxLevel){
		if(nodes.size() == 0)
			return;
		
		int counter = 0;
		
		for(int i =0;i<nodes.size();i++){
			if(nodes.get(i)==null)
				counter++;
		}
		
		if(counter==nodes.size()){
			return;
		}
		
		int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        LinkedList<Node> newNodes = new LinkedList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print((char)(byte)node.getPivot() + ";" + node.getContentLength());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printInternal(newNodes, level + 1, maxLevel);
	}
	
    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

}
