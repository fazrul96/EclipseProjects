import  java.util.Scanner;
public class Number {
	public static void main(String args[]){
		int n;
		double max, min, average=0, difference=0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Value :");
		n = scan.nextInt();
		int a[] = new int[n];
		for(int i=0;i<n;i++){
			a[i] = scan.nextInt();
			average = (average+a[i]); 
		}
		max = a[0];
		min = max;
		difference =a[0]- a[n-1];
		average = average/n;
		for(int i=0;i<n;i++)
			{
				if(max<a[i]){
					max=a[i];
				}
				else
					min=a[i];
			}
		System.out.println("Low " + min + ", " + "High " + max  + ", " + "Average " + average + ", " + "Diff " + difference);
	}
}

//Read in a single line containing 1…N numbers k[i] separated by spaces. You may assume (1 <= N <= 100) and (0 <= k[i] <= 1000)

//- Lowest value
//- Highest value
//- Average value
//- Difference between first and last values

//Example:
//Input: 104.45 108.89 99.56 102.3
//Output: Low 99.56, High 108.89, Average 103.8, Diff -2.15