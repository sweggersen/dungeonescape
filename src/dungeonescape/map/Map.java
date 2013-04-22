package dungeonescape.map;

import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.character.Player;
import dungeonescape.helper.Levels;
import dungeonescape.helper.PlayerImg;
import dungeonescape.level.Collision;
import dungeonescape.level.CollisionMisc;
import dungeonescape.level.Door;
import dungeonescape.level.Ground;
import dungeonescape.level.Level;
import dungeonescape.level.Misc;
import dungeonescape.level.Moveable;
import dungeonescape.level.TopLayer;

public class Map {

	// CAMERA //
	public Camera camera;
	private int location = Levels.ROOMA;
	private int new_location = Levels.ROOMA;

	// LEVELS //
	private Ground ground;
	private Collision collision;
	private CollisionMisc collisionMisc;
	private Misc misc;
	private Moveable moveable;
	private TopLayer topLayer;
	private Player player;
	private Door door;

	// REMEMBER POSITION //
	private ArrayList<Moveable> moveables;

	// PLAYER GRAPHIC //
	private GObject player_graphic;
	
	// OVERLAY TEXT
	public OverlayText overlayText;
	
	// MINI MAP
	MiniMap miniMap;
	double measure;

	public Map(Player player) {
		this.player = player;
		this.camera = new Camera(this);
		this.moveables = new ArrayList<Moveable>();
		this.overlayText = new OverlayText(this);

		Main.main.setBackground(Color.BLACK);
		Main.main.setSize(camera.getWindowX() * Camera.IMG_SIZE
				* Camera.IMG_SCALE, camera.getWindowY() * Camera.IMG_SIZE
				* Camera.IMG_SCALE);
		printMap();
	}

	public void add(GObject object) {
		Main.main.add(object);
	}

	public void remove(GObject object) {
		Main.main.remove(object);
	}

	public void removeAll() {
		Main.main.removeAll();
	}

	public Player getPlayer() {
		return this.player;
	}

	public void restartCurrentLevel() {
		printMap();
	}

	public int getLevelCode() {
		return location;
	}

	public void resetLevelCode() {
		this.location = -1;
	}
	
	public void setLevelCode(int location) {
		this.new_location = location;
		printMap();	
	}
	
	public int getMapX() {
		return ground.getSizeX();
	}

	public int getMapY() {
		return ground.getSizeY();
	}
	
	public void printMap() {
		overlayText.printLables();
		initializeNewLevels(this.new_location);
		redrawViews();
	}

	private void initializeNewLevels(int level) {
		this.ground = new Ground(level);
		this.collision = new Collision(level);
		this.collisionMisc = new CollisionMisc(level);
		this.moveable = getMoveableToUse(level);
		this.misc = new Misc(level);
		this.door = new Door(level);
		this.topLayer = new TopLayer(level);
		this.miniMap = new MiniMap(this, camera, ground, collision, collisionMisc, misc, moveable);
		this.camera.setCamera();
		this.player.setPlayer(camera, collision, collisionMisc, moveable, door, location);
		this.location = new_location;
	}

	private void printLevel(Level level) {
		GImage image = null;
		int position_x = 0;
		int position_y = 0;
		for (int i = camera.getOffsetY(); i < camera.getVisibleTilesY(); i++) {
			position_x = 0;
			for (int j = camera.getOffsetX(); j < camera.getVisibleTilesX(); j++) {
				image = level.printMap(i, j, position_x, position_y);
				if (image != null) {
					add(image);
				}
				position_x++;
			}
			position_y++;
		}
	}

	public void redrawViews() {
		removeAll();
		camera.getCamera();
		printLevel(ground);
		printLevel(collision);
		printLevel(collisionMisc);
		printLevel(misc);
		printLevel(moveable);
		printCharacter(player);
		printLevel(topLayer);
		miniMap.printMiniMap();
		if (!overlayText.isDoneShowing()) {
			overlayText.printLablesAgain();
		}
	}

	public void printCharacter(Player player) {
		player_graphic = player.getCharacterView(PlayerImg.PLAYER_MAP_SIZE_LARGE, 0);
		add(player_graphic);
	}

	private Moveable getMoveableToUse(int level) {
		for (Moveable m : moveables)
			if (m.getLevelCode() == level)
				return m;
		Moveable moveable = new Moveable(level);
		moveable.setLevelCode(level);
		moveables.add(moveable);
		return moveable;
	}

	public void moveToNextRoom() {
		int code = door.getCell(player.getCharacterX(), player.getCharacterY());
		if (door.isDoor(code)) {
			setLevelCode(code);
		}
	}
}
