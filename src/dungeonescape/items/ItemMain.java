package dungeonescape.items;

import acm.graphics.GObject;
import dungeonescape.character.Action;
import dungeonescape.helper.Type;

public abstract class ItemMain implements Item {

	private String name;
	private int type;
	private int value;
	private int dps;
	private int protection;
	private int healing;
	private int required;
	private GObject view;
	private Action action;
	private boolean isActive;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDPS() {
		return this.dps;
	}

	public void setDPS(int dps) {
		this.dps = dps;
	}

	public int getProtection() {
		return this.protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	public int getHealing() {
		return this.healing;
	}

	public void setHealing(int healing) {
		this.healing = healing;
	}

	public int getRequired() {
		return this.required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public GObject getView() {
		return this.view;
	}

	public void setView(GObject gobject) {
		this.view = gobject;
	}

	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void doAction() {

	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getMSS() {
		return (getType(1));
	}

	public String getSTSS() {
		return (getType(2));
	}

	private String getType(int iter) {
		switch (getType()) {
		case Type.POTION_RARE:
		case Type.POTION_EPIC:
		case Type.POTION_LEGENDARY:
			switch (iter) {
			case 1:
				return "Healing: " + getHealing();
			case 2:
				return "Value" + getValue();
			}
		case Type.POTION_ATTACK_SPEED:
			switch (iter) {
			case 1:
				return "Damage: " + getDPS();
			case 2:
				return "Value" + getValue();
			}
		case Type.POTION_PROTECTION:
			switch (iter) {
			case 1:
				return "Protection: " + getProtection();
			case 2:
				return "Value" + getValue();
			}
			case Type.SHIELD_RARE:
			case Type.SHIELD_EPIC:
			case Type.SHIELD_LEGENDARY:
				switch (iter) {
				case 1:
					return "Protection: " + getProtection();
				case 2:
					return "Lvl Required: " + getRequired();
				}
			case Type.HEAD_COMMON:
			case Type.HEAD_RARE:
			case Type.HEAD_EPIC:
			case Type.HEAD_LEGENDARY:
				switch (iter) {
				case 1:
					return "Protection: " + getProtection();
				case 2:
					return "Lvl Required: " + getRequired();
				}
			case Type.CHEST_COMMON:
			case Type.CHEST_RARE:
			case Type.CHEST_EPIC:
			case Type.CHEST_LEGENDARY:
				switch (iter) {
				case 1:
					return "Protection: " + getProtection();
				case 2:
					return "Lvl Required: " + getRequired();
				}
			case Type.HANDS_RARE:
			case Type.HANDS_EPIC:
			case Type.HANDS_LEGENDARY:
				switch (iter) {
				case 1:
					return "Protection: " + getProtection();
				case 2:
					return "Lvl Required: " + getRequired();
				}
			case Type.FEET_EPIC:
			case Type.FEET_LEGENDARY:
				switch (iter) {
				case 1:
					return "Protection: " + getProtection();
				case 2:
					return "Lvl Required: " + getRequired();
				}
			case Type.SWORD_COMMON:
			case Type.SWORD_RARE:
			case Type.SWORD_EPIC:
			case Type.SWORD_LEGENDARY:
				switch (iter) {
				case 1:
					return "Damage: " + getDPS();
				case 2:
					return "Lvl Required: " + getRequired();
				}
			case Type.STAFF_COMMON:
			case Type.STAFF_RARE:
			case Type.STAFF_EPIC:
			case Type.STAFF_LEGENDARY:
				switch (iter) {
				case 1:
					return "Damage: " + getDPS();
				case 2:
					return "Lvl Required: " + getRequired();
				}
			case Type.KEY_RARE:
			case Type.KEY_EPIC:
			case Type.KEY_LEGENDARY:
				switch (iter) {
				case 1:
					return "";
				case 2:
					return "";
			}
		}
		return "";
	}

}
