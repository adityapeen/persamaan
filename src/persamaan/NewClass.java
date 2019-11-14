/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persamaan;

import java.util.Arrays;

/**
 *
 * @author adityapeen
 */
public class NewClass {
    public static void main(String[] args){
        String equation="1a+2B+3c+4d+5e=30";
        String[] array = equation.split("[+=]");
        System.out.println(equation);
//        for(String e : array){
////            String replaceAll = e.replaceAll("[^0-9?!\\.]", "");
//            String replaceAll = e.replaceAll("[^a-z^A-Z?!\\.]", "");
//            System.out.println(e+" "+replaceAll+" | ");
//        }
        System.out.println(array.length);
        String Eq[][] = new String[2][array.length] ;
        System.out.println(array[0].length());
        System.out.println(Eq[1].length);
        for(int i=0;i<Eq[1].length;i++){
            Eq[0][i] = array[i].replaceAll("[^0-9?!\\.]", "");
            Eq[1][i] = array[i].replaceAll("[^a-z^A-Z?!\\.]", "");
        }
//        for(int i=0;i<array[0].length();i++){
//            Eq[0][i] = array[i].replaceAll("[^0-9?!\\.]", "");
//            Eq[1][i] = array[i].replaceAll("[^a-z^A-Z?!\\.]", "");
//        }
//        for(String[] e : Eq){
//            System.out.println(e[0]+" "+e[1]);
//        }
//        
        System.out.println(Arrays.toString(Eq[0]));
        System.out.println(Arrays.toString(Eq[1]));
    }
}
