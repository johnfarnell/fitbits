package au.com.medibank.dao;

import au.com.medibank.exception.FitbitsException;

import java.util.Scanner;

import static au.com.medibank.Constants.DEFAULT_INPUT_FILE_NAME;

/**
 * Returns an Image object that can then be painted on the screen.
 * The url argument must specify an absolute {@link URL}. The name
 * argument is a specifier that is relative to the url argument.
 * <p>
 * This method always returns immediately, whether or not the
 * image exists. When this applet attempts to draw the image on
 * the screen, the data will be loaded. The graphics primitives
 * that draw the image will incrementally paint on the screen.
 */
public class ConsoleFitbitRetrieveFileName implements FitbitRetrieveFileName {
    public String getFileName() {
        try {
            // creates a console object
            Scanner sc = new Scanner(System.in);

            //PRovide a default
            String defaultFileName = DEFAULT_INPUT_FILE_NAME;
            System.out.println("Please enter the Fitbits input file or press enter for the default (\"" + defaultFileName + "\")");
            String fileName = sc.nextLine();

            if ((fileName == null) || (fileName.trim().length() == 0)) {
                fileName = defaultFileName;
            }

            return fileName;
        } catch (Exception e) {
            throw new FitbitsException(e);
        }
    }
}
