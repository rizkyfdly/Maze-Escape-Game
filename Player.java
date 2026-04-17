public class Player {
    private int row, col;
    private int hp;
    private int attack;

    public Player(int row, int col) {
        this.row = row;
        this.col = col;
        this.hp = 100;
        this.attack = 20;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > 100) hp = 100;
    }

    public boolean isAlive() {
        return hp > 0;
    }
}