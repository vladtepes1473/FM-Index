package FMIndex;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) throws IOException {

		//Invalid number of parameters, print usage
		if(args.length!=2&&args.length!=1&&args.length!=4){
			System.out.println("fmindex filetype filepath");
			return;
		}
		
		Parser parser = null;
		String path;
		
		if(args.length==1){
			parser = new PlainTextParser();
			path = args[0];
		}
		else{
			if(args[0].equals("-p")){
				parser = new PlainTextParser();
			}
			else if(args[0].equals("-f")){
				parser = new FASTAParser();
			}
			else{
				System.out.println("Invalid input parameter!");
				return;
			}
			path = args[1];
		}
		
		if(args.length==4){
			if(args[2].equals("-o")){
				System.setOut(new PrintStream(new File(args[3])));
			}
			else{
				System.out.println("Invalid input parameter!");
			}
		}
		
		StringWrapper input;
		try{
		input = parser.Parse(path);
		}
		catch(Exception e){
			System.out.println("An error occurred: "+e.getMessage());
			return;
		}
		
		System.out.println("Building index...");
		FMIndex fmindex;
		try{
		fmindex = new FMIndex(input);
		}
		catch(Exception e){
			System.out.println("An exception during the index construction occurred!");
			return;
		}
		input = null;
		System.gc();
		System.out.println("Index built...");
		
		System.out.println("Enter a pattern to search for");
		Scanner sc = new Scanner(System.in);
		
		while(true){
			String pattern = sc.nextLine();
			if(pattern.length()<1)
				continue;
			StringWrapper p = new StringWrapper(pattern.getBytes("US-ASCII"));
			int count = fmindex.Count(p);
			System.out.println("Pattern "+pattern+" count: "+count);
		}
		

	}

}
