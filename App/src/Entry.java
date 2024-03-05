public class Entry implements Comparable<Entry>{
    private String term;
    private String statement;
    private Double score;

    public Entry(String term, String statement, Double score){
        this.term = term;
        this.statement = statement;
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    public String getStatement() {
        return statement;
    }

    public String getTerm() {
        return term;
    }

    public String toString(){
        return term + "\t" + statement + "\t" + score;
    }

    @Override
    public int compareTo(Entry o) {
        return this.term.compareTo(o.term);
    }

}
