package com.restapi.Restfull.API.Server.utility;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirebaseStoreDelete {

    Firestore db;
    int batchSize = 100;

    public void deleteFireStore(HttpServletRequest request, int userNo) {
        try {
            init(request);
            ArrayList<String> chatRoomIdList = getDocumentOfUser(userNo);
            for(int i = 0; i<chatRoomIdList.size(); i++){
                //부모 문서 하위 컬렉션 삭제
                CollectionReference collection = db.collection("Chat").document(chatRoomIdList.get(i)).collection("MESSAGES");
                deleteCollection(collection, batchSize);
                //부모 문서 삭제
                db.collection("Chat").document(chatRoomIdList.get(i)).delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error deleting collection : " + e.getMessage());
        }
    }

    void init(HttpServletRequest request) {
        try {
            InputStream serviceAccount = new FileInputStream(request.getSession().getServletContext().getRealPath("/") + "resources/firebase/eatchange-e61d2-firebase-adminsdk-ct2ba-71fa74f196.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();

            if(FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options);
                db = FirestoreClient.getFirestore();
                System.out.println("fireStore init success");
            } else{
                System.out.println("fireStore init Exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fireStore init fail");
        }
    }

    public ArrayList<String> getDocumentOfUser(int userNo) throws Exception {
        ArrayList<String> result = new ArrayList<>();

        CollectionReference citiesRef = db.collection("Chat");

        Query query = citiesRef.whereArrayContains("allid", userNo);
        ApiFuture<QuerySnapshot> future = query.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            result.add(document.getId());
        }

        return result;
//        ApiFuture<QuerySnapshot> future1 = db.collection("Chat").get();
//        List<QueryDocumentSnapshot> documents2 = future1.get().getDocuments();
//        for (QueryDocumentSnapshot document : documents2) {
//            System.out.println("document: " + document.getData());
//        }
    }

    void deleteCollection(CollectionReference collection, int batchSize) throws Exception {
        // retrieve a small batch of documents to avoid out-of-memory errors
        ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
        int deleted = 0;
        // future.get() blocks on document retrieval
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            document.getReference().delete();
            ++deleted;
        }
        if (deleted >= batchSize) {
            // retrieve and delete another batch
            deleteCollection(collection, batchSize);
        }
    }
}
