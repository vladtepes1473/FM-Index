package testcases;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

import FMIndex.ConcreteAlphabet;
import FMIndex.StringWrapper;
import FMIndex.WaveletTree;

public class WaveletTreeTests {

	@Test
	public void testOccurence() {
		StringWrapper string = new StringWrapper();
		try {
			string.string = "abracadabra".getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WaveletTree tree = new WaveletTree(string, new ConcreteAlphabet(string));
		int occ = tree.getCharacterOcurrance((byte)'b', 9);
		 Assert.assertEquals(2 , occ);
	}
	@Test
	public void testOccurence2() {
		StringWrapper string = new StringWrapper();
		try {
			string.string = "yabbadabbadoo".getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WaveletTree tree = new WaveletTree(string, new ConcreteAlphabet(string));
		int occ = tree.getCharacterOcurrance((byte)'b', 9);
		 Assert.assertEquals(4 , occ);
	}
}
