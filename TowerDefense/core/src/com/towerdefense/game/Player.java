package com.towerdefense.game;

public class Player {
    private int MaxHP;
    private int Hp;
    private int money;
    private boolean Alive;
    public Player(int Hp,int money){
        this.Hp=Hp;
        this.MaxHP=Hp;
        this.money=money;
        this.Alive=true;
    }

    public boolean isAlive() {
        return Alive;
    }
    public void Dies(){
        this.Alive=false;
    }

    public int getHp() {
        return Hp;
    }
    public int getMaxHP() {
        return MaxHP;
    }

    public int getMoney() {
        return money;
    }
    public void loseMoney(int money) { this.money -= money; }
    public void gainMoney(int money) { this.money += money; }

    public void LoseHp(int damage) {
        this.Hp -=damage;
    }

    public void KillBounty(int bounty) {
        this.money += bounty;
    }
}
