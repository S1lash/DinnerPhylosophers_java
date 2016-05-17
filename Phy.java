/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinnerphylosophers;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Phy implements Runnable {
    Thread flow;
    public int number; 
    private int leftFork; 
    private int rightFork; 
    private Forks[] forks;
    private boolean wantEat;
    private boolean stop;
    private int maxPlaces; 
    static int countDin;
    
    public Phy(Forks[] fork, int numberOfPhy, int allPhy, boolean stop){
        this.forks = fork;
        this.number = numberOfPhy;
        this.leftFork = this.number;
        if (this.number == 1){
            this.rightFork = allPhy;
        } else {
            this.rightFork = this.number - 1;
        }
        this.wantEat = false;
        this.flow = new Thread(this, "Phy #" + this.number);
        this.stop = stop;
        this.maxPlaces = allPhy - 1;
        this.flow.start();
    }
    
    public void run(){
        Random rand = new Random();
        
        while (!stop) {
            if (!this.wantEat){
                Sleep(rand.nextInt(10));
                this.wantEat = true;
            } else{
              
                
                if (IGO(maxPlaces)) {
                  synchronized(forks[IndFork(leftFork)]){
            TakeLeftFork();
            Sleep(rand.nextInt(3));
                synchronized(forks[IndFork(rightFork)]){
                    TakeRightFork();
                    //System.out.println("Phylosopher #" + this.number + " BEGAN eat...");
                    Sleep(rand.nextInt(5));
                    //System.out.println("Phylosopher #" + this.number + " FINISHED eat!");
                    LoseRightFork();
                }
            Sleep(rand.nextInt(3));
            LoseLeftFork();
                    }
                  System.out.println("Phy #" + number + " go out!");
                  LeaveDinningRoom();
                  this.wantEat = false;
              }
                
            }
        }
    }
    
    private void TakeLeftFork(){
        forks[IndFork(leftFork)].isAlieve = true;
        System.out.println("Phy #" + this.number + " TAKE LEFT fork");
    }
    
    private void TakeRightFork(){
        forks[IndFork(rightFork)].isAlieve = true;
        System.out.println("Phy #" + this.number + " TAKE RIGHT fork");
    }
    
    private void LoseLeftFork(){
        forks[IndFork(leftFork)].isAlieve = false;
        System.out.println("Phy #" + this.number + " LOSE LEFT fork");
    }
    
    private void LoseRightFork(){
        forks[IndFork(rightFork)].isAlieve = false;
        System.out.println("Phy #" + this.number + " LOSE RIGHT fork");
    }
    
    private int IndFork (int numberOfFork){
        int num = 0;
        for (int i = 0; i < forks.length; i++) {
            if (forks[i].number == numberOfFork){
                num = i;
                break;
        }
    }
        return num;
}
    private void Sleep(int time){
        try {
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                Logger.getLogger(Phy.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public int GetInd(){
        return this.number-1;
    }
    
    private synchronized static void GoInDinninrRoom(){
        countDin++;
     }
    
    private synchronized static void LeaveDinningRoom(){
        countDin--;
     }
         
    private synchronized static boolean IGO(int maxPlace){
        if (countDin<maxPlace){
            GoInDinninrRoom();
            return true;
        }
        else{
            return false;
        }
    }
}