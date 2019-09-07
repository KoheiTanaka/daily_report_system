package models;

public class BookmarkCheck {





    private Report report;
    private boolean check = false;

    public Report getReport(){
        return report;
    }
    public void setReport(Report report){
        this.report = report;
    }
    public boolean isCheck(){
        return check;
    }
    public void setCheck(boolean check){
        this.check = check;
    }
    }

