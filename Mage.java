public class Mage extends PartyMember {
    private int spellAttack;
    private int spellSlots;
    public Mage (String characterName, int health, int baseAttack, int spellAttack, int spellSlots) throws IllegalArgumentException{
        super(characterName, health, baseAttack);
        if (spellAttack < super.getBaseAttack() || spellAttack > (super.getBaseAttack() * 2)){
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage compareMage = (Mage) o;
        return (super.getCharacterName().equals(compareMage.getCharacterName()) &&
                super.getHealth() == compareMage.getHealth() &&
                super.getBaseAttack() == compareMage.getBaseAttack() &&
                this.spellAttack == compareMage.spellAttack &&
                this.spellSlots == compareMage.spellSlots);
    }
    @Override
    public int questLevel(){
        return super.questLevel() + (spellSlots * spellAttack);
    }

    public int getSpellAttack() {
        return spellAttack;
    }

    public void setSpellAttack(int spellAttack) {
        this.spellAttack = spellAttack;
    }

    public int getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(int spellSlots) {
        this.spellSlots = spellSlots;
    }
}
