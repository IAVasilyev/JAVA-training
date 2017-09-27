/**
 * Realease calculator class
 */
public class calculator {
    /**
     * storage results
     */
    private int result = 0;

    /**
     * Realease add function
     *
     * @param params elements from sum
     */
    public void add(Integer... params) {
        for (Integer param : params) {
            this.result += param;
        }
    }

    /**
     * Realease divide function
     *
     * @param params elements for divide
     */
    public void divide(Integer... params) {
        for (Integer param : params) {
            this.result /= param;
        }
    }

    /**
     * Return result of calculation
     *
     * @return result of calculation
     */
    public int getResult() {
        return this.result;
    }

    /**
     * Clear results
     */
    public void clearResult() {
        this.result = 0;
    }
}