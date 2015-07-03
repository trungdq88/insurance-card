package com.fpt.mic.micweb.model.entity.helper;

import javax.persistence.*;

/**
 * FPT University - Capstone Project - Summer 2015 - MICWeb
 * Created by dinhquangtrung on 7/3/15.
 */
@Entity
@Table(name = "mic_notification_read", schema = "", catalog = "mic_data")
public class NotificationReadEntity {
    private int id;
    private String userCode;
    private int notificationId;
    private int isRead;
    private NotificationEntity micNotificationByNotificationId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_code")
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "is_read")
    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    @Basic
    @Column(name = "notification_id")
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @ManyToOne
    @JoinColumn(name = "notification_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public NotificationEntity getMicNotificationByNotificationId() {
        return micNotificationByNotificationId;
    }

    public void setMicNotificationByNotificationId(NotificationEntity micNotificationByNotificationId) {
        this.micNotificationByNotificationId = micNotificationByNotificationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationReadEntity that = (NotificationReadEntity) o;

        if (id != that.id) return false;
        if (isRead != that.isRead) return false;
        if (userCode != null ? !userCode.equals(that.userCode) : that.userCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + isRead;
        return result;
    }
}
