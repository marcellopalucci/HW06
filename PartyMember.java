public abstract class PartyMember extends IllegalArgumentException {
    private String characterName;
    private int health;
    private int baseAttack;
    public PartyMember(String characterName, int health, int baseAttack) throws IllegalArgumentException{
        if (characterName.isBlank() || characterName == null){
            throw new IllegalArgumentException("Name is black or null.");
        } else {
            this.characterName = characterName;
        }
        if (health < 0 || health > 100){
            throw new IllegalArgumentException("Invalid health :)");
        } else {
            this.health = health;
        }
        if (baseAttack < 0 || baseAttack > 2 * health) {
            throw new IllegalArgumentException("Invalid base attack");
        } else {
            this.baseAttack = baseAttack;
        }
    }
    @Override
    public String toString(){
        return "Name: " + characterName
                + ", Health: " + health
                + ", Base Attack: " + baseAttack;
    }
    @Override
    public boolean equals (Object o){
        if (o == null){
            return false;
        }
        PartyMember compareO = (PartyMember) o;
        if (! (compareO instanceof PartyMember)){
            return false;
        }
        return (compareO.characterName.equals(this.characterName) &&
                compareO.health == this.health &&
                compareO.baseAttack == this.baseAttack);
    }
    public int questLevel(){
            return ((health + baseAttack) / 2);
    }
}
