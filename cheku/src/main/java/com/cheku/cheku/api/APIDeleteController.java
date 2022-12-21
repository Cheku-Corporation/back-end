package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.Group;
import com.cheku.cheku.service.CarService;
import com.cheku.cheku.service.GroupService;
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

    @DeleteMapping("api/user/{userId}/group/{groupId}/car/{carId}")
    public Object deleteCar(@PathVariable Long carId, @PathVariable Long groupId, @PathVariable Long userId) throws ResourceNotFoundException {
        Group group = groupService.getGroupById(groupId);
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
}
