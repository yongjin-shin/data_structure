package pack;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Prob2 {
	class Node{
		Character ch;
		Node left;
		Node right;
		
		public Node(Character ch) {
			this.ch = ch;
			this.left = null;
			this.right = null;
		}
	
		public boolean has_child(Node T) {
			if(T.left != null && T.right != null )
				return true;
			return false;
		}
	}
	
	//member
	private Vector<Vector<Character> > exp = new Vector<Vector<Character> >();
	private Vector<Node> tree = new Vector<Node>();
	private Vector<Double> result = new Vector<Double>();
	private String path = "";
	
	//method
	public Prob2(String path) {this.path = path;}
	
	private void read_exp() throws Exception{
		File file = new File(this.path);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			Vector<Character> in_tmp = new Vector<Character>();
			String input = sc.nextLine();
			int size = input.length();
			for(int i = 0; i<size; i++) {
				Character a_letter = input.charAt(i);
				in_tmp.add(a_letter);
			}
			exp.add(in_tmp);
		}
	}
	
	private void bild_expression() {
		int vec_len = this.exp.size();
		for(int i = 0; i<vec_len; i++) {
			Stack<Node> stk = new Stack<Node>();
			Vector<Character> each = this.exp.elementAt(i);
			int each_len = each.size();
			for(int j = 0; j<each_len; j++) {
				Character ch = each.elementAt(j);
				if(ch == '(')
					continue;
				else if(ch == ')') {
					Node t2 = stk.pop();
					Node t = stk.pop();
					Node t1 = stk.pop();
					t.left = t1;
					t.right = t2;
					stk.push(t);
				}else {
					Node t = new Node(ch);
					stk.push(t);
				}
			}
			this.tree.add(stk.pop());
		}
	}
	
	private void evaluateExpression() {
		int tree_size = this.tree.size();
		for(int i = 0; i<tree_size; i++) {
			Node each = this.tree.elementAt(i);
			double eve_result = pstodr(each);
			this.result.addElement(eve_result);
		}
	}
	
	private double pstodr(Node T) {
		double result = 0;

		if(T.has_child(T)) {
			double x = pstodr(T.left);
			double y = pstodr(T.right);
			switch(T.ch) {
			case '+':
				result = x+y;
				return result;
			case '-':
				result = x-y;
				return result;
			case '*':
				result = x*y;
				return result;
			case '/':
				result = x/y;
				return result;
			case '^':
				result = java.lang.Math.pow(x,y);
				return result;
			case '%':
				result = x%y;
				return result;
			}
		}else {
			result = (double)(T.ch-'0');
		}
		return result;
	}
	
	private void write_eval() throws Exception{
		int exp_size = this.exp.size();
		String out_path = "src/output_prob2.txt";
		Writer file = new FileWriter(out_path);
		try (BufferedWriter bw = new BufferedWriter(file)) {
			for(int i = 0; i<exp_size; i++) {
				Vector<Character> each = this.exp.elementAt(i); 
				int each_size = each.size();
				for(int j = 0; j<each_size; j++) {
					bw.write(each.elementAt(j));
					bw.write(' ');
				}
				String equal_mark = "     =     ";
				bw.write(equal_mark);
				bw.write(this.result.elementAt(i).toString());
				bw.write('\n');
			}
			System.out.println("Done");
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void solve() throws Exception{
		read_exp();
		bild_expression();
		evaluateExpression();
		write_eval();
	}
}

