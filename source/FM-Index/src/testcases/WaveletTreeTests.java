package testcases;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import FMIndex.ConcreteAlphabet;
import FMIndex.StringWrapper;
import FMIndex.WaveletTree;

public class WaveletTreeTests {

	@Test
	public void testOccurence() {
		StringWrapper string = new StringWrapper();
		string.string = "abracadabra";
		WaveletTree tree = new WaveletTree(string, new ConcreteAlphabet(string));
		int occ = tree.getCharacterOcurrance('b', 9);
		 Assert.assertEquals(2 , occ);
	}
	@Test
	public void testOccurence2() {
		StringWrapper string = new StringWrapper();
		string.string = "yabbadabbadoo";
		WaveletTree tree = new WaveletTree(string, new ConcreteAlphabet(string));
		int occ = tree.getCharacterOcurrance('b', 9);
		 Assert.assertEquals(4 , occ);
	}
}
