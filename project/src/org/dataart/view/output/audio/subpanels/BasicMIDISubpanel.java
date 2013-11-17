package org.dataart.view.output.audio.subpanels;

import java.awt.GridBagLayout;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.dataart.model.Data;
import org.dataart.util.DataHelper;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class BasicMIDISubpanel extends AAudioSubpanel {
	private final JComboBox<Instrument> instrumentComboBox;
	private final JComboBox<String> velocityFieldComboBox;
	private final JComboBox<String> noteFieldComboBox;
	private JSpinner tempoSpinner;

	private final DefaultComboBoxModel<Instrument> instrumentComboBoxModel = new DefaultComboBoxModel<Instrument>();
	private final DefaultComboBoxModel<String> velocityFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final DefaultComboBoxModel<String> noteFieldComboBoxModel = new DefaultComboBoxModel<String>();
	private final SpinnerNumberModel tempoSpinnerModel = new SpinnerNumberModel();

	private static int MIN_TEMPO = 1;
	private static int MAX_TEMPO = 200;
	private static int CHANNEL = 4;
	private MidiChannel channel;
	private Synthesizer synthesizer;
	private Sequencer sequencer;
	
	public BasicMIDISubpanel() {
		PANEL_TITLE = "Basic MIDI";

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		JLabel lblTempo = new JLabel("Tempo (" + MIN_TEMPO + "-" + MAX_TEMPO + "):");
		GridBagConstraints gbc_lblTempo = new GridBagConstraints();
		gbc_lblTempo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTempo.anchor = GridBagConstraints.EAST;
		gbc_lblTempo.gridx = 0;
		gbc_lblTempo.gridy = 0;
		add(lblTempo, gbc_lblTempo);

		// FIXME: Need to figure out how to not go beyond boundaries
		tempoSpinner = new JSpinner();
		GridBagConstraints gbc_tempoSpinner = new GridBagConstraints();
		gbc_tempoSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_tempoSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_tempoSpinner.gridx = 1;
		gbc_tempoSpinner.gridy = 0;
		add(tempoSpinner, gbc_tempoSpinner);
		tempoSpinner.setModel(tempoSpinnerModel);

		JLabel lblInstrument = new JLabel("Instrument:");
		GridBagConstraints gbc_lblInstrument = new GridBagConstraints();
		gbc_lblInstrument.anchor = GridBagConstraints.EAST;
		gbc_lblInstrument.insets = new Insets(0, 0, 5, 5);
		gbc_lblInstrument.gridx = 0;
		gbc_lblInstrument.gridy = 1;
		add(lblInstrument, gbc_lblInstrument);

		instrumentComboBox = new JComboBox<Instrument>();
		GridBagConstraints gbc_instrumentComboBox = new GridBagConstraints();
		gbc_instrumentComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_instrumentComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_instrumentComboBox.gridx = 1;
		gbc_instrumentComboBox.gridy = 1;
		add(instrumentComboBox, gbc_instrumentComboBox);
		instrumentComboBox.setModel(instrumentComboBoxModel);
		instrumentComboBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				if(value != null && value instanceof Instrument){
					Instrument i = (Instrument) value;
					setText(i.getName());
				}
				return this;
			}
		});
		
		JLabel lblVelocity = new JLabel("Velocity/Volume Field:");
		GridBagConstraints gbc_lblVelocity = new GridBagConstraints();
		gbc_lblVelocity.anchor = GridBagConstraints.EAST;
		gbc_lblVelocity.insets = new Insets(0, 0, 5, 5);
		gbc_lblVelocity.gridx = 0;
		gbc_lblVelocity.gridy = 2;
		add(lblVelocity, gbc_lblVelocity);

		velocityFieldComboBox = new JComboBox<String>();
		GridBagConstraints gbc_velocityFieldComboBox = new GridBagConstraints();
		gbc_velocityFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_velocityFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_velocityFieldComboBox.gridx = 1;
		gbc_velocityFieldComboBox.gridy = 2;
		add(velocityFieldComboBox, gbc_velocityFieldComboBox);
		velocityFieldComboBox.setModel(velocityFieldComboBoxModel);
		
		JLabel lblNote = new JLabel("Note Field:");
		GridBagConstraints gbc_lblNote = new GridBagConstraints();
		gbc_lblNote.anchor = GridBagConstraints.EAST;
		gbc_lblNote.insets = new Insets(0, 0, 5, 5);
		gbc_lblNote.gridx = 0;
		gbc_lblNote.gridy = 3;
		add(lblNote, gbc_lblNote);
		
		noteFieldComboBox = new JComboBox<String>();
		GridBagConstraints gbc_noteFieldComboBox = new GridBagConstraints();
		gbc_noteFieldComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_noteFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_noteFieldComboBox.gridx = 1;
		gbc_noteFieldComboBox.gridy = 3;
		add(noteFieldComboBox, gbc_noteFieldComboBox);
		noteFieldComboBox.setModel(noteFieldComboBoxModel);
		tempoSpinnerModel.setMinimum(MIN_TEMPO);
		tempoSpinnerModel.setMaximum(MAX_TEMPO);
		tempoSpinnerModel.setValue(MAX_TEMPO / 2);

		try {
			synthesizer = MidiSystem.getSynthesizer();
			synthesizer.open();
			channel = synthesizer.getChannels()[CHANNEL];
			for(Instrument inst : synthesizer.getDefaultSoundbank().getInstruments()) {
				instrumentComboBoxModel.addElement(inst);
            }
		} catch (Exception e) {
			
		}
	}
	
	private void generateSequencer() {

		String velocityField = (String)noteFieldComboBoxModel.getSelectedItem();
		String noteField = (String)noteFieldComboBoxModel.getSelectedItem();
		
		int newUpper = 100;
		int newLower = 0;

		int oldVelocityUpperbound = DataHelper.getUpperBound(data, velocityField);
		int oldVelocityLowerbound = DataHelper.getLowerBound(data, velocityField);
		int oldNoteUpperbound = DataHelper.getUpperBound(data, noteField);
		int oldNoteLowerbound = DataHelper.getLowerBound(data, noteField);
		
		try {
            sequencer = MidiSystem.getSequencer();
			Instrument currInst = (Instrument)instrumentComboBoxModel.getSelectedItem();
			synthesizer.loadInstrument(currInst);
			channel.programChange(currInst.getPatch().getProgram());
            Sequence sequence = new Sequence(Sequence.PPQ, 10);
            Track track = sequence.createTrack();
        	
            // For instrument change
            ShortMessage myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.PROGRAM_CHANGE, CHANNEL, currInst.getPatch().getProgram(), 0);
            track.add(new MidiEvent(myMsg, 0));

            int tick = 0;
            for(HashMap<String, Object> currElem : data) {
				int newVelocity = (int)DataHelper.normalizeNumber(newLower,
						newUpper, oldVelocityLowerbound, oldVelocityUpperbound,
						Data.getNumberValue(currElem.get(velocityField))
								.intValue());
				int newNote = (int)DataHelper.normalizeNumber(newLower,
						newUpper, oldNoteLowerbound, oldNoteUpperbound,
						Data.getNumberValue(currElem.get(noteField))
								.intValue());
	            try {
	            	
	            	myMsg = new ShortMessage();
					myMsg.setMessage(ShortMessage.NOTE_ON, CHANNEL,
							AudioHelper.getNote(newLower, newNote),
							AudioHelper.getVelocity(newLower, newVelocity));
	                track.add(new MidiEvent(myMsg, tick));

	            } catch (Exception ex) { ex.printStackTrace(); }            	
                
                tick+=4;
            }
            sequencer.setSequence(sequence);
            sequencer.open();
            sequencer.setTempoInBPM(tempoSpinnerModel.getNumber().intValue());
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void deleteSequencer() {
		sequencer = null;
	}

	@Override
	public long play() {
		if(sequencer == null) {
			generateSequencer();
		}	
		sequencer.start();
		return sequencer.getMicrosecondLength();
	}
	@Override
	public void pause() {
        sequencer.stop();
	}
	@Override
	public void stop() {
        sequencer.stop();
        deleteSequencer();
	}
	@Override
	protected void updatePanel() {
		for (String dataType : data.get(0).keySet()) {
			velocityFieldComboBoxModel.addElement(dataType);
			noteFieldComboBoxModel.addElement(dataType);
		}
	}
	
	private static class AudioHelper {
		private static final int MIN_VELOCITY = 30;
		private static final int MAX_VELOCITY = 90;

		private static final ArrayList<String> PENTATONIC_NOTES = new ArrayList<String>(); 
		private static final ArrayList<Integer> PENTATONIC_NOTE_NUMBERS = new ArrayList<Integer>();
		static {
			PENTATONIC_NOTES.add("C");
			PENTATONIC_NOTES.add("D");
			PENTATONIC_NOTES.add("E");
			PENTATONIC_NOTES.add("G");
			PENTATONIC_NOTES.add("A");
			for (int noteNum = 0; noteNum < 128; noteNum++) {
	        	if(PENTATONIC_NOTES.contains(noteNumToNoteString(noteNum))) {
	        		PENTATONIC_NOTE_NUMBERS.add(noteNum);
	        	}
	        }
		}
		
		private static int getNote(int lowerBound, int number) {
			int i = (number - lowerBound) % PENTATONIC_NOTE_NUMBERS.size();
			return PENTATONIC_NOTE_NUMBERS.get(i);
		}
		private static int getVelocity(int lowerBound, int noteNumber) {
			return ((noteNumber - lowerBound) % (MAX_VELOCITY - MIN_VELOCITY)) + MIN_VELOCITY;
		}
		private static String noteNumToNoteString(int noteNumber){
	        String notes = "C C#D D#E F F#G G#A A#B ";
	        return notes.substring((noteNumber % 12) * 2,
	                (noteNumber % 12) * 2 + 2).trim();
		}
		@SuppressWarnings("unused")
		private static int noteNumToOctave(int noteNumber) {
	        return noteNumber / 12 - 1;
		}
	}
}
