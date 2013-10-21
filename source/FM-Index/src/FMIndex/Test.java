package FMIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("test.txt"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringWrapper input = new StringWrapper();
		String text = null;
		try {
			text = new String(Files.readAllBytes(Paths.get("D:\\FM-Index\\source\\test.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(text.length());
		input.string = text;
		StringWrapper output = new StringWrapper();
		long startTime = System.currentTimeMillis();
		BWT.performBTW(input, output);
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println(elapsedTime);
		return;
	}

}
