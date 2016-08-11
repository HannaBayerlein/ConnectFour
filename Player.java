package ConnectFourGame;

import java.awt.Color;

public class Player {
	private int nbr;
	private String name;
	private Color color;

	/**
	* Player that plays
	* Contains a number, a nick name and a color
	* 
	* @param nbr of the player
	* @param name nick name of the player
	* @param color background of the squares on the board
	*/
	public Player(int nbr, String name, Color color) {
		this.nbr = nbr;
		this.name = name;
		this.color = color;
		
	}
	
	/**
	* Returns the players number
	* @return the players number
	*/
	public int getNbr(){
		return nbr;
	}
	
	/**
	* Gives the player a nick name
	* @param nickName
	*/
	public void setName(String nickName){
		name = nickName;
	}
	
	/**
	* Returns the players name
	* @return the players name
	*/
	public String getName(){
		return name;
	}
	
	/**
	* Returns the players color
	* @return the players color
	*/
	public Color getColor(){
		return color;
		
	}
	

}