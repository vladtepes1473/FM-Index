//package testcases;
//import java.io.UnsupportedEncodingException;
//
//import junit.framework.Assert;
//import FMIndex.*;
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//public class BWTTests {
//
//	@Test
//	public void BWTTest1() {
//		String input = "#abracadabra";
//		String expectedOutput = "ard#rcaaaabb";
//		
//		StringWrapper inputString = new StringWrapper();
//		try {
//			inputString.string = input.getBytes("US-ASCII");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		StringWrapper outputString = new StringWrapper();
//		BWT.performBWT(inputString, outputString);
//		
//		
//		Assert.assertEquals(outputString.string, expectedOutput);
//		
//	}
//	@Test
//	public void BWTTest2() {
//		String input = "#mississippi";
//		String expectedOutput = "ipssm#pissii";
//		
//		StringWrapper inputString = new StringWrapper();
//		try {
//			inputString.string = input.getBytes("US-ASCII");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		StringWrapper outputString = new StringWrapper();
//		BWT.performBWT(inputString, outputString);
//		
//		
//		Assert.assertEquals(outputString.string, expectedOutput);
//		
//	}
//	
//	@Test
//	public void BWTTest3() {
//		String input = "#ananas";
//		String expectedOutput = "s#nnaaa";
//		
//		StringWrapper inputString = new StringWrapper();
//		try {
//			inputString.string = input.getBytes("US-ASCII");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		StringWrapper outputString = new StringWrapper();
//		BWT.performBWT(inputString, outputString);
//		
//		
//		Assert.assertEquals(outputString.string, expectedOutput);
//		
//	}
//}
