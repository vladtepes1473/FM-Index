package FMIndex;

import java.util.*;

/**
 * Class for performing the BWT transformation
 */
public class BWT {


	private static class DisplacedArray{

		//the input string array
		private byte[] array;
		//array of the pointers to the string
		private int[] pointers;
		//start index of the array
		private int start = 0;
		//the length of the reduced array
		public final int reducedLength;

		/**
		 * 
		 * @param inputArray Array containing the input string
		 * @param pointers array of the pointers to the string
		 * @param position the starting position of the array
		 * @param reducedLength length of the array
		 */
		public DisplacedArray(byte[] inputArray, int[] pointers, int position, int reducedLength) {
			array = inputArray;
			this.pointers = pointers;
			start = position;
			this.reducedLength = reducedLength;
		}


		/**
		 * Returns the real length of the input string array
		 * @return length of the original array 
		 */
		public int RealLength(){
			return array.length;
		}

		/**
		 * Gets the element from the array displaced a certain number of characters to the right
		 * @param index Index of the character to be returned
		 * @param displacement displacement of the character
		 * @return
		 */
		public byte getElementWithDisplacement(int index, int displacement){
			//we do not query the byte array directly, we query the pointers array first,
			//and then use use that index to query the the byte array
			int i = (pointers[index+start]+displacement)%array.length;
			return array[i];
		}

		/**
		 * Return a subarray of the current array
		 * @param startPosition Start position of the subarray
		 * @param endPosition length of the subarray
		 * @return the new subarray
		 */
		public DisplacedArray newArray(int startPosition, int endPosition){
			return new DisplacedArray(array, pointers, start+startPosition, endPosition);
		}


		/**
		 * Swaps two elements in the pointer array
		 * @param firstIndex Index of the first pointer
		 * @param secondIndex Index of the second pointer
		 */
		public void swapElements(int firstIndex, int secondIndex){
			int temp = pointers[firstIndex+start];
			pointers[firstIndex+start] = pointers[secondIndex+start];
			pointers[secondIndex+start] = temp;
		}

	}

	/*
	 * Static fields used by the algorithm
	 */
	private static Random rand = new Random();


	/**
	 * Performs the BWT on the given string
	 * @param inputString the string wrapper on which the BWT should be performed
	 * @param outputString the string wrapper where the transformed string will be saved
	 */
	public static void performBWT(StringWrapper inputString, StringWrapper outputString){
		int stringLength = inputString.length();

		// We initialize the array of integer pointers on the string 
		int[] pointers = new int[stringLength];
		for(int i = 0; i < stringLength; i++){
			pointers[i] = i;
		}

		DisplacedArray da = new DisplacedArray(inputString.string, pointers, 0, stringLength);

		//Perform the multikey quicksort
		sort(da, 0);
		outputString.string = new byte[stringLength];

		//We calculate the outputstring by using the pointer array to determine 
		//the positions of characters
		for(int i = 0; i < stringLength; i++){
			int index = (pointers[i]+stringLength-1)%stringLength;
			outputString.string[i]=inputString.string[index];
		}

		inputString= null;
		pointers = null;

		System.gc();
	}


