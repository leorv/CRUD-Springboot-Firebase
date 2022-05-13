package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CRUDController {
    public CRUDService crudService;

    public CRUDController(CRUDService crudService){
        this.crudService = crudService;
    }

    @PostMapping(value="/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.createCRUD(crud);
    }

    @GetMapping(value = "/get")
    public CRUD getCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return crudService.getCRUD(documentId);
    }

    @PutMapping(value = "/update")
    public String updateCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.updateCRUD(crud);
    }

    @DeleteMapping(value = "/delete")
    public CRUD deleteCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return crudService.deleteCRUD(documentId);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> testGetEndPoint() {
        return ResponseEntity.ok("Test Get EndPoint is working.");
    }
    
}
