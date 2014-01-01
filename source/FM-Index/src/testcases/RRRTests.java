package testcases;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;
import FMIndex.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RRRTests {

	@Test
	public void RRRTest1() {
		
		StringWrapper string = new StringWrapper();
		try {
			string.string = "abracadabra".getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char pivot = 'c';
		int position = 5;
		int expectedRank = 2;
		
		BitLookup bitContent = new RRR((byte)pivot, string);
		int rank = bitContent.rank(position);
		
		Assert.assertEquals(rank , expectedRank);
		
	}
	
	
	@Test
	public void RRRTest2() {
		
		StringWrapper string = new StringWrapper();
		try {
			string.string = "ananas".getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char pivot = 'n';
		int position = 0;
		int expectedRank = 0;
		
		BitLookup bitContent = new RRR((byte)pivot, string);
		int rank = bitContent.rank(position);
		
		Assert.assertEquals(rank , expectedRank);
		
	}
	
	
	
	@Test
	public void RRRTest3() {
		
		StringWrapper string = new StringWrapper();
		try {
			string.string = "rrrstructure".getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char pivot = 'c';
		int position = 11;
		int expectedRank = 12;
		
		BitLookup bitContent = new RRR((byte)pivot, string);
		int rank = bitContent.rank(position);
		
		Assert.assertEquals(rank , expectedRank);
		
	}
	
	
}
