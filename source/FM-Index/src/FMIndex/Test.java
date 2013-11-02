package FMIndex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;

public class Test {

	public static void main(String[] args) {
		
		BufferedReader reader = null;
		
		BitSet bs = new BitSet(100000);
		
		try {
			reader = new BufferedReader(new FileReader("test.txt"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringWrapper input = new StringWrapper();
		String text = null;
		try {
			text = new String(Files.readAllBytes(Paths.get("D:\\FM-Index\\source\\test1.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(text.length());
		input.string = text;
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
		
		StringWrapper sw = new StringWrapper();
		sw.string = "$jajajaj sam ja i samo ja";
		StringWrapper sw1 = new StringWrapper();
		sw1.string = "AC";
		
		FMIndex fmindex = new FMIndex(input);
		int count = fmindex.Count(sw1);
		System.out.println(count);
		return;
	}

}
