package org.niceram.midianalyzer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.niceram.midianalyzer.view.MidiParserView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author niceRAM
 * @date 2019.01.02
 * @version 0.0.1
 */
@SpringBootApplication
public class MidiAnalyzerApplication {

	public static void main(String[] args) {
		new MidiParserView().initialize();
		SpringApplication.run(MidiAnalyzerApplication.class, args);
	}

}

