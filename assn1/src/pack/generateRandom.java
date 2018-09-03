package pack;

public class generateRandom {
	public static void main(String[] argv) {
		randNo two = new randNo(2);
		two.generate();
		randNo three = new randNo(3);
		three.generate();
		randNo four = new randNo(4);
		four.generate();
		randNo five = new randNo(5);
		five.generate();
		randNo six = new randNo(6);
		six.generate();
		
		System.out.println("---------------------------------------");
		System.out.println("N    10^2 10^3 10^4 10^5 10^6");
		System.out.println("T(N)"  + "  " + two.getGentime() + "  " + three.getGentime()  + "  "+ four.getGentime()  + "  "+ five.getGentime()  + "  "+ six.getGentime() );
		System.out.println("---------------------------------------");
		
		System.out.println("Before Sorting: N=100");
		two.print();
		two.sort();
		three.sort();
		four.sort();
		five.sort();
		six.sort();
		
		System.out.println("\n" + "After Sorting: N=100");
		two.print();
		
		System.out.println("---------------------------------------");
		System.out.println("N   10^2 10^3 10^4 10^5 10^6");
		System.out.println("T(N)"  + " " + two.getSortTime() + "  " + three.getSortTime()  + "  "+ four.getSortTime()  + " "+ five.getSortTime()  + "  "+ six.getSortTime() );
		System.out.println("---------------------------------------");
		
		
		
		return;
	}	
}
