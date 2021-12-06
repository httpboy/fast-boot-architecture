package com.example.grpcclient;

import com.grpc.grpclib.HelloReply;
import com.grpc.grpclib.HelloRequest;
import com.grpc.grpclib.SimpleGrpc;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("local-grpc-server")
    private SimpleGrpc.SimpleBlockingStub simpleBlockingStub;

    public String sendMessage(final String name) {
        try {
            final HelloReply response =  simpleBlockingStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            e.printStackTrace();
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }

}