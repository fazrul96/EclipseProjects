import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Number1 {

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String lines = br.readLine();
		int a[] = new int[0];
		String[] strs = lines.trim().split("\\s+");
		
		for(int i=0;i<strs.length;i++){
			a[i]=Integer.parseInt(strs[i]);
			System.out.println(a[1]);
		}
		
		//Read in a single line containing 1…N numbers k[i] separated by spaces. You may assume (1 <= N <= 100) and (0 <= k[i] <= 1000)

		//- Lowest value
		//- Highest value
		//- Average value
		//- Difference between first and last values

		//Example:
		//Input: 104.45 108.89 99.56 102.3
		//Output: Low 99.56, High 108.89, Average 103.8, Diff -2.15

	}

}
