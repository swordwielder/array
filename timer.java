package test;
import java.util.*;


public class timer {
	
		public static void main (String [] args) {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter a number");
			String n = in.nextLine();
			int a = Integer.parseInt(n);
			System.out.println("ere");
			String mre = in.nextLine();
			if (a>10||a<0) {
				System.out.println("00:00:00");
			}else if (a==1) {
				System.out.println(in.nextLine());
			}else {
				String[]times = new String [a];
				int i=0;
				while(in.hasNext()) {
					times[i]=in.nextLine();
					i++;
				}
				System.out.println(best(times));
			}
		}
		
		public static String best(String [] a){
			String besttime=a[0];
			int[]fir = new int[3];
			int[]sec = new int[3];
			
			for (int i = 1; i< a.length ; i++) {
				String[]first = besttime.split(":");
				String[]second =a[i].split(":");
				for (int j=0; j<fir.length; j++) {
					fir[j]=Integer.parseInt(first[j]);
					sec[j]=Integer.parseInt(second[j]);
					
					if(fir[j] > sec[j]) {
						besttime = a[i];
						break;
					}
				}
			}
			return besttime;
		}
		
}
