package com.ph.security.demo.client.future;

import com.ph.security.demo.client.rpc.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ClientFuture {
    @Autowired
    private IClientService clientService;

    public CompletableFuture<String> clientAuthen() {
        return CompletableFuture.supplyAsync(()->{
            return clientService.clientAuthen();
        });
    }
}
