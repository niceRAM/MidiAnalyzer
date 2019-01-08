package org.niceram.midianalyzer.model;

import org.niceram.midianalyzer.utils.StringUtils;

/**
 * A Class representing a note. Used to store note note to be printed out to the finished text file.
 * @author andrewalc, niceRAM
 * @version 20190108 custom-template
 */
public class Note {

  private int startingBeat;
  private int endBeat;
  private int pitch;
  private int instrument;
  private int volume;

  /**
   * command template;
   */
  private String template;

  private static final String START = "\\$\\{start\\}";
  private static final String END = "\\$\\{end\\}";
  private static final String PITCH = "\\$\\{pit\\}";
  private static final String INSTRUMENT = "\\$\\{ins\\}";
  private static final String VOLUME = "\\$\\{vol\\}";

  private static final String ENTER = "\\$\\{enter\\}";

  public Note(){};
  /**
   * Constructor for a Note.
   *
   * @param startingBeat The beat the note starts at.
   * @param endBeat      The beat the note ends at.
   * @param instrument   The MIDI instrument value the note will play as.
   * @param pitch        The MIDI pitch value the note will play as.
   * @param volume       The volume this note will play at.
   */
  public Note(int startingBeat, int endBeat, int instrument, int pitch, int volume) {
    this.startingBeat = startingBeat;
    this.endBeat = endBeat;
    this.pitch = pitch;
    this.instrument = instrument;
    this.volume = volume;
  }

  public int getStartingBeat() {
    return startingBeat;
  }

  public int getEndBeat() {
    return endBeat;
  }

  public int getPitch() {
    return pitch;
  }

  public int getInstrument() {
    return instrument;
  }

  public int getVolume() {
    return volume;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  @Override
  public String toString() {
    if (StringUtils.isNotBlank(template)) {
      return template
              // lowerCase
              .replaceAll(START, String.valueOf(startingBeat))
              .replaceAll(END, String.valueOf(endBeat))
              .replaceAll(PITCH, String.valueOf(pitch))
              .replaceAll(INSTRUMENT, String.valueOf(instrument))
              .replaceAll(VOLUME, String.valueOf(volume))
              // upperCase
              .replaceAll(START.toUpperCase(), String.valueOf(startingBeat))
              .replaceAll(END.toUpperCase(), String.valueOf(endBeat))
              .replaceAll(PITCH.toUpperCase(), String.valueOf(pitch))
              .replaceAll(INSTRUMENT.toUpperCase(), String.valueOf(instrument))
              .replaceAll(VOLUME.toUpperCase(), String.valueOf(volume))
              // line separator
              .replaceAll("(\r\n)|(\r)|(\n)", System.lineSeparator())
              .replaceAll(ENTER, System.lineSeparator());
    } else {
      return "note " + startingBeat + " " + endBeat + " " + instrument + " " + pitch + " " + volume;
    }
  }
}
