package com.sokol;

public class SearchSnippet {
    private String dataId;
    private String dataCoordinates;
    private String href;
    private String title;
    private String address;
    private String workingStatus;

    public SearchSnippet() {
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataCoordinates() {
        return dataCoordinates;
    }

    public void setDataCoordinates(String dataCoordinates) {
        this.dataCoordinates = dataCoordinates;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    @Override
    public String toString() {
        return "SearchSnippet{" +
                "dataId='" + dataId + '\'' +
                ", dataCoordinates='" + dataCoordinates + '\'' +
                ", href='" + href + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", workingStatus='" + workingStatus + '\'' +
                '}';
    }
}
