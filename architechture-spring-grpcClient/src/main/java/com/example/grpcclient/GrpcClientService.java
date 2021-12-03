package com.example.grpcclient;

import com.grpc.grpclib.HelloReply;
import com.grpc.grpclib.HelloRequest;
import com.grpc.grpclib.SimpleGrpc;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("local-grpc-server")
    private Channel serverChannel;

    public String sendMessage(final String name) {
        try {
            final HelloReply response =  SimpleGrpc.newBlockingStub(serverChannel).sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }

}