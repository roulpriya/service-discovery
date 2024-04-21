package com.app.servicediscoveryservice.controller;

import com.app.servicediscoveryservice.model.Service;
import com.app.servicediscoveryservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@RestController
public class RegisterServiceController {

    private final RegisterService registerService;

    @Autowired
    public RegisterServiceController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/registerService")
    public Service register(@RequestBody Service service) {
        return registerService.registerService(service);
    }


    @PostMapping("/deregisterService")
    public ResponseEntity<String> deregister(@RequestParam String namespace) {
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deregistered service");
    }

    @GetMapping("/services/{name}")
    public List<Service> getService(@PathVariable String name) {
        return registerService.getService(name);
    }

    @PostMapping("/registerInstances")
    public ResponseEntity<String> register(@RequestBody List<Service> services) throws UnknownHostException {
        for (Service service : services) {
            registerService.registerService(service);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Successfully registered service instances");
    }

}
