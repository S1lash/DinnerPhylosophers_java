/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinnerphylosophers;


public class Oficient {
    private Forks[] forks;
    private Phy[] phy;
    private int numGuests; // Number of Guests (Phylosophers)
    public boolean stop;
    
       Oficient(int numGuests){
        this.numGuests = numGuests;
        this.forks = new Forks[numGuests];
        this.phy = new Phy[numGuests];
        this.stop = false;
    } 
    
    
    private void CreateListGuests(){
        for (int i = 0; i < numGuests; i++) {
            phy[i] = new Phy(this.forks, i+1, numGuests, this.stop);
        }
    }
    
    private void Serving(){
        for (int i = 0; i < numGuests; i++) {
            this.forks[i] = new Forks(i+1);
        }
    }
    
    public void OpenRestaurant(){
        this.Serving();
        this.CreateListGuests();
    }
    
}
