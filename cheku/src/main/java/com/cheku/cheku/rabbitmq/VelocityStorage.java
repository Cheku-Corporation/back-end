// package com.cheku.cheku.rabbitmq;

// import java.util.Date;

// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Component;

// import com.cheku.cheku.exception.ResourceNotFoundException;
// import com.cheku.cheku.model.HistoryVelocity;
// import com.cheku.cheku.service.VelocityService;

// @Component
// public class VelocityStorage {

//     @Autowired
// 	private VelocityService velocityService;

//     @Bean
//     public void addVelocity(String json_str) {
//         JSONObject j = new JSONObject(json_str);
//         Double vel = j.getDouble("velocity");
//         System.out.println("Vel = " + vel + "\n");   
//         HistoryVelocity historyVelocity = new HistoryVelocity();
//         historyVelocity.setGear(j.getInt("gear"));
//         historyVelocity.setVelocity(j.getDouble("velocity"));
//         historyVelocity.setDate(new Date((long) (j.getDouble("timestamp") * 1000)));
//         try {
//             velocityService.addVelocity(historyVelocity);
//         } catch (ResourceNotFoundException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//     }
// }
