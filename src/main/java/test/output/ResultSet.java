package test.output;

public class ResultSet {
    private String description;
    private String data;

    public ResultSet(String desc, String data){
        this.description=desc;
        this.data=data;
    }
    public String getDescription() {
        return description;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
