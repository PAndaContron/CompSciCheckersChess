package general;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	private final static int BUFFER_SIZE = 128000;
	private static File soundFile;
	private static AudioInputStream audioStream;
	private static AudioFormat audioFormat;
	private static SourceDataLine sourceLine;
	
	public static void main(String[] args)
	{
		MusicPlayer opus = new MusicPlayer("checkersbgm/opus.wav");
		opus.play();
		while(opus.isPlaying());
	}

	public static void playMusic(String filename) {

		/**
		 * @param filename
		 *            the name of the file that is going to be played
		 */
		String strFilename = filename;

		try {
			soundFile = new File(strFilename);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		audioFormat = audioStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		sourceLine.start();

		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioStream.read(abData, 0, abData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}

		sourceLine.drain();
		sourceLine.close();
	}
	
	public static void playMusicAsync(String filename)
	{
		File audioFile = new File(filename);
		 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
        
        System.out.println("Method finished");
	}
	
	private String filename;
	private Clip clip;
	
	public MusicPlayer(String fileName)
	{
		setFileName(fileName);
	}
	
	public boolean setFileName(String newFileName)
	{
		filename = newFileName;
		try
		{
			File audioFile = new File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	public boolean isPlaying()
	{
		return clip.isActive();
	}
	
	public void play()
	{
		play(1);
	}
	
	public void play(int times)
	{
		clip.loop(times-1);
		while(!isPlaying());
	}
	
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		while(!isPlaying());
	}
	
	public void pause()
	{
		clip.stop();
	}
	
	public void stop()
	{
		pause();
		clip.setMicrosecondPosition(0);
	}
}
