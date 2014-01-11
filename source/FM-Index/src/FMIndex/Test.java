package FMIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.Scanner;

public class Test {
	
	 public static byte[] getBytesFromFile(File file) throws IOException {        
	        // Get the size of the file
	        long length = file.length();

	        // You cannot create an array using a long type.
	        // It needs to be an int type.
	        // Before converting to an int type, check
	        // to ensure that file is not larger than Integer.MAX_VALUE.
	        if (length > Integer.MAX_VALUE) {
	            // File is too large
	            throw new IOException("File is too large!");
	        }

	        // Create the byte array to hold the data
	        byte[] bytes = new byte[(int)length];

	        // Read in the bytes
	        int offset = 0;
	        int numRead = 0;

	        InputStream is = new FileInputStream(file);
	        try {
	            while (offset < bytes.length
	                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	                offset += numRead;
	            }
	        } finally {
	            is.close();
	        }

	        // Ensure all the bytes have been read in
	        if (offset < bytes.length) {
	            throw new IOException("Could not completely read file "+file.getName());
	        }
	        return bytes;
	    }
	
	
	  private static final long MEGABYTE = 1024L * 1024L;

	  public static long bytesToMegabytes(long bytes) {
	    return bytes / MEGABYTE;
	  }
	public static void main(String[] args) throws IOException {

		
		byte bb = 84;
		byte bv = 47;
		byte[] nn = new byte[]{bb,bv};
		//SaisBWT.Test();
		
		System.out.println((char)bb);
		Scanner sc = new Scanner(System.in);

		//sc.next();
		StringWrapper input = new StringWrapper();
		String text = null;
//		try {
//			text = new String(Files.readAllBytes(Paths.get("D:\\FM-Index\\source\\dnabig.txt")));
//			//text = new String(Files.readAllBytes(Paths.get("/Users/ljama/FM-Index/source/FM-Index/test.txt")));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//System.out.println(text.length());
		input.string = getBytesFromFile(new File("D:\\FM-Index\\source\\rand\\rand200.txt"));
		text = null;
		System.gc();
		//sc.next();
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
		sw1.string = "TATATATAT".getBytes("US-ASCII");
		//BWT.performBWT(input, );
		//System.out.println(new String(input.string));
		long startTime = System.currentTimeMillis();
		FMIndex fmindex = new FMIndex(input);
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("TOTAL Time:" + elapsedTime);
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
