package edu.pattern.management.reader;

import edu.pattern.management.exception.TaskException;

import java.util.List;

/**
 * TaskFileReader interface. It declares a method for parsing Task parameters.
 */
public interface TaskFileReader {
    List<String> parseTaskParameters(String fileName) throws TaskException;
}
