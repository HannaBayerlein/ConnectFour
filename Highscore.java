package ConnectFourGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Highscore {
	private File topFile;
	private List<String> winners;
	private Map<String, Integer> topList;
	private String winner;

	
	/**
	 * Keeps the highscore and shows who is the best
	 */
	public Highscore() {
		winner = new String();
		winners = new ArrayList<String>();
		topList = new TreeMap<String, Integer>();
		String homeFolder = System.getProperty("user.home");
		topFile = new File(homeFolder, "Winners.txt");
		if (topFile.exists() && !topFile.isDirectory()) {
			readHighscore();
		}
	}

	 /**
     * Uses a FileReader to read all the names from previous games from topFile 
     * Adds each line from topFile in the list winners
     */
	public void readHighscore() {
		FileReader fileReader;
		try {
			fileReader = new FileReader(topFile);
			BufferedReader in = new BufferedReader(fileReader);
			String line = null;
			try {
				while ((line = in.readLine()) != null) {
					winners.add(line);
				}

			} catch (IOException e) {

				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	  /**
     * Reads all the winners from previous games via the list winners
     * Uses a FileWriter to write over the winners to the file topFile
     * @param the winner from the previous game
     */
	public void writeHighscore(String s) {

		winners.add(s);
		scanForNbrOne();
		try {
			FileWriter fileWriter = new FileWriter(topFile);
			BufferedWriter out = new BufferedWriter(fileWriter);
			for (int j = 0; j < winners.size(); j++) {
				out.write(winners.get(j));
				out.newLine();
			}
			winners.clear();
			out.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * Returns the player with most wins
	 * @return the players name and number of wins
	 */
	public String getHighscore() {
		return winner;
	}

	/**
	 * Scans the map for keys (names of the winners) and count who has got the most wins
	 */
	public void scanForNbrOne() {
		int max = Integer.MIN_VALUE;
		String key = "";
		String value = "";
		for (int j = 0; j < winners.size(); j++) {
			int n = 0;
			if (topList.containsKey(winners.get(j))) {
				n = topList.get(winners.get(j));
				n++;
				topList.remove(winners.get(j));
				topList.put(winners.get(j), n);

			} else {
				topList.put(winners.get(j), 1);
			}

			for (String name : topList.keySet()) {

				key = name;
				value = topList.get(name).toString();
				if (topList.get(key) > max) {
					max = topList.get(key);

				}

				winner = key + " har vunnit " + value + " gånger";

			}

		}

	}
}
