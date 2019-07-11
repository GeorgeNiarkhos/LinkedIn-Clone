package com.sdi1500103sdi1500109.Network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sdi1500103sdi1500109.Network.StorageService;

@SpringBootApplication
public class NetworkApplication {

	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(NetworkApplication.class, args);
	}
	
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
