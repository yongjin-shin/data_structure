package pack;

public class test {
	public static void main(String args[]) throws Exception{
		String path1 = "src/input_prob1.txt";
		Prob1 p1 = new Prob1(path1);
		p1.solve();
		System.out.println("Prob1 Sovled in output_prob1.txt!");
		
		String path2 = "src/input_prob2.txt";
		Prob2 p2 = new Prob2(path2);
		p2.solve();
		System.out.println("Prob2 Sovled in output_prob2.txt!");
	  }
}
