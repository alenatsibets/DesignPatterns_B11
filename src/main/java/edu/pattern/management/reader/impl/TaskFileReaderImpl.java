package edu.pattern.management.reader.impl;

import edu.pattern.management.exception.TaskException;
import edu.pattern.management.factory.impl.GeneralTaskFactory;
import edu.pattern.management.reader.TaskFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Realisation of TaskFileReader interface is used for parsing Task parameters.
 */
public class TaskFileReaderImpl implements TaskFileReader {
    /**
     * Logger (configuration is declared in log4j2.xml file).
     */
    static Logger logger = LogManager.getLogger();
    /**
     * A sign that is used in files to separate the data.
     */
    public static final String PARAMETERS_SEPARATOR = ": ";

    /**
     * Method for parsing Task parameters from file into List of Strings.
     *
     * @param file file with data
     * @return List of parameters
     */
    @Override
    public List<String> parseTaskParameters(String file) throws TaskException {
        String title = null;
        String dueDate = null;
        String assignedTo = null;
        String description = null;
        List<String> parameters = new ArrayList<>();

        if (file == null || file.isEmpty()) {
            logger.error("Sorry, unable to read the file");
            throw new TaskException("unable to read the file");
        }
        GeneralTaskFactory factory = new GeneralTaskFactory();
        InputStream inputStream = TaskFileReaderImpl.class.getClassLoader().getResourceAsStream(file);
        if (inputStream == null) {
            logger.error("Sorry, unable to find the file");
            throw new TaskException("unable to find the file");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(PARAMETERS_SEPARATOR, 2);
                String key = parts[0];
                String value = parts[1];

                switch (key) {
                    case "Title":
                        title = value;
                        parameters.add(value);
                        break;
                    case "DueDate":
                        dueDate = value;
                        parameters.add(value);
                        break;
                    case "AssignedTo":
                        assignedTo = value;
                        parameters.add(value);
                        break;
                    case "Description":
                        description = value;
                        parameters.add(value);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new TaskException("cannot parse the task", e);
        }
        return parameters;
    }
}
