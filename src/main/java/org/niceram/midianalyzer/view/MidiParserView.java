package org.niceram.midianalyzer.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.niceram.midianalyzer.model.MidiParser;
import org.niceram.midianalyzer.utils.StringUtils;

/**
 * JFrame view for the MidiParser.
 * @author andrewalc, niceRAM
 * @version 20190108 message update, add custom-template
 */
public class MidiParserView extends JFrame {

  private Color clBackground = new Color(43, 43, 43);
  private Color clButton = new Color(74, 74, 74);
  private JButton openButton = new JButton("Choose MIDI");
  private JTextArea templateArea = new JTextArea();
  private JFileChooser jFileChooser = new JFileChooser();
  private JLabel message = new JLabel("Please input template at textArea and click button to choose MIDI.");
  private JLabel authorLabel = new JLabel("built by niceRAM, modified from CS3500 MIDIToTxt Converter");


  /**
   * Constructor for a MidiParserView. A Swing Application to select a midifile to convert to a txt.
   */
  public MidiParserView() {
    super("MidiAnalyzer");
    setSize(600, 275);
    setResizable(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setBackground(clBackground);
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


    // FileChooser settings
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Midi Files", "mid", "midi");
    jFileChooser.setFileFilter(filter);

    // Set up LookAndFeel of FileChooser.
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException ex) {
      ex.getMessage();
    } catch (InstantiationException ex) {
      ex.getMessage();
    } catch (IllegalAccessException ex) {
      ex.getMessage();
    } catch (UnsupportedLookAndFeelException ex) {
      ex.getMessage();
    }
    SwingUtilities.updateComponentTreeUI(jFileChooser);


    // Button Settings
    openButton.setPreferredSize(new Dimension(150, 30));
    openButton.setBackground(clButton);
    openButton.setForeground(Color.white);
    openButton.setFocusPainted(false);
    openButton.setBorder(BorderFactory.createLineBorder(Color.white));

    // Give button an action listener to open the file chooser.
    openButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
          int returnVal = jFileChooser.showOpenDialog(MidiParserView.this);
          if (returnVal == JFileChooser.APPROVE_OPTION) {
            writeSelectedFile(jFileChooser.getSelectedFile());


          } else {
            System.out.println("A file was not selected.");
          }
        }
      }
    });

    // Label placement
    c.gridx = 0;
    c.gridy = 0;
    c.ipady = 20;
    c.anchor = GridBagConstraints.CENTER;
    message.setForeground(Color.WHITE);
    getContentPane().add(message, c);

    // templateArea placement
    templateArea.setSize(new Dimension(500, 20));
    templateArea.setLineWrap(true);
    JScrollPane scroll = new JScrollPane(templateArea);

    scroll.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    c.gridy = 1;
    c.ipady = 50;
    c.anchor = GridBagConstraints.CENTER;
    getContentPane().add(scroll, c);

    // Button placement
    c.gridy = 2;
    c.ipady = 20;
    c.anchor = GridBagConstraints.SOUTH;
    getContentPane().add(new JLabel(), c);

    c.gridy = 3;
    c.ipady = 0;
    c.anchor = GridBagConstraints.CENTER;
    getContentPane().add(openButton, c);

    // Author placement
    c.gridy = 4;
    c.ipady = 30;
    c.anchor = GridBagConstraints.SOUTH;
    getContentPane().add(new JLabel(), c);

    c.gridy = 5;
    c.ipady = 0;
    c.anchor = GridBagConstraints.SOUTH;
    authorLabel.setForeground(Color.GRAY);
    getContentPane().add(authorLabel, c);
  }


  /**
   * Display the view.
   */
  public void initialize() {
    setVisible(true);
  }

  /**
   * Sends the given midifile to a MidiParser to convert it to a txt file. The txt file will be
   * written in the directory that the midi file came from.
   *
   * @param midiFile The midi file to convert to a txt file.
   */
  public void writeSelectedFile(File midiFile) {
    try {
      MidiParser parser = new MidiParser(midiFile);
      // set template
      if (StringUtils.isNotBlank(this.templateArea.getText())) {
        parser.setTemplate(this.templateArea.getText());
      }
      parser.writeMidiTextFile();
      // Update the gui to show conversion information.
      this.message.setForeground(Color.GREEN);
      this.message.setText(parser.getSuccessMessage());
      this.openButton.setText("Convert another MIDI");
    } catch (InvalidMidiDataException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } catch (MidiUnavailableException e) {
      System.out.println(e.getMessage());
    }
  }
}
