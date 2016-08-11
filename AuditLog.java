package ConnectFourGame;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AuditLog {
	private List<String> activities;
	private File activityFile;

	/**
     * Audit log that saves all activity from the FourConnectGUI
     */
	public AuditLog() {
		activities = new ArrayList<String>();
		String homeFolder = System.getProperty("user.home");
		activityFile = new File(homeFolder, "ConnectFourLog.txt");
		if (activityFile.exists() && !activityFile.isDirectory()) {
		readLog();
		}
	}

	
	  /**
     * Reads all the activities from each game via the list games
     * Uses a FileWriter to write over the activities to the file activityFile
     * @param games a list of all the activities from a game
     */
	public void writeLog(List<String> games) {

		for (int i = 0; i < games.size(); i++) {
			activities.add(games.get(i));
		}
		try {
			FileWriter fileWriter = new FileWriter(activityFile);
			BufferedWriter out = new BufferedWriter(fileWriter);
			for (int j = 0; j < activities.size(); j++) {
				out.write(activities.get(j));
				out.newLine();
			}
			activities.clear();
			out.close();
	
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	 /**
     * Uses a FileReader to read all the activities from previous games from activityFile 
     * Adds each line on activityFile in the list activities
     */
	public void readLog() {
			FileReader fileReader;
			try {
				fileReader = new FileReader(activityFile);
				BufferedReader in = new BufferedReader(fileReader);
				String line = null;
				try {
					while ((line = in.readLine()) != null) {
						activities.add(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	
	/**
	 * Open and shows the file activityFile
	 */
	public void open() {
		if (!Desktop.isDesktopSupported()) {
			System.out.println("Desktop is not supported");
			return;
		}

		Desktop desktop = Desktop.getDesktop();
		if (activityFile.exists()) {
			try {
				desktop.open(activityFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//	public void print() {
//		for (int i = 0; i < activities.size(); i++) {
//			System.out.println(activities.get(i));
//			System.out.println();
//		}
//	}

}
