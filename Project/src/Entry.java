public class Entry implements Comparable<Entry>{
    private String term;
    private String statement;
    private Double score;

    public Entry(String term, String statement, Double score){
        this.term = term;
        this.statement = statement;
        this.score = score;
    }

    
    /** 
     * @return Double
     */
    public Double getScore() {
        return score;
    }

    
    /** 
     * @param score
     */
    public void setScore(Double score){
        this.score = score;
    }

    
    /** 
     * @return String
     */
    public String getStatement() {
        return statement;
    }

    
    /** 
     * @param statement
     */
    public void setStatement(String statement){
        this.statement = statement;
    }

    
    /** 
     * @return String
     */
    public String getTerm() {
        return term;
    }

    
    /** 
     * @return String
     */
    public String toString(){
        return term + "\t" + statement + "\t" + score;
    }

    
    /** 
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Entry o) {
        return this.term.compareTo(o.term);
    }

}
