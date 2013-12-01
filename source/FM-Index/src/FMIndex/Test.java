package FMIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.Scanner;

public class Test {
	  private static final long MEGABYTE = 1024L * 1024L;

	  public static long bytesToMegabytes(long bytes) {
	    return bytes / MEGABYTE;
	  }
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		sc.next();
		StringWrapper input = new StringWrapper();
		String text = null;
		try {
			text = new String(Files.readAllBytes(Paths.get("D:\\FM-Index\\source\\dna.txt")));
			//text = new String(Files.readAllBytes(Paths.get("/Users/ljama/FM-Index/source/FM-Index/test.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(text.length());
		input.string = text;
		text = null;
		System.gc();
		sc.next();
		/*StringWrapper output = new StringWrapper();
		long startTime = System.currentTimeMillis();
		ConcreteAlphabet a = new ConcreteAlphabet(input); 
		Character c[] = a.getAllCharacters();
		PrefixSumTable sum = new DictionaryPrefixSumTable(a);
		for(Character ca : c){
			System.out.println(ca + " " + sum.getCharacterPrefixSum(ca));
		}
		BWT.performBTW(input, output);
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println(elapsedTime);*/

		//StringWrapper sw = new StringWrapper();
		//sw.string = "$matootootooteaimatortu";
		StringWrapper sw1 = new StringWrapper();
		sw1.string = "TAT";
		long startTime = System.currentTimeMillis();
		FMIndex fmindex = new FMIndex(input);
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("Time:" + elapsedTime);
	    startTime = System.currentTimeMillis();
		int count = fmindex.Count(sw1);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("Time:" + elapsedTime);
		System.out.println("Count: "+count);
		input = null;
		
		System.gc();
		
	    Runtime runtime = Runtime.getRuntime();
	    // Run the garbage collector
	    runtime.gc();
	    // Calculate the used memory
	    runtime.freeMemory();
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("Used memory is bytes: " + runtime.totalMemory());
	    System.out.println("Used memory is megabytes: "
	        + bytesToMegabytes(runtime.totalMemory()));
		sc.next();
		return;
	}

}
