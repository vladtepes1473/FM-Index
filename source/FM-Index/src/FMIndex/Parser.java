package FMIndex;

import java.io.IOException;

public interface Parser {
	public StringWrapper Parse(String path) throws IOException;
}
