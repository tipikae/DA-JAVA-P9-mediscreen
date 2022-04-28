/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Terms reader.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class TermReaderImpl implements ITermReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TermReaderImpl.class);
	
	private String dir = "terms";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> read() {
		LOGGER.debug("read: dir=" + dir);
		List<String> terms = new ArrayList<>();
		
		// get all files
		Set<String> files = new HashSet<>();
		try (Stream<Path> stream = Files.list(Paths.get(dir))) {
	        files = stream
	          .filter(file -> !Files.isDirectory(file))
	          .map(Path::getFileName)
	          .map(Path::toString)
	          .collect(Collectors.toSet());
	    } catch (IOException e) {
			LOGGER.debug("read: list files: IOException: " + e.getMessage());
		}
		
		// get all lines from each file
		files.forEach(file -> {
			try (Stream<String> lines = Files.lines(Paths.get(dir + "/" + file))) {
				lines.forEach(line -> terms.add(line));
			} catch (IOException e) {
				LOGGER.debug("read: lines: IOException: " + e.getMessage());
			}
		});
		
		return terms;
	}

}
