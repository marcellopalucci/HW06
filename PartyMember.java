public abstract class PartyMember extends IllegalArgumentException {
    private String characterName;
    private int health;
    private int baseAttack;
    public PartyMember(String characterName, int health, int baseAttack) throws IllegalArgumentException{
        if (characterName.isBlank()){
            throw new IllegalArgumentException("Name is blank or null.");
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartyMember compareO = (PartyMember) o;
        return (compareO.characterName.equals(this.characterName) &&
                compareO.health == this.health &&
                compareO.baseAttack == this.baseAttack);
    }
    public int questLevel(){
            return ((health + baseAttack) / 2);
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }
}
