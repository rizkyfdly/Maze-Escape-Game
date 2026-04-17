public class Enemy {
    private int row, col;
    private int hp;
    private int attack;

    public Enemy(int row, int col) {
        this.row = row;
        this.col = col;
        this.hp = 50;
        this.attack = 10;
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

    public boolean isAlive() {
        return hp > 0;
    }
}