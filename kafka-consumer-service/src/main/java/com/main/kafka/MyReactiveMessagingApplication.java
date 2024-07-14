package com.main.kafka;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyReactiveMessagingApplication {
    
    private static final Logger LOG = Logger.getLogger(MyReactiveMessagingApplication.class);

	@Incoming("ProcessData_topic")
	public void processDataFromTopic(Record<String, String> record) {
		try {
			LOG.info("consumer data==>"+record.key()+"======>"+ record.value());
			writeToFile(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void writeToFile(Record<String, String> record) throws IOException {
        String fileName = "kafka_data.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Key: " + record.key() + ", Value: " + record.value());
            writer.newLine();
        }catch (Exception e) {
        	e.printStackTrace();
		}
    }

}
