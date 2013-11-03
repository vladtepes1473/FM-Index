package testcases;
import junit.framework.Assert;
import FMIndex.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BWTTests {

	@Test
	public void BWTTest1() {
		String input = "#abracadabra";
		String expectedOutput = "ard#rcaaaabb";
		
		StringWrapper inputString = new StringWrapper();
		inputString.string = input;
		StringWrapper outputString = new StringWrapper();
		BWT.performBWT(inputString, outputString);
		
		
		Assert.assertEquals(outputString.string, expectedOutput);
		
	}
	@Test
	public void BWTTest2() {
		String input = "#mississippi";
		String expectedOutput = "ipssm#pissii";
		
		StringWrapper inputString = new StringWrapper();
		inputString.string = input;
		StringWrapper outputString = new StringWrapper();
		BWT.performBWT(inputString, outputString);
		
		
		Assert.assertEquals(outputString.string, expectedOutput);
		
	}
	
	@Test
	public void BWTTest3() {
		String input = "#ananas";
		String expectedOutput = "s#nnaaa";
		
		StringWrapper inputString = new StringWrapper();
		inputString.string = input;
		StringWrapper outputString = new StringWrapper();
		BWT.performBWT(inputString, outputString);
		
		
		Assert.assertEquals(outputString.string, expectedOutput);
		
	}
}
