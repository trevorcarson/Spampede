package com.gradescope.spampede;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * The "view" in MVC that is responsible for drawing the board on the screen.
 * 
 * @author CS60 instructors
 */
class SpampedeDisplay {

	/** The board/spampede data being drawn. */
	private final SpampedeData theData;

	/** The display where the board is drawn. */
	private final Graphics theScreen;

	/** The width of the display in pixels. */
	private final int width;

	/** The height of the display in pixels. */
	private final int height;

	/** A picture of a can of spam. */
	public static Image imageSpam;

	/**
	 * Creates a new SpampedeDisplay.
	 * 
	 * @param theBoard  the data being displayed
	 * @param theScreen the display on which to draw the board
	 * @param width     the width of the display (in pixels)
	 * @param height    the height of the display (in pixels)
	 */
	public SpampedeDisplay(SpampedeData theBoard, Graphics theScreen, int width, int height) {
		this.theScreen = theScreen;
		this.theData = theBoard;
		this.height = height;
		this.width = width;
	}

	/* -------------------- */
	/* Displaying the board */
	/* -------------------- */

	/**
	 * Re-draws the board, spam, and snake (but not the buttons).
	 */
	public void updateGraphics() {
		// Draw the background -- DO NOT REMOVE!
		this.clear();

		// Draw the title
		this.displayTitle();
		int startX = (this.width-Preferences.NUM_CELLS_WIDE*Preferences.CELL_SIZE)/2;
		int startY = startX;
		// Draw the board
		for(int i = 0; i < Preferences.NUM_CELLS_WIDE; i+= 1) { //50
			for(int j = 0; j < Preferences.NUM_CELLS_TALL; j+= 1) { //30
				this.drawSquare(i*Preferences.CELL_SIZE + startX,
						j*Preferences.CELL_SIZE + startY,
						theData.getCellColor(j,i));
			}
		}

		// Display an image, just for fun
		if (SpampedeDisplay.imageSpam != null) {
			this.theScreen.drawImage(SpampedeDisplay.imageSpam, 200, 370, null);
		}

		// Draw the game-over message, if appropriate
		if (this.theData.getGameOver()) {
			this.displayGameOver();
		}

	}

	/**
	 * Draws a cell-sized square with its upper-left corner at the specified pixel
	 * coordinates (i.e. x pixels to the right and y pixels below the upper-left
	 * corner) on the display.
	 * 
	 * @param x         the x-coordinate, between 0 and this.width-1 inclusive
	 * @param y         the y-coordinate, between 0 and this.height-1 inclusive
	 * @param cellColor the color of the square being drawn
	 */
	private void drawSquare(int x, int y, Color cellColor) {
		this.theScreen.setColor(cellColor);
		this.theScreen.fillRect(x, y, Preferences.CELL_SIZE, Preferences.CELL_SIZE);
	}

	/**
	 * Draws the background. DO NOT MODIFY!
	 */
	private void clear() {
		this.theScreen.setColor(Preferences.COLOR_BACKGROUND);
		this.theScreen.fillRect(0, 0, this.width, this.height);
		this.theScreen.setColor(Preferences.TITLE_COLOR);
		this.theScreen.drawRect(0, 0, this.width - 1, Preferences.GAMEBOARDHEIGHT - 1);
	}

	/* ------------ */
	/* Text Display */
	/* ------------ */

	/**
	 * Displays the title of the game.
	 */
	private void displayTitle() {
		this.theScreen.setFont(Preferences.TITLE_FONT);
		this.theScreen.setColor(Preferences.TITLE_COLOR);
		this.theScreen.drawString(Preferences.TITLE, Preferences.TITLE_X, Preferences.TITLE_Y);
	}

	/**
	 * Displays the game-over message.
	 */
	private void displayGameOver() {
		this.theScreen.setFont(Preferences.GAME_OVER_FONT);
		this.theScreen.setColor(Preferences.GAME_OVER_COLOR);
		this.theScreen.drawString(Preferences.GAME_OVER_TEXT, Preferences.GAME_OVER_X, Preferences.GAME_OVER_Y);
	}

}
