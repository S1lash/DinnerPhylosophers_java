/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinnerphylosophers;

/**
 *
 * @author Ilya
 */
public class DinnerPhylosophers {
    
    public static void main(String[] args) {
       int num = 5; // кол-во философов
       Oficient oficient = new Oficient(num);
       oficient.OpenRestaurant();
    }
}
