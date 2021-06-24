package com.restapi.Restfull.API.Server.services;/*
 * Created By zlzld in SpringMavenProject
 * Date : 1월 월요일 16 10
 */

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@Log4j2
@Service
public class CDNService {
    private String accessKey = "test";
    private String secretKey = "test";
    private String bucketName = "test";

    private AmazonS3 s3Client;

    public CDNService() throws AmazonClientException {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
        if (!s3Client.doesBucketExistV2(bucketName)) {
            // Verify that the bucket was created by retrieving it and checking its location.
            s3Client.createBucket(bucketName);
            s3Client.getBucketLocation(new GetBucketLocationRequest(bucketName));
        } else {
            /*Path has already*/
        }
    }

    /**
     * @param filepath : CDN File Directory(File Path) + File Name
     * @param file     : File file
     * @return String : Cdn File Path
     */
    public String upload(String filepath, File file) {
        String awsCdnFilePath = filepath;
        s3Client.putObject(bucketName, awsCdnFilePath, file);
        return awsCdnFilePath;
    }

    public File download(String awsCdnFilePath) throws IOException {
        File file = null;
        S3Object s3object = s3Client.getObject(bucketName, awsCdnFilePath);    //#1 - 버킷에 있는 파일을 다운로드함
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, file);  //#2 - 스트림을 파일로 저장함
        return file;
    }

    public boolean delete(String awsCdnFilePath) {
        s3Client.deleteObject(new DeleteObjectRequest(bucketName, awsCdnFilePath));
        return true;
    }

    public boolean deletes(String[] awsCdnFilePaths) {
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName).withKeys(awsCdnFilePaths);
        s3Client.deleteObjects(delObjReq);
        return true;
    }

    public ArrayList<String> finds() {
        ArrayList<String> finds = new ArrayList<>();
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        int i = 0;
        int j = 0;
        while (true) {
            Iterator<S3ObjectSummary> iterator = objectListing.getObjectSummaries().iterator();
            while (iterator.hasNext()) {
                S3ObjectSummary object = iterator.next();
                log.info("Directory Index : " + j + "\nItem Index : " + i + "\nKey : " + object.getKey());
                log.info("Directory : " + object.getKey().substring(0, object.getKey().lastIndexOf("/")));
                log.info("File : " + object.getKey().substring(object.getKey().lastIndexOf("/") + 1));
                i++;
            }
            if (objectListing.isTruncated()) {
                j++;
                objectListing = s3Client.listNextBatchOfObjects(objectListing);
            } else {
                break;
            }
        }
        return finds;
    }
}
