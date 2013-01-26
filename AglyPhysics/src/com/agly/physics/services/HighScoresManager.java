package com.agly.physics.services;

import com.agly.physics.AglyPhysics;
import com.agly.physics.domain.HighScores;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;


public class HighScoresManager {

	// the location of the high scores data file
	private static final String HIGHSCORES_DATA_FILE = "data/highscores-v1.json";

	private HighScores highScores;

	public HighScoresManager() {

	}

	/**
	 * Retrieves the player's high scores, creating one if needed.
	 */
	public HighScores retrieveHighScores() {
		// create the handle for the high scores data file
		FileHandle highScoresDataFile = Gdx.files.local(HIGHSCORES_DATA_FILE);
		Gdx.app.log(AglyPhysics.LOG, "Retrieving high scores from: "
				+ highScoresDataFile.path());

		// if the high scores is already loaded, just return it
		if (highScores != null)
			return highScores;

		// create the JSON utility object
		Json json = new Json();

		// check if the high scores data file exists
		if (highScoresDataFile.exists()) {

			// load the high scores from the data file
			try {

				// read the file as text
				String highScoresAsText = highScoresDataFile.readString()
						.trim();

				// decode the contents (if it's base64 encoded)
				if (highScoresAsText.matches("^[A-Za-z0-9/+=]+$")) {
					Gdx.app.log(AglyPhysics.LOG,
							"Persisted high scores is base64 encoded");
					highScoresAsText = Base64Coder
							.decodeString(highScoresAsText);
				}

				// restore the state
				highScores = json.fromJson(HighScores.class, highScoresAsText);

			} catch (Exception e) {

				// log the exception
				Gdx.app.error(AglyPhysics.LOG,
						"Unable to parse existing high scores data file", e);

				// recover by creating a fresh new high scores data file;
				// note that the player will lose all game progress
				highScores = new HighScores();
				persist(highScores);

			}

		} else {
			// create a new high scores data file
			highScores = new HighScores();
			persist(highScores);
		}

		// return the result
		return highScores;
	}

    /**
     * Persists the given high scores.
     */
    protected void persist(
        HighScores highScores )
    {
        // create the handle for the high scores data file
        FileHandle highScoresDataFile = Gdx.files.local( HIGHSCORES_DATA_FILE );
        Gdx.app.log( AglyPhysics.LOG, "Persisting high scores in: " + highScoresDataFile.path() );

        // create the JSON utility object
        Json json = new Json();

        // convert the given high scores to text
        String highScoresAsText = json.toJson( highScores );

        // encode the text
        if( ! AglyPhysics.DEV_MODE ) {
            highScoresAsText = Base64Coder.encodeString( highScoresAsText );
        }

        // write the high scores data file
        highScoresDataFile.writeString( highScoresAsText, false );
    }
	
	
    /**
     * Persists the high scores.
     * <p>
     * If no highScores is available, this method does nothing.
     */
    public void persist()
    {
        if( highScores != null ) {
            persist( highScores );
        }
    }
}
