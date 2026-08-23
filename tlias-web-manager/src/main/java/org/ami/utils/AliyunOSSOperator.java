//package org.ami.utils;
//
//import com.aliyun.sdk.service.oss2.OSSClient;
//import com.aliyun.sdk.service.oss2.OSSClientBuilder;
//import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
//import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
//import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
//import com.aliyun.sdk.service.oss2.models.PutObjectResult;
//import com.aliyun.sdk.service.oss2.transport.BinaryData;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.UUID;
//
//@Component
//public class AliyunOSSOperator {
//
//    @Value("${aliyun.oss.region}")
//    private String region;
//
//    @Value("${aliyun.oss.bucket}")
//    private String bucket;
//
//    public String upload(byte[] fileBytes, String originalFilename) {
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
//        String key = dir + "/" + UUID.randomUUID().toString() + extension;
//
//        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
//        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
//                .credentialsProvider(provider)
//                .region(region);
//
//        try (OSSClient client = clientBuilder.build()) {
//            PutObjectResult result = client.putObject(PutObjectRequest.newBuilder()
//                    .bucket(bucket)
//                    .key(key)
//                    .body(BinaryData.fromBytes(fileBytes))
//                    .build());
//
//            System.out.printf("upload success, statusCode:%d, requestId:%s, eTag:%s%n",
//                    result.statusCode(), result.requestId(), result.eTag());
//        } catch (Exception e) {
//            throw new RuntimeException("文件上传失败: " + e.getMessage(), e);
//        }
//
//        return "https://" + bucket + ".oss-" + region + ".aliyuncs.com/" + key;
//    }
//}
//
