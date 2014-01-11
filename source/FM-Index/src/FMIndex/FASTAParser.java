package FMIndex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FASTAParser implements Parser {

	private static final int MAX_MULTIFASTA_COUNT = 30;
	
	@Override
	public StringWrapper Parse(String path) throws IOException {
		File file = new File(path);
		// Get the size of the file
        long length = file.length();
    	char newLineChar = System.lineSeparator().charAt(0);

        /* You cannot create an array using a long type.
         * It needs to be an int type.
         * Before converting to an int type, check
      	 * to ensure that file is not larger than Integer.MAX_VALUE.
      	 */
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }
        
        int intLength = (int) length + MAX_MULTIFASTA_COUNT;

        /* Create the byte array to hold the data and add the 
         * extra space for special end character which is lexicographically smallest
         */
        byte[] bytes = new byte[(int)intLength + 1];

        // Read in the bytes
        int offset = 0;

        InputStream inputStream = new FileInputStream(file);
        try {
        	boolean isMultiFasta = false;
            while (offset < intLength && (inputStream.read(bytes, offset, 1)) >= 0) {
            	if(bytes[offset] < 4 || bytes[offset] > 127)
            		throw new IOException("Invalid character: " + bytes[offset] + "in file " + file.getName());
            	if(bytes[offset] == newLineChar)
            		offset -= 1;
            	offset += 1;
            	if(bytes[offset - 1] == (byte)'>'){
            		offset -= 1;
            		byte[] controlLineChar = new byte[1];
            		do{
            			inputStream.read(controlLineChar, 0, 1);
            		}
            		while(controlLineChar[0] != (byte)newLineChar);
            		if(isMultiFasta){
            			//bytes[offset] = (byte) 3;
            			//offset += 1;
            			bytes[offset] = (byte) '_';
            			offset +=1;
            		}
            		
            		isMultiFasta = true;
            	}
                
            }
        } finally {
            inputStream.close();
        }

        //add special end character
        bytes[intLength] = (byte) 3;
        
        ArrayList<Byte> nonEmptyMembers = new ArrayList<Byte>();
        for(int i = 0; i <= intLength; i++){
        	if(bytes[i] != 0)
        		nonEmptyMembers.add(bytes[i]);
        }
        
        byte[] finalBytes = new byte[nonEmptyMembers.size()];
        for(int i = 0; i < nonEmptyMembers.size(); i++)
        	finalBytes[i] = (byte) nonEmptyMembers.get(i);
        
        StringWrapper readData = new StringWrapper();
        readData.string = finalBytes;
        return readData;
	}

}
