package au.com.medibank.exception;

import java.util.Arrays;

/**
 */
public class FitbitsException extends RuntimeException {
    private Object[] entities;

    public FitbitsException(String message) {
        super(message);
    }

    public FitbitsException(Throwable e) {
        super(e);
    }

    public <T extends Object> FitbitsException(String message, Object... entities) {
        super(message);
        this.entities = entities;
    }

    @Override
    public String getMessage() {
        if (entities != null) {
            StringBuffer sb = new StringBuffer();
            sb.append(super.getMessage());
            sb.append(System.getProperty("line.separator"));
            Arrays.asList(entities).forEach((entity) -> sb.append(entity + System.getProperty("line.separator")));
            return sb.toString();
        }
        return super.getMessage();
    }
}
