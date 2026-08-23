package org.ami;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import com.aliyun.sdk.service.oss2.exceptions.ServiceException;
import com.aliyun.sdk.service.oss2.models.*;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.UUID;

public class PutObject implements Example {

    public static void main(String[] args) {
        System.out.println("AK: [" + System.getenv("OSS_ACCESS_KEY_ID") + "]");
        System.out.println("SK: [" + System.getenv("OSS_ACCESS_KEY_SECRET") + "]");

        String originalFilename = ".jpg";
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String randomKey = "images/" + UUID.randomUUID().toString() + extension;

        execute(null, "cn-beijing", "aimiai", randomKey);
    }

    private static void execute(
            String endpoint,
            String region,
            String bucket,
            String key) {

        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region);

        if (endpoint != null) {
            clientBuilder.endpoint(endpoint);
        }

        try (OSSClient client = clientBuilder.build()) {

//            String data = "hello world";
            byte[] fileBytes = Files.readAllBytes(Path.of("C:\\Users\\艾米\\Desktop\\图片\\11949215_143004845103_2.jpg"));



            PutObjectResult result = client.putObject(PutObjectRequest.newBuilder()
                            .bucket(bucket)
                            .key(key)
                    .body(BinaryData.fromBytes(fileBytes))
                    .build());

            System.out.printf("status code:%d, request id:%s, eTag:%s\n",
                    result.statusCode(), result.requestId(), result.eTag());

        } catch (Exception e) {
            ServiceException se = ServiceException.asCause(e);
            if (se != null) {
                System.out.printf("ServiceException: requestId:%s, errorCode:%s, errorMessage:%s, statusCode:%d\n",
                        se.requestId(), se.errorCode(), se.errorMessage(), se.statusCode());
            } else {
                System.out.printf("error:\n%s", e);
            }
        }
    }

    @Override
    public Options getOptions() {
        Options opts = new Options();
        opts.addOption(Option.builder().longOpt("endpoint").desc("The domain names that other services can use to access OSS.").hasArg().build());
        opts.addOption(Option.builder().longOpt("region").desc("The region in which the bucket is located.").hasArg().required().build());
        opts.addOption(Option.builder().longOpt("bucket").desc("The name of the bucket.").hasArg().required().build());
        opts.addOption(Option.builder().longOpt("key").desc("The name of the object.").hasArg().required().build());
        return opts;
    }

    @Override
    public void runCmd(CommandLine cmd) throws ParseException {
        String endpoint = cmd.getParsedOptionValue("endpoint");
        String region = cmd.getParsedOptionValue("region");
        String bucket = cmd.getParsedOptionValue("bucket");
        String key = cmd.getParsedOptionValue("key");
        execute(endpoint, region, bucket, key);
    }
}
