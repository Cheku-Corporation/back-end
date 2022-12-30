package com.cheku.cheku.service;

import com.cheku.cheku.auxiliar_classes.SimpleFluid;
import com.cheku.cheku.auxiliar_classes.UserNotification;
import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.Fluid;
import com.cheku.cheku.model.Group;
import com.cheku.cheku.model.Notification;
import com.cheku.cheku.repository.FluidRepository;
import com.cheku.cheku.repository.NotificationRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRespository notificationRespository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CarService carService;

    public List<Notification> getAllNotifications(Long group_id) {
        Group group = groupService.getGroupById(group_id);
        Date date = new Date();
        long now = date.getTime() / 100;
        for (Car car : group.getCarList()) {
            System.out.println(car.getInspectionDate().getTime());
            if(car.getInspectionDate().getTime() - now < 1000000) {
                System.out.println("Adding notification Inspection");
                Notification not = new Notification();
                not.setPriority(2);
                not.setSubject("Inspection Insurance");
                not.setMessage("Your Inspection Insurance will expire soon");
                not.setGroup(groupService.getGroupById(group_id));
                notificationRespository.save(not);
            }
        }
        return notificationRespository.getAllNotifications(group_id);
    }

    public Notification addNotification(Notification notification) throws ResourceNotFoundException {
        return notificationRespository.save(notification);
    }

    public void deleteNotification(Long notification_id) {
        notificationRespository.deleteById(notification_id);
    }
}
