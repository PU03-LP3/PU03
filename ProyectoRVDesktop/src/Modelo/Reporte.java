package Modelo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Reporte {
    private int id;
    private int userId;
    private int problemTypeId;
    private String title;
    private String description;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private int statusId;
    private Timestamp reportedAt;
    private Integer assignedTo;

    public Reporte() {
    }

    public Reporte(int id, int userId, int problemTypeId, String title, String description,
                   BigDecimal latitude, BigDecimal longitude, String address, int statusId,
                   Timestamp reportedAt, Integer assignedTo) {
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
        this.assignedTo = assignedTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProblemTypeId() {
        return problemTypeId;
    }

    public void setProblemTypeId(int problemTypeId) {
        this.problemTypeId = problemTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Timestamp reportedAt) {
        this.reportedAt = reportedAt;
    }

    public Integer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }
}
