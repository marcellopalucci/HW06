public class Warrior extends PartyMember {
    private String weapon;
    private int armorClass;
    public Warrior(String characterName, int health, int baseAttack, String weapon, int armorClass){
        super(characterName, health, baseAttack);
        this.weapon = weapon;
        this.armorClass = armorClass;
    }

    @Override
    public String toString(){
        return super.toString() + ", Weapon: " + weapon + ", Armor Class: " + armorClass;
    }
    @Override
    public boolean equals (Object o){
        if (o == null){
            return false;
        }
        Warrior compareO = (Warrior) o;
        return (super.equals(o) &&
                this.armorClass == compareO.armorClass&&
                this.weapon.equals(compareO.weapon));
    }
    @Override
    public int questLevel(){
        int maceAddOn = (weapon.equals("mace")) ? 2 : 0;
        return armorClass + super.questLevel() + maceAddOn;
    }
}

