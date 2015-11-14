

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	public int compare(Node o1, Node o2) {
		if (o1.f_distance < o2.f_distance) return -1;
		if (o1.f_distance > o2.f_distance) return 1;
		return 0;
	}

}
