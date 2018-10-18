package comp3160.hw5;

import comp3160.util.StringRedBlackTree;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SpellChecker
{
	
/*	public static void main (String [] args)
	{
		FileHandler fh = new FileHandler("wordEn.txt");
		StringRedBlackTree wordsTree = new StringRedBlackTree();
		FileHandler in = new FileHandler("input.txt");
		while(fh.readLine()!=null){
			if (fh.readLine()!=null )
				wordsTree.insert(fh.readLine());
		}
		
		String nextLine = "";
		while (in.readLine()!= null){
			if (in.readLine()!=null)
				nextLine+= in.readLine() +" ";
		}
		
		String [] textArray = nextLine.split(" ");
		
		for (int i =0 ; i< textArray.length; i++){
			System.out.println(textArray[i]);
		}
		
	}*/
	public static void main(String[] args)
	{
		Scanner s = null;
		StringRedBlackTree wordsTree = new StringRedBlackTree();
        try {
            s = new Scanner(new File("wordEn.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR! File is not in place!");
        }
        while (s.hasNext()) {   
            String nextLine = s.nextLine();
            wordsTree.insert(nextLine);
        }
		try{
			s = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException ae){
			System.out.println("ERROR! File is not in place!");
		}
		int ski =0;
		while (s.hasNext()) {
			String textLine = s.nextLine();
			String [] textArray = textLine.split(" ");
			for (int i= 0; i<textArray.length; i++){
				char [] chArray = textArray[i].toCharArray();
				ski = Math.max(ski, chArray.length);
			}
		}
		try{
			s = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException ae){
			System.out.println("ERROR! File is not in place!");
		}
		while (s.hasNext()){
			String suggestion ="";
			String line = s.nextLine();
			String [] wordsArray = line.split(" ");
			System.out.println(line);
			for (int i =0; i<wordsArray.length;i++){
				char [] charArray = wordsArray[i].toCharArray();
				int skip = ski - charArray.length;
				String sk ="";
				for (int j =0; j < skip;j++)
					sk+=" ";
				if (!wordsTree.contains(wordsArray[i]))
					System.out.println(wordsArray[i] +  sk + "  " + wordsTree.findClosest(wordsArray[i]));
				suggestion += wordsTree.findClosest(wordsArray[i])+ " ";
			}
			System.out.println(suggestion + "\n");
		}
	}
}
