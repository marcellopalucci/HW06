public class Warrior extends PartyMember {
    private String weapon;
    private int armorClass;
    public Warrior(String characterName, int health, int baseAttack, String weapon, int armorClass){
        super(characterName, health, baseAttack);
        if (weapon.isEmpty()){
            throw new IllegalArgumentException("Invalid weapon");
        } else {
            this.weapon = weapon;
        }
        if (armorClass < 0 || armorClass > 10){
            throw new IllegalArgumentException("Invalid armor class.");
        } else {
            this.armorClass = armorClass;
        }
        this.armorClass = armorClass;
    }

    @Override
    public String toString(){
        return super.toString() + ", Weapon: " + weapon + ", Armor Class: " + armorClass;
    }
    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null) return false;
        Warrior compareO = (Warrior) o;
        return (super.equals(compareO) &&
                this.weapon.equals(compareO.weapon) &&
                this.armorClass == compareO.armorClass);
    }
    @Override
    public int questLevel(){
        int maceIncrement = (weapon.equals("mace")) ? 2 : 0;
        return armorClass + super.questLevel() + maceIncrement;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }
}

