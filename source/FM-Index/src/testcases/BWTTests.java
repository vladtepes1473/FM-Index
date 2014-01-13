package testcases;
import java.io.UnsupportedEncodingException;

import junit.framework.Assert;
import FMIndex.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BWTTests {

	@Test
	public void BWTTest1() throws UnsupportedEncodingException {
		String input = "abracadabra#";
		String expectedOutput = "ard#rcaaaabb";
		
		StringWrapper inputString = new StringWrapper();
		try {
			inputString.string = input.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringWrapper outputString = new StringWrapper();
		new SaisBWT().PerformBWT(inputString, outputString);
		
		
		byte[] o= expectedOutput.getBytes("US-ASCII");
		
		for(int i=0;i<outputString.length();i++){
			Assert.assertEquals(outputString.string[i], o[i]);
		}
		
		}
		
	
	@Test
	public void BWTTest2() throws UnsupportedEncodingException {
		String input = "mississippi#";
		String expectedOutput = "ipssm#pissii";
		
		StringWrapper inputString = new StringWrapper();
		try {
			inputString.string = input.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringWrapper outputString = new StringWrapper();
		new SaisBWT().PerformBWT(inputString, outputString);
		
		
		
		byte[] o= expectedOutput.getBytes("US-ASCII");
		
		for(int i=0;i<outputString.length();i++){
			Assert.assertEquals(outputString.string[i], o[i]);
		}
		
		
	}
	
	@Test
	public void BWTTest3() throws UnsupportedEncodingException {
		String input = "ananas#";
		String expectedOutput = "s#nnaaa";
		
		StringWrapper inputString = new StringWrapper();
		try {
			inputString.string = input.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringWrapper outputString = new StringWrapper();
		new SaisBWT().PerformBWT(inputString, outputString);
		
		
		
		byte[] o= expectedOutput.getBytes("US-ASCII");
		
		for(int i=0;i<outputString.length();i++){
			Assert.assertEquals(outputString.string[i], o[i]);
		}
		
		
	}
}
