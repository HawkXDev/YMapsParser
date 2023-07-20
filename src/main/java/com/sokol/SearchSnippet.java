package com.sokol;

public class SearchSnippet {
    private String dataId;
    private String dataCoordinates;
    private String href;
    private String title;
    private String address;
    private String workingStatus;
    private String title2;
    private String phoneNumber;
    private int pictureCount;
    private int reviewCount;
    private String businessUrl;

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

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPictureCount() {
        return pictureCount;
    }

    public void setPictureCount(int pictureCount) {
        this.pictureCount = pictureCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getBusinessUrl() {
        return businessUrl;
    }

    public void setBusinessUrl(String businessUrl) {
        this.businessUrl = businessUrl;
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
                ", title2='" + title2 + '\'' + '\n' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pictureCount=" + pictureCount +
                ", reviewCount=" + reviewCount +
                ", businessUrl='" + businessUrl + '\'' +
                '}';
    }
}
