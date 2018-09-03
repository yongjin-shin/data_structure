package pack;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Prob1 {
	static int Prec(char ch){
        switch (ch){
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
        case '%':
            return 2;
      
        case '^':
            return 3;
        }
        return -1;
    }
	
	//member
	private Vector<Vector<Character> > infix = new Vector<Vector<Character> >();
	private Vector<Vector<Character> > postfix = new Vector<Vector<Character> >();
	private Vector<Double> result = new Vector<Double>();
	private String path = "";
	
	//method
	public Prob1(String path) {this.path = path;}
	
	private void read_infix() throws Exception{
		File file = new File(this.path);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			Vector<Character> in_tmp = new Vector<Character>();
			Vector<Character> post_tmp = new Vector<Character>();
			String input = sc.nextLine();
			int size = input.length();
			for(int i = 0; i<size; i++) {
				Character a_letter = input.charAt(i);
				in_tmp.add(a_letter);
			}
			infix.add(in_tmp);
			postfix.add(post_tmp);
		}
	}
	
	private void infix_to_postfix() {
		int infix_size = infix.size();
		for(int i = 0; i<infix_size; i++) {
			
			Vector<Character> tmp = infix.elementAt(i);tmp.add(')');
			int str_size = tmp.size();
			
			Stack<Character> stk = new Stack<Character>();
			stk.push('(');
			int j = 0;
			while(!stk.isEmpty()) {
				if(j>str_size)
					System.out.println("Something Wrong!");;
				
				Character ch = tmp.elementAt(j);
				if ('('== ch) {
					stk.push('(');
				
				}else if(ch==')') {
	                while (!stk.isEmpty() && stk.peek() != '(')
	                		this.postfix.get(i).addElement(stk.pop());
	                
	                if (!stk.isEmpty() && stk.peek() != '(')
	                    System.out.println("Invalid Expression"); // invalid expression                
	                else
	                    stk.pop();
	                
				}else if(Character.isLetterOrDigit(ch)) {
					this.postfix.get(i).addElement(ch);
				
				}else {
					while (!stk.isEmpty() && Prec(ch) <= Prec(stk.peek()))
						this.postfix.get(i).addElement(stk.pop());
	                stk.push(ch);
				}
				j += 1;
			}
		}		
	}
	
	private void evaluate_postfix() {
		int post_size = this.postfix.size();
		
		for(int i = 0; i<post_size; i++) {
			Vector<Character> tmp = this.postfix.elementAt(i);
			Stack<Double> stk = new Stack<Double>();
			
			tmp.addElement('\0');
			int j = 0;
			Character ch = tmp.elementAt(j++);
			while(ch != '\0') {
				if(Character.isLetterOrDigit(ch))
					stk.push((double) (ch-'0'));
				else {
					double x = stk.pop();
					double y = stk.pop();
					double result =  0;
					switch(ch) {
						case '+':
							result = y+x;
							break;
						case '-':
							result = y-x;
							break;
						case '*':
							result = y*x;
							break;
						case '/':
							result = y/x;
							break;
						case '^':
							result = java.lang.Math.pow(y,x);
							break;
						case '%':
							result = y%x;
							break;
					}
					stk.push(result);
				}
				ch = tmp.elementAt(j++);	
			}
			this.result.add(stk.pop());
		}		
	}
	
	private void write_eval() throws Exception{
		int post_size = this.postfix.size();
		String out_path = "src/output_prob1.txt";
		Writer file = new FileWriter(out_path);
		try (BufferedWriter bw = new BufferedWriter(file)) {
			for(int i = 0; i<post_size; i++) {
				Vector<Character> each = this.postfix.elementAt(i); 
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
		read_infix();
		infix_to_postfix();
		evaluate_postfix();
		write_eval();
	}
}

