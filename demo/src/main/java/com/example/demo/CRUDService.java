package com.example.demo;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class CRUDService {

    public String createCRUD(CRUD crud) throws InterruptedException, ExecutionException  {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud-test")
            .document(crud.getDocumentId()).create(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public CRUD getCRUD(String documentId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("crud-test").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        CRUD crud;
        if (document.exists()) {
            crud = document.toObject(CRUD.class);
            return crud;
        }
        return null;
    }

    public String updateCRUD(CRUD crud) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> Result = dbFirestore.collection("crud-test")
                .document(crud.getDocumentId()).set(crud);
        return Result.get().getUpdateTime().toString();
    }

    public String deleteCRUD(String documentId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> Result = dbFirestore.collection("crud-test")
                .document(documentId).delete();
        return "Objeto deletado com sucesso.\n".concat(Result.toString());
    }
}
