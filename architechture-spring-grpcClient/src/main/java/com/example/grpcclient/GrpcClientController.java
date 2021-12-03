package com.example.grpcclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grpc")
public class GrpcClientController {

    @Autowired
    GrpcClientService grpcClientService;

    @RequestMapping("/sendName")
    public String printMessage(@RequestParam(defaultValue = "name") String name) {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9898)
//                .usePlaintext(true)
//                .build();
//
//        SimpleGrpc.SimpleBlockingStub stub =
//                SimpleGrpc.newBlockingStub(channel);
//
//        HelloReply helloResponse = stub.sayHello(
//                HelloRequest.newBuilder()
//                        .setName("forezp")
//                        .build());
//
//        System.out.println(helloResponse);
//
//
//        channel.shutdown();
//        return "";
        return grpcClientService.sendMessage(name);
    }

}