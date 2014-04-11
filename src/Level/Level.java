package Level;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import Level.tile.GroundTile;
import Level.tile.SolidTile;
import Level.tile.Tile;
import character.Character;

public class Level {

	private Tile[][] tiles;

	private TiledMap map;
	// a list of all characters present somewhere on this map
	private ArrayList<Character> characters;

	public Level(String level) throws SlickException {
		map = new TiledMap("data/levels/MyFirstGame.tmx", "data/img");
		characters = new ArrayList<Character>();
		loadTileMap();
	}

	private void loadTileMap() {
		// create an array to hold all the tiles in the map
		tiles = new Tile[map.getWidth()][map.getHeight()];

		int layerIndex = map.getLayerIndex("Borders");

		if (layerIndex == -1) {
			// TODO we can clean this up later with an exception if we want, but
			// because we make the maps ourselfs this will suffice for now
			System.err.println("Map does not have the layer \"Borders\"");
			System.exit(0);
		}

		// loop through the whole map
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {

				// get the tile
				int tileID = map.getTileId(x, y, layerIndex);

				Tile tile = null;

				// and check what kind of tile it is (
				switch (map.getTileProperty(tileID, "tileType", "solid")) {
				case "background":
					tile = new GroundTile(x, y);
					break;
				default:
					tile = new SolidTile(x, y);
					break;
				}
				tiles[x][y] = tile;
			}
		}
	}

	public void addCharacter(character.Character c) {
		characters.add(c);
	}
	public ArrayList<Character>  getCharacter() {
		return characters;
	}
	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void render() {
		// render the map first
		map.render(0, 0, 0, 0, 32, 32);

		// and then render the characters on top of the map
		for (character.Character c : characters) {
			c.render();
		}
	}

}