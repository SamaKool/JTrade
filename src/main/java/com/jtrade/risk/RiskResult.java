package com.jtrade.risk;

public class RiskResult {
    private final String result;
    private final boolean approved;

    public RiskResult(boolean approved, String result) {
        if(result == null) throw new NullPointerException ("result " + result + " cannot be null.");
        else if(result.isBlank()) throw new IllegalArgumentException ("result " + result + " cannot be blank.");

        this.result = result;
        this.approved = approved;
    }

    public String getResult() { return result; }
    public boolean getApproved() { return approved; }

    @Override 
    public String toString() {
        return "RiskResult{" +
            "result='" + result + '\'' +
            ", approved=" + approved +
            '}';
    }
}
