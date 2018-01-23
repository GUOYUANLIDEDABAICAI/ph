package com.ph.security.demo.client.future;

import com.ph.security.demo.client.rpc.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class LanguageFuture {
    @Autowired
    private ILanguageService languageService;

    public CompletableFuture<String> clientAuthen() {
        return CompletableFuture.supplyAsync(()->{
            return languageService.clientAuthen();
        });
    }
}
