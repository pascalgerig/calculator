import java.util.Observable;

public class CalcModel extends Observable implements CalculatorModel {

    private String query;
    private String display;

    public CalcModel() {
        this.display = "";
        this.query = "";
    }

    /**
     * Update the query by adding an operation to the end
     * @param operation
     */
    public void updateQuery(Operation operation) {
        switch (operation) {
            case ADD:
                query += "+";
                break;
            case SUB:
                query += "-";
                break;
            case MULT:
                query += "*";
                break;
            case DIV:
                query += "/";
                break;
            case MOD:
                query += "%";
                break;
        }
        display = query;
        setChanged();
        notifyObservers(display);
        clearChanged();
    }

    /**
     * update query by adding value to the end
     * @param value
     */
    public void updateQuery(int value) {
        query += Integer.toString(value);
        display = query;
        setChanged();
        notifyObservers(display);
        clearChanged();
    }

    public String displayResult() {
        try {
            float result = this.executeQuery();
            display = Float.toString(result);
            setChanged();
            notifyObservers(display);
            clearChanged();
            query = "";
            return display;
        }
        catch (InvalidQueryException e) {
            display = "Invalid query";
            setChanged();
            notifyObservers(display);
            clearChanged();
            return display;
        }
    }

    private float executeQuery() throws InvalidQueryException{
        String query = this.query;
        if(this.checkQuery(query)) {
            return this.executeSubQuery(query);
        }
        else {
            display = "";
            throw new InvalidQueryException("This is not a valid Query");
        }
    }

    private float executeSubQuery(String query) {
        Operation split = this.getSplitOperation(query);
        String[] substrings;
        float result;
        if (split == null) {
            return Integer.parseInt(query);
        }
        switch (split) {
            case ADD:
                substrings = query.split("[+]");
                result = 0;
                for(String sub : substrings) {
                    result +=  executeSubQuery(sub);
                }
                return result;
            case SUB:
                substrings = query.split("-");
                result = executeSubQuery(substrings[0]);
                for (int i = 1; i < substrings.length; i++) {
                    result -= executeSubQuery(substrings[i]);
                }
                return result;
            case MULT:
                substrings = query.split("[*]");
                result = executeSubQuery(substrings[0]);
                for (int i = 1; i < substrings.length; i++) {
                    result *= executeSubQuery(substrings[i]);
                }
                return result;
            case DIV:
                substrings = query.split("/");
                result = executeSubQuery(substrings[0]);
                for (int i = 1; i < substrings.length; i++) {
                    result /= executeSubQuery(substrings[i]);
                }
                return result;
            case MOD:
                substrings = query.split("%");
                result = executeSubQuery(substrings[0]);
                for (int i = 1; i < substrings.length; i++) {
                    result %= executeSubQuery(substrings[i]);
                }
                return result;
            default:
                return Integer.parseInt(query);
        }
    }

    private Operation getSplitOperation(String query) {
        if(queryContains(query,'+')) {
            return Operation.ADD;
        }
        else if(queryContains(query,'-')) {
            return Operation.SUB;
        }
        else if(queryContains(query,'*')) {
            return Operation.MULT;
        }
        else if(queryContains(query,'/')) {
            return Operation.DIV;
        }
        else if(queryContains(query,'%')) {
            return Operation.MOD;
        }
        else {
            return null;
        }

    }

    private boolean queryContains(String query, char symbol) {
        for (int i = 0; i < query.length(); i++) {
            if (query.charAt(i) == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean checkQuery(String query) {
        for(int i = 0; i < query.length()-1; i++) {
            if(bothCharsAreNotDigits(query.charAt(i), query.charAt(i+1))) {
                return false;
            }
        }
        return this.firstAndLastCharIsDigit(query);
    }

    private boolean firstAndLastCharIsDigit(String query) {
        return Character.isDigit(query.charAt(0)) && Character.isDigit(query.charAt(query.length()-1));
    }

    private boolean bothCharsAreNotDigits(char first, char second) {
        return !Character.isDigit(first) && !Character.isDigit(second);
    }
}
