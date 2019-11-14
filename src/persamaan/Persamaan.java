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
public class Persamaan {

    public static void tampilkan(Chromosome[] pop){
        double[] terbaik = Process.terbaik(pop);
        double[] terburuk = Process.terburuk(pop);
        for(int i=0;i<pop.length;i++){
            System.out.print(pop[i].GetAngka() +" F : "+pop[i].GetFitness());
            if(i==(int)terbaik[1]) System.out.println("\t<-- Terbaik");
            else if(i==(int)terburuk[1]) System.out.println("\t<-- Terburuk");
            else System.out.println();
        }
    }
    
    public static String[][] GetArray(String equation){
        String[] array = equation.split("[+=]");
        System.out.println(equation);
        String Eq[][] = new String[2][array.length] ;
        for(int i=0;i<Eq[1].length;i++){
            Eq[0][i] = array[i].replaceAll("[^0-9?!\\.]", "");
            Eq[1][i] = array[i].replaceAll("[^a-z^A-Z?!\\.]", "");
        }        
        System.out.println(Arrays.toString(Eq[0]));
        System.out.println(Arrays.toString(Eq[1]));
        return Eq;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String persamaan = "1a+2b+3c+4d+2e+1f+2g=60";
        String[][] Equ = GetArray(persamaan);
        int jpop = 6;
        int jgen = Equ[0].length-1;
        double pc = 0.25;
        double pm = 0.10;
        double prob[][]= new double[jpop][jpop];
        Chromosome[] generasi = new Chromosome[jpop];
        int best;
        int iterasi = 0;
//        double R[]={0.201, 0.284, 0.099, 0.822, 0.398, 0.501};
        
        Process.SetEq(Equ[0]);
        System.out.println(Arrays.toString(Equ[0]));
        System.out.println(Arrays.toString(Equ[1]));

        Process.BangkitkanPopulasi(generasi, jgen);
        
        do{
            System.out.println("\n\t\tGENERASI KE-"+iterasi);
            System.out.println("======POPULASI AWAL=======");
            Process.HitungProbabilitas(generasi, prob);
            tampilkan(generasi);

            System.out.println("=========SELEKSI==========");
            Chromosome seleksi[] = new Chromosome[jpop];
            Process.Seleksi(generasi, prob, seleksi);
            tampilkan(seleksi);

            System.out.println("========CROSSOVER=========");
            Chromosome[] individu = new Chromosome[jpop];
            Chromosome[] anak;
            for(int i=0; i<jpop; i+=2){
                anak = Process.crossOver(seleksi[i], seleksi[i+1], pc);
                individu[i] = anak[0];
                individu[i+1] = anak[1];
            }
            tampilkan(individu);

            System.out.println("=========MUTASI==========");
            for(int i=0; i<jpop; i++){
                individu[i] = Process.mutasi(individu[i], pm);
            }
            tampilkan(individu);

            System.out.println("========ELITISME=========");
            generasi = Process.elitisme(generasi, individu);
            tampilkan(generasi);
            ++iterasi;
            best = (int)Process.terbaik(generasi)[1];
        }
        while(generasi[best].GetFitness()!=1);
        System.out.println("\n Proses Selesai\n");
        int[] solusi = generasi[best].GetPerAbjad();
        System.out.println("----------------------------------------");
        System.out.print(" Persamaan >>");
        System.out.println("\t"+persamaan);
        System.out.println(" Solusi ditemukan pada Generasi ke-"+(iterasi-1));
        System.out.print(" Solusi : ");
        for(int i=0;i<Equ[0].length-1;i++){
            System.out.print(" "+Equ[1][i]+"="+solusi[i]+" ");
        }
        System.out.println("");
        System.out.println("----------------------------------------");
    }
    
}
