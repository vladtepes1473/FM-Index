package FMIndex;

import java.io.IOException;

public interface Parser {
	/** Parses file with given path
	 * @param file path
	 * @return StringWrapper of parsed file
	 * @throws IOException
	 * @throws Exception 
	 */
	public StringWrapper Parse(String path) throws IOException, Exception;
}
