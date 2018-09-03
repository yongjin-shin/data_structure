package pack;

import java.util.*;
import java.lang.Math;

public class randNo {
	int size;
	int[] number;
	double gen_time;
	double sort_time;
	
	public randNo(int size){
		this.size = (int) Math.pow(10, size);
		number = new int[this.size];
		this.gen_time = 0;
		this.sort_time = 0;
	}
	
	public double getGentime() { return this.gen_time; }
	public double getSortTime() { return this.sort_time; }
	public void generate() {
		Random tmp = new Random();
		double start = System.currentTimeMillis();
		for(int j = 0; j<this.size; j++) {
			int num = tmp.nextInt();
			number[j] = num; 
		}
		double end = System.currentTimeMillis();
		this.gen_time = end - start;
	}
	
	public void sort() {
		int n = this.size;
		double start = System.currentTimeMillis();
		for(int k =1; k<n;k++) {
			int cur = number[k];
			int j = k;
			while(j>0 && number[j-1]>cur) {
				number[j] = number[j-1];
				j--;
			}
			number[j] = cur;
		}
		double end = System.currentTimeMillis();
		this.sort_time = end - start;
	}
	
	public void print() {
		for(int j = 0; j<this.size; j++) {
			System.out.println(this.number[j]);
		}
	}
	
}