public class Mage extends PartyMember {
    private int spellAttack;
    private int spellSlots;
    public Mage (String characterName, int health, int baseAttack, int spellAttack, int spellSlots) throws IllegalArgumentException{
        super(characterName, health, baseAttack);
        if (spellAttack < baseAttack || spellAttack > (baseAttack * 2)){
            throw new IllegalArgumentException("Invalid spell attack");
        } else{
            this.spellAttack = spellAttack;
        }
        if (spellSlots < 0 || spellSlots > (health / 2)){
            throw new IllegalArgumentException("Invalid spellSlots");
        } else{
            this.spellSlots = spellSlots;
        }
    }

    @Override
    public String toString(){
        return super.toString() + ", Spell Attack: " + spellAttack + ", Spell slots: " + spellSlots;
    }
    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        Mage compareMage = (Mage) o;
        return super.equals(o) && this.spellSlots == compareMage.spellSlots && this.spellAttack == compareMage.spellAttack;
    }
    @Override
    public int questLevel(){
        return super.questLevel() + (spellSlots * spellAttack);
    }
}
