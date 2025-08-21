package com.a3.lfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.a3.lfs.service.BatchService;

@SpringBootApplication
public class BatchApplication implements CommandLineRunner{

	@Autowired
	private BatchService batchService;

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		if(args.length != 1){

			System.out.println("invalid argument. argument is [status | shop | dpt]");
			return;
		}

		String cmd = args[0];

		if(cmd.equals("status")){
			batchService.makeDailyStatus();
			batchService.scenarioDetect();
		}
		else if(cmd.equals("shop")){

			batchService.syncShopInfo();
		}
		else if(cmd.equals("usrDptSync")){
			
			batchService.syncUsrDptInfo();
		}
		else{

			System.out.println("invalid argument. argument is [status | shop | dpt]");
		}
	}
}