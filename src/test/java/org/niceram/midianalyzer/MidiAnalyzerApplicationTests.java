package org.niceram.midianalyzer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.niceram.midianalyzer.model.Note;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MidiAnalyzerApplicationTests {

	@Test
	public void contextLoads() {
		Note note = new Note();
		note.setTemplate("enter ${enter}" +
				"\\r\\n ${start} \r\n" +
				"\\r ${start} \r" +
				"\\n ${start} \n");
		System.err.println(note);
	}

}

