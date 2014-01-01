package FMIndex;


import java.util.BitSet;


public class SaisBWT {

	//we need a unified interface for working with integer and byte arrays
	//because in the second iteration we will call the algorithm with the
	//suffix array

	/**
	 * 
	 * Array that represents an array with displacement, which means that the element
	 * with the index n is now treated as the element with the index 0
	 *
	 */
	private static interface DisplacedArray{
		/**
		 * Gets the element from the specified index
		 * @param index index of the element to be fetched
		 * @return element on the specified index
		 */
		int get(int index);

		/**
		 * Sets the element at the specified index to the specified value
		 * @param index index of the element to be set
		 * @param value the new value of the specified element
		 */
		void set(int index, int value);


		/**
		 * Updates the element at the specified index
		 * @param index index of the element to be set
		 * @param value value by which the element should be incremented
		 */
		void update(int index, int value);

		/**
		 * Updates the element at the specified index and returns the new value
		 * @param index index of the element to be set
		 * @param value value by which the element should be incremented
		 * @return new value of the element
		 */
		int updateAndReturnNew(int index, int value);

		/**
		 * Updates the element at the specified index and returns the old value
		 * @param index index of the element to be set
		 * @param value value by which the element should be incremented
		 * @return old value of the element
		 */
		int updateAndReturnOld(int index, int value);

	}

	//Concrete implementation for the displaced array of bytes
	private static class ByteDisplacedArray implements DisplacedArray{

		private byte[] array;
		private int displacement = 0;

		public ByteDisplacedArray(byte[] inputArray, int position) {
			array = inputArray;
			displacement = position;
		}

		@Override
		public int get(int i) {
			return array[i+displacement];
		}

		@Override
		public void set(int i, int value) {
			array[i+displacement] = (byte)value;
		}


		@Override
		public void update(int index, int value) {
			array[index+displacement]+=(byte)value;
		}

		@Override
		public int updateAndReturnNew(int i, int value) {
			return array[i+displacement]+=(byte)value;
		}

		@Override
		public int updateAndReturnOld(int i, int value) {
			int old = array[i + displacement];
			array[i + displacement] += value;
			return old;
		}


	}

	//Concrete implementation for the displaced array of integers
	private static class IntDisplacedArray implements DisplacedArray{

		private int[] array;
		private int displacement = 0;

		public IntDisplacedArray(int[] inputArray, int position) {
			array = inputArray;
			displacement = position;

		}

		@Override
		public int get(int i) {
			return array[i + displacement];
		}

		@Override
		public void set(int i, int value) {
			array[i + displacement] = value;
		}

		@Override
		public void update(int index, int value) {
			array[index + displacement] += value;
		}

		@Override
		public int updateAndReturnNew(int i, int value) {
			return array[i + displacement] += value;
		}

		@Override
		public int updateAndReturnOld(int i, int value) {
			int old = array[i + displacement];
			array[i + displacement] += value;
			return old;
		}



	}

	/**
	 * 
	 * Performs the Burrows-Wheeler transformation
	 * @param inputString the array on which the BWT should be performed
	 * @param outputString the array where the result should be stored
	 */
	public static void PerformBWT(StringWrapper inputString, StringWrapper outputString){
		//first compute the suffix array for the input
		int SA[] = new int[inputString.length()];
		SAIS(new ByteDisplacedArray(inputString.string, 0), SA, inputString.length(), 128);

		outputString.string = new byte[inputString.length()];

		//when we have the suffix array we can easily compute the BWT because of the following
		//BWT[i] = SA[input[i]] 
		for(int i=0; i<SA.length;i++){
			outputString.string[i] = inputString.string[SA[i]-1>=0?SA[i]-1:inputString.length()-1];
		}
	}


