package com.alibou.Notification.Notification;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository  extends MongoRepository<Notification,String> {

}
