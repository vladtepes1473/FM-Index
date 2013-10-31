package testcases;

import static org.junit.Assert.*;

import org.junit.Test;

import FMIndex.StringWrapper;
import FMIndex.WaveletTree;

public class WaveletTreeTests {

	@Test
	public void createNewTree() {
		StringWrapper string = new StringWrapper();
		string.string = "abracadabra";
		WaveletTree tree = new WaveletTree(string);
		
	}

}