	/**
	 * Finds the start or end indexes of all buckets
	 * @param array input array
	 * @param buckets bucket array
	 * @param k bucket size
	 * @param end find the start or the end of buckets
	 */
	private static void GetBuckets(DisplacedArray array, DisplacedArray buckets, int n,  int k, boolean end){
		int sum = 0;

		//reset the bucketArray
		for(int i = 0; i<k;i++){
			buckets.set(i, 0);
		}
		//compute the size of each bucket
		for(int i =0; i<n;i++){
			buckets.update(array.get(i) ,1);
		}

		//finds the end of each bucket
		if(end==true){
			for(int i=0; i<k; i++){
				sum+=buckets.get(i);
				buckets.set(i, sum);
			}
		}
		//finds the beginning of each bucket 
		else{
			for(int i=0; i<k; i++){
				sum+=buckets.get(i);
				buckets.set(i, sum-buckets.get(i));
			}
		}
	}


	/**
	 * Constructs the suffix array
	 * @param array the input string array
	 * @param SA the suffix array
	 * @param length length of the array to be computed
	 * @param k size of the alphabet
	 */
	private static void SAIS(DisplacedArray array, int[] SA, int length, int k){
		IntDisplacedArray buckets;
		buckets = new IntDisplacedArray(new int[k], 0);

		//array of bits which will indicate which characters are of l and which of s type
		BitSet bs = new BitSet(length);

		/*
		 * Compute the endings of all buckets and initialize the suffix array to 0
		 */
		GetBuckets(array, buckets,length, k,  true);
		for(int i = 0; i<length; i++){
			SA[i] = 0;
		}

		//represents the character type
		boolean sTypeChar=false;
		int rigthCharacter=array.get(length-1);
		int leftCharacter = 0;
		//iterate through the array and finds all LMS characters, as well classifies
		//all the characters as either s or l type
		for(int i=length-2; i>=0; i--){
			leftCharacter = array.get(i);
			//if our left character is smaller than the right character it is an s-type character
			//so we set the flag to true
			if(leftCharacter<rigthCharacter){
				sTypeChar = true;
			}

			//if we found the s-type character we find the leftmost such character
			//and store it's index
			else if(sTypeChar&&leftCharacter>rigthCharacter){
				SA[buckets.updateAndReturnNew(rigthCharacter, -1)] = i+1;
				sTypeChar = false;

			}

			//set the bit to true if it is an l type character
			if(!sTypeChar)
				bs.set(i,true);
			rigthCharacter = leftCharacter;
		}

		//perform the suffix array induction
		InduceSufixArray(array, SA, buckets, length, k, bs);
		buckets=null;



		int nl=0;
		int suffixArrayElement = 0;
		for(int i=0; i<length; i++){

			//we find the LMS characters, and put their indexes into the beginning suffix array
			suffixArrayElement = SA[i];
			if(suffixArrayElement>0&&(suffixArrayElement+1<length)&&!bs.get(suffixArrayElement)&&bs.get(suffixArrayElement-1)){
				SA[nl] = SA[i];
				nl++;
			}
		}


		//initialize the name array buffer
		//it is enough to initialize only the first n/2 items after nl
		//because the  next part of the algorithm never updates elements after
		//the nl+n/2 index
		int boundary =nl+ length/2;
		for(int i = nl; i<boundary; i++){
			SA[i] = 0;
		}

		//precompute the lengths of all substrings
		int position = length;
		for(int i=length-3; i>=0; i--){
			//when you find a LMS character, calculate its length
			//and store it into the SA on the exact same positions where
			//we will later store their lexicographical names 
			if(bs.get(i)&&!bs.get(i+1)){
				SA[nl+((i+1)/2)] = position-i-1;
				position=i+1;
			}
		}

		//find the lexicographic names of all substrings
		boolean diff;
		int name = 0;
		int substringIndex = length;
		int substringLength=0;
		int len = 0;
		int displacement = 0;
		int pos=0;
		int index =0;
		//iterate through the substrings
		for(int i = 0; i<nl; i++){
			//get the index of the LMS
			index = SA[i];
			//calculate the index of where the length for this LMS substring is stored
			//and retrieve it from the array
			displacement = index/2;
			len = SA[nl+displacement];
			diff = true;
			//if the substrings are of equal length, compare each character to check if they are equal
			if(len==substringLength){
				pos= 0;
				while((pos<len)&&(array.get(pos+index)==array.get(pos+substringIndex))){
					pos++;
				}
				if(pos==len){
					diff = false;
				}
			}
			//if substrings are of different lengths or different, give the substring a new lexicographical name
			if(diff){
				name++;
				substringIndex = index;
				substringLength = len;
			}

			//assign the name to the substring
			SA[nl+displacement]=name;
		}


		//if more LMS substrings are equal we have a problem because we can't order them,
		//that's why we need to recurse the problem and deduce the order of those substrings
		if(nl>name){
			//create a new array which will contain all the lexicographical names
			IntDisplacedArray reducedArray = new IntDisplacedArray(SA, length-nl);
			index = length  - 1;
			//move the lexicographical names into the end of the array
			for(int i = nl + length/2 -1; nl<=i;i--){
				if(SA[i]!=0){
					SA[index] = SA[i]-1;
					index--;
				}
			}

			//recurse the problem
			SAIS(reducedArray, SA, nl, name);
			reducedArray = null;

			//now that we have the order of the substrings, we locate the LMS substring of the original array again
			index = nl*2-1;
			for(int i = length-3; i>=0; i--){
				if(bs.get(i)&&!bs.get(i+1)){
					SA[index] = i+1;
					index--;
				}
			}

			//by using the computed suffix array for the LMS substrings which were equal
			//we can now correctly order all the LMS substrings
			for(int i = 0; i<nl;i++){
				SA[i] = SA[SA[i]+nl];
			}

		}


		buckets =  new IntDisplacedArray(new int[k], 0);

		GetBuckets(array, buckets, length, k,  true);
		//initialize the suffix array to 0 from nl until the end
		for(int i =nl; i<length; i++){
			SA[i]=0;
		}
		//print(SA);

		//put all the LMS indexes into the right positions (because they are currently located in the 
		//first nl elements of the SA array)
		for(int i = nl-1;i>=0;i--){
			index = SA[i];
			SA[i] = 0;
			SA[buckets.updateAndReturnNew(array.get(index), -1)] = index;
		}

		//now that we have finally ordered the LMS-substrings we can perform a final induction which will
		//compute the suffix array
		InduceSufixArray(array, SA,  buckets, length, k,bs);
		buckets=null;
		bs = null;
	}



