package testcases;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import FMIndex.StringWrapper;
import FMIndex.WaveletTree;

public class WaveletTreeTests {

	@Test
	public void dummyOccurence() {
		StringWrapper string = new StringWrapper();
		string.string = "abracadabra";
		WaveletTree tree = new WaveletTree(string);
		int occ = tree.getCharacterOcurrance('a', 3);
		 Assert.assertEquals(occ , -1);
	}

}
