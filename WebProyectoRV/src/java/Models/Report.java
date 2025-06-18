package Models;

import java.util.Date;

public class Report {
    private int id;
    private int userId;
    private int problemTypeId;
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private String address;
    private int statusId;
    private Date reportedAt;

    private byte[] photo;
    private String contentType;
    private String userName;

    public Report() {}


    
    public Report(int id, int userId, int problemTypeId, String title, String description,
                double latitude, double longitude, String address, int statusId, Date reportedAt,
                byte[] photo, String contentType) {
          this.id = id;
          this.userId = userId;
          this.problemTypeId = problemTypeId;
          this.title = title;
          this.description = description;
          this.latitude = latitude;
          this.longitude = longitude;
          this.address = address;
          this.statusId = statusId;
          this.reportedAt = reportedAt;
          this.photo = photo;
          this.contentType = contentType;
      }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProblemTypeId() { return problemTypeId; }
    public void setProblemTypeId(int problemTypeId) { this.problemTypeId = problemTypeId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getStatusId() { return statusId; }
    public void setStatusId(int statusId) { this.statusId = statusId; }

    public Date getReportedAt() { return reportedAt; }
    public void setReportedAt(Date reportedAt) { this.reportedAt = reportedAt; }

    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
    
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
}