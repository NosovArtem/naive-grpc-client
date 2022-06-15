package org.example;

import com.example.gprc.GreetingServiceGrpc;
import com.example.gprc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:8080")
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder()
                .setName("Artem")
                .build();

        GreetingServiceOuterClass.HelloResponse helloResponse = stub.greeting(request);
        System.out.println(helloResponse);

        channel.shutdownNow();
    }
}
