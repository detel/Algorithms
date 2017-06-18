import java.util.ArrayList;
import java.util.Stack;

public class BlockCutTree {

	public static int n, time;
	public static Stack<Integer> stack;
	public static ArrayList<Integer>[] graph;
	public static int[] num, low, art, belongsTo;
	public static ArrayList<ArrayList<Integer>> comps, tree;
    
	public static void init() {
		
		num = new int[n];
		low = new int[n];
		art = new int[n];
		stack = new Stack<Integer>();
		comps = new ArrayList<ArrayList<Integer>>();
		
		for (int u = 0; u < n; ++u) {
			if (num[u] != 0) {
				continue;
			}
			dfs(u, -1);
		}
		buildTree();
	}
	
	private static void dfs(int u, int p) {
		num[u] = low[u] = ++time;
		stack.push(u);
 
		for (int v : graph[u]) {
			if (v == p) {
				continue;
			}
			if(num[v] != 0) {
				low[u] = Math.min(low[u], low[v]);
				continue;
			}
			
			dfs(v, u);
			low[u] = Math.min(low[u], low[v]);
			
			if (low[v] >= num[u]) {
				art[u] = (num[u] > 1 || num[v] > 2) ? 1 : 0;
				
				int lastIdx = comps.size();
				comps.add(new ArrayList<Integer>());
				comps.get(lastIdx).add(u);
				while (comps.get(lastIdx).get(comps.get(lastIdx).size() - 1) != v) {
					comps.get(lastIdx).add(stack.pop());
				}
			}	
		}
	}
	
	private static void buildTree() {
		tree = new ArrayList<>();

		belongsTo = new int[n];
		for (int i = 0; i < n; i++) {
			if(art[i] == 0) {
				continue;
			}
			belongsTo[i] = addNode();
		}
		
		for(ArrayList<Integer> list : comps) {
			int node = addNode();
			for(Integer u : list) {
				if(art[u] == 0) {
					belongsTo[u] = node;
					continue;
				}
				tree.get(node).add(belongsTo[u]);
				tree.get(belongsTo[u]).add(node);
			}
		}
	}
	
	private static int addNode() {
		tree.add(new ArrayList<Integer>());
		return tree.size() - 1;
	}
}
