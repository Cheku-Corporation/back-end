package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Group;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.GroupService;
import com.cheku.cheku.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIDeleteController {

    @Autowired
    private CarService carService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private NotificationService notificationService;

    @DeleteMapping("api/user/{userId}/group/{groupId}/car/{carId}")
    public Object deleteCar(@PathVariable Long carId, @PathVariable Long groupId, @PathVariable Long userId) throws ResourceNotFoundException {
        Group group = groupService.deleteFromGroup(groupId);
        if (groupService.verifyAdmin(userId, groupId)) {
            carService.deleteCar(carId);
            return new SuccessResponse("Carro apagado com sucesso");
        } else {
            return new ResourceNotFoundException("Not authorized");
        }
    }

    private static class SuccessResponse {
        private String success;

        public SuccessResponse(String success) {
            this.success = success;
        }

        public String getSuccess() {
            return success;
        }
    }

    @DeleteMapping("api/group/{group_id}/notification/{not_id}")
    public void deleteNotification(@PathVariable Long group_id, @PathVariable Long not_id) throws ResourceNotFoundException {
        // Group group = groupService.deleteFromGroup(groupId);
        // if (groupService.verifyAdmin(userId, groupId)) {
        //     carService.deleteCar(carId);
        //     return new SuccessResponse("Carro apagado com sucesso");
        // } else {
        //     return new ResourceNotFoundException("Not authorized");
        // }
        notificationService.deleteNotification(not_id);
    }
}
