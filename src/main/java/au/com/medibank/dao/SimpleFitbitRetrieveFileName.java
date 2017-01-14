package au.com.medibank.dao;

import static au.com.medibank.Constants.DEFAULT_INPUT_FILE_NAME;

/**
 */
public class SimpleFitbitRetrieveFileName implements FitbitRetrieveFileName {

    private String fileName = DEFAULT_INPUT_FILE_NAME;

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
