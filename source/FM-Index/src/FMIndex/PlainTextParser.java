package FMIndex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PlainTextParser implements Parser {

	@Override
	public StringWrapper Parse(String path) throws IOException {
		File file = new File(path);
		// Get the size of the file
        long length = file.length();

        /* You cannot create an array using a long type.
         * It needs to be an int type.
         * Before converting to an int type, check
      	 * to ensure that file is not larger than Integer.MAX_VALUE.
      	 */
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }
        
        int intLength = (int) length;

        /* Create the byte array to hold the data and add the 
         * extra space for special end character which is lexicographically smallest
         */
        byte[] bytes = new byte[(int)intLength + 1];

        // Read in the bytes
        int offset = 0;

        InputStream inputStream = new FileInputStream(file);
        try {
            while (offset < intLength && (inputStream.read(bytes, offset, 1)) >= 0) {
            	if(bytes[offset] < 4 || bytes[offset] > 127)
            		throw new IOException("Invalid character: " + bytes[offset] + "in file " + file.getName());
                offset += 1;
            }
        } finally {
            inputStream.close();
        }

        // Ensure all the bytes have been read in
        if (offset < intLength) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        //add special end character
        bytes[intLength] = (byte) 3;
        
        StringWrapper readData = new StringWrapper();
        readData.string = bytes;
        return readData;
	}
}