	/**
	 * Sorts the BWT strings using the Multikey Quicksort
	 * @param pstart Start index of the array which will be sorted
	 * @param pLength Length of the array which will be sorted
	 * @param displacement Index of the string by which we are currently sorting all the BWT strings
	 */
	private static void sort(DisplacedArray da, int displacement){
		int leftPivotsPointer;
		int startPointer;
		int endPointer;
		int rightPivotsPointer;

		int pivotIndex;
		int length;
		byte pivot;
		byte currentCharacter;

		//if the length of the array is less than 2, the algorithm is completed			
		if(da.reducedLength<=1)
			return;

		//we randomly determine the index of the pivot
		pivotIndex = rand.nextInt(da.reducedLength);
		da.swapElements(0, pivotIndex);

		pivot =da.getElementWithDisplacement(0, displacement);

		//we initialize the indexes
		leftPivotsPointer = startPointer =  1;
		endPointer = rightPivotsPointer =  da.reducedLength - 1;


		
		while(startPointer<=endPointer){
			currentCharacter = da.getElementWithDisplacement(startPointer, displacement);
			//iterate through the array until you reach a character larger than the pivot
			while(currentCharacter <= pivot){

				//if the current character is equal to the pivot we swap it to the beginning of the array
				if(currentCharacter == pivot){
					da.swapElements(leftPivotsPointer, startPointer);
					leftPivotsPointer++;
				}

				//we increment the index
				startPointer++;
				//if the lower index is greater than the upper index we are finished, because we checked
				//all of the characters
				if(startPointer>endPointer)
					break;
				currentCharacter = da.getElementWithDisplacement(startPointer, displacement);
			}


			currentCharacter = da.getElementWithDisplacement(endPointer, displacement);
			//iterate through the array until you reach a character smaller than the pivot
			while(currentCharacter >= pivot){

				//if the current character is equal to the pivot we swap it to the end of the array
				if(currentCharacter == pivot){
					da.swapElements(endPointer,rightPivotsPointer);
					rightPivotsPointer--;
				}
				//we decrement the index
				endPointer--;
				//if the lower index is greater than the upper index we are finished, because we checked
				//all of the characters
				if(startPointer>endPointer)
					break;
				currentCharacter = da.getElementWithDisplacement(endPointer, displacement);

			}

			//if the lower index is still smaller or equal to the upper index we still haven't
			//passed through the whole array, so we swap the elements to which the indexes are pointing
			//(because currently the upper element is pointing to an element which is smaller than the pivot,
			//and vice versa) increment the indexes and continue the loop
			if(startPointer<=endPointer){
				da.swapElements(startPointer, endPointer);
				startPointer++;
				endPointer--;
			}

		}


		//now we have all the pivots at the beginning and at the end of the array 
		//e.g. PPLLLLGGGGPPP (P - pivot, L - character smaller than the pivot, G - character greater than the pivot)
		//we now need to place the pivots into the center


		//compute the number of swaps that will be performed
		//the number of swaps will be either the number of the number of pivots in the beginning
		//of the array, or the number of characters smaller than the pivot
		//depending on which of the two is smaller
		if(startPointer>(startPointer-leftPivotsPointer))
			length = startPointer-leftPivotsPointer;
		else
			length = leftPivotsPointer;

		//we move all the pivots to the middle, and all characters smaller than the pivot to the beginning
		for(int i = 0; i< length; i++){
			da.swapElements(i, startPointer - length+i);
		}

		//compute the number of swaps that will be performed
		//the number of swaps will be either the number of the number of pivots in the end
		//of the array, or the number of characters greater than the pivot
		//depending on which of the two is smaller
		if((da.reducedLength-rightPivotsPointer-1)<(rightPivotsPointer-endPointer)){
			length = da.reducedLength-rightPivotsPointer-1;
		}
		else{
			length = rightPivotsPointer-endPointer;
		}

		//we move all the pivots to the middle, and all characters smaller than the pivot to the end
		for(int i = 0; i < length; i++){
			da.swapElements(startPointer+i, da.reducedLength-length+i);
		}


		//sort the lower part of the array containing characters smaller than the pivot
		//but with the same displacement
		sort(da.newArray(0, startPointer-leftPivotsPointer), displacement);

		//if the displacement is smaller than the length of the original array
		//we need to sort the middle elements
		if(displacement<(da.RealLength()-1)){
			//sort the middle characters (pivots), but increment the displacement, and sort them by those characters
			sort(da.newArray(startPointer-leftPivotsPointer, leftPivotsPointer+da.reducedLength-(rightPivotsPointer+1)), displacement+1);
		}

		//sort the upper part of the array containing characters larger than the pivot
		//but with the same displacement
		sort(da.newArray(da.reducedLength-(rightPivotsPointer-endPointer), rightPivotsPointer-endPointer), displacement);	
	}



}
