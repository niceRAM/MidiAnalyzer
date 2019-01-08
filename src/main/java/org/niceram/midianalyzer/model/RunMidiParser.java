package org.niceram.midianalyzer.model;

import org.niceram.midianalyzer.view.MidiParserView;


/**
 * Main method for running a MidiParser.
 */
public class RunMidiParser {
  public static void main(String[] args) {
    MidiParserView view = new MidiParserView();
    view.initialize();
  }
}
