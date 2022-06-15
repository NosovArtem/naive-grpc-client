package org.example;

import com.example.gprc.GreetingServiceGrpc;
import com.example.gprc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

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

        Iterator<GreetingServiceOuterClass.HelloResponse> helloResponse = stub.greeting(request);

        while (helloResponse.hasNext()){
            System.out.println(helloResponse.next());
        }

        channel.shutdownNow();
    }
}