	/**
	 * Performs the suffix array induction
	 * @param input the input array
	 * @param SA the suffix array
	 * @param buckets the bucket array
	 * @param length length of the input string
	 * @param alphabetSize Number of characters in alphabet
	 * @param bs bitset which represents the character types
	 */
	private static void InduceSufixArray(DisplacedArray input, int[] SA, DisplacedArray buckets, int length, int alphabetSize, BitSet bs){
		int suffixArrayElement = 0;
		int index = 0;
		int bucketElement;
		int character;

		//Get the starts of all buckets
		GetBuckets(input,buckets,length,alphabetSize,false);

		//put the last character in the array in its place
		suffixArrayElement = length-1;
		character = input.get(suffixArrayElement);
		index = buckets.get(character);
		SA[index] = suffixArrayElement;


		//perform the forward pass
		//iterate through the whole input array and induce the 
		//suffix array of l type characters (SAl)
		for(int i = 0; i<length; i++){
			index = SA[i]-1;
			//check if the index is bigger than zero (we do not perform induction on zero elements)
			//and if it is an l-type character
			if(index>=0 && bs.get(index)){
				character = input.get(index);
				//move the bucket pointer to the right
				bucketElement = buckets.updateAndReturnOld(character, 1);
				//update the suffix array with the new element
				SA[bucketElement] = index;

			}
		}

		//get the endings of all buckets
		GetBuckets(input,buckets,length,alphabetSize,true);

		//perform the backward pass
		//iterate through the whole input array and induce the 
		//suffix array of l type characters (SAl)
		for(int i = length-1; i>=0;i--){
			index = SA[i]-1;
			//check if the index is bigger than zero (we do not perform induction on zero elements)
			//and if it is an s-type character
			if(index>=0 && !bs.get(index)){
				character = input.get(index);
				//move the bucket pointer to the left
				bucketElement = buckets.updateAndReturnNew(character, -1);
				SA[bucketElement] = index;
			}
		}

	}

}
