package test.output;

public class ResultSet {
    private String description;
    private String data;
    private Object descriptionObject;
    private Object dataObject;

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

    public Object getDescriptionObject() {
        return descriptionObject;
    }

    public void setDescriptionObject(Object descriptionObject) {
        this.descriptionObject = descriptionObject;
    }

    public Object getDataObject() {
        return dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }
}
