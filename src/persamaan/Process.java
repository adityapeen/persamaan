/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persamaan;

import java.text.DecimalFormat;
import java.util.Random;
import static persamaan.Interface.tampilkan;

/**
 *
 * @author adityapeen
 */
public class Process {
    private static int[] equation = {1,2,3,4,30};
    public static Gen acuan;
    public static int jmlGen;
    
    public static void BangkitkanPopulasi(Chromosome[] pop, int jmlGen){   
        for (int i=0; i<pop.length; i++) {
            pop[i] = new Chromosome(jmlGen);
        }
    }
    
    public static double format(double desimal){
        double val=0;
        try{
            DecimalFormat df = new DecimalFormat("#.####");
            val = Double.valueOf(df.format(desimal));
            
        }
        catch(NumberFormatException e){
            System.out.println(e);
        }
        return val;
    }
    
    private int Eval(Gen[] Gens){
        int Gen[] = new int[Gens.length];
        for(int i=0; i<Gens.length;i++){
            Gen[i] = Gens[i].getAngka();
//            System.out.print(Gens[i].getAngka() +" ");
        }
        int has = 0;
        for(int i=0;i<Gens.length;i++){
            has += equation[i]*Gen[i];
        }
        has = has-equation[equation.length-1];   
        //int res = ( equation[0]*Gen[0] + equation[1]*Gen[1] + equation[2]*Gen[2] + equation[3]*Gen[3] ) - equation[4];
        int ev = Math.abs(has);
        return ev;
    }
    
    public double HitungFitness(Gen[] Gens){
        int r = Eval(Gens);
        double fitness= (double) 1/(1+r);
//        System.out.println(fitness);
        fitness = format(fitness);
        return fitness;
    }
    
    public static double HitungTotalFitness(Chromosome[] pop){
        double total=0;
        for (Chromosome pop1 : pop) {
            total += pop1.GetFitness();
        }
//        System.out.println("total fitness "+total);
        total=format(total);
        return total;
    }
    
    public static void  HitungProbabilitas(Chromosome[] pop, double[][] prob){
        double total = HitungTotalFitness(pop);
        for(int i=0;i<prob.length;i++){
            prob[0][i]=format(pop[i].GetFitness()/total);
//            if(i==0) prob[1][i]=+ prob[0][i];
//            else prob[1][i]= prob[0][i-1] + prob[0][i];
        }
        total=0;
        for(int i=0;i<prob[0].length;i++){
            total += prob[0][i];
            prob[1][i]=format(total);
        }
    }
    
    public static double[][]  HitungProbabilitas(Chromosome[] pop){
        double[][] prob = new double[pop.length][pop.length];
        double total = HitungTotalFitness(pop);
        for(int i=0;i<prob.length;i++){
            prob[0][i]=format(pop[i].GetFitness()/total);
//            if(i==0) prob[1][i]=+ prob[0][i];
//            else prob[1][i]= prob[0][i-1] + prob[0][i];
        }
        total=0;
        for(int i=0;i<prob[0].length;i++){
            total += prob[0][i];
            prob[1][i]=format(total);
        }
        return prob;
    }
    
    public static void Seleksi(Chromosome[] pop, double[][] prob, Chromosome[] sel, double[] r){
        for(int i=0;i<pop.length;i++){
            for(int j=0;j<prob[0].length;j++){
                //double r=Math.random();
                if(r[i]<prob[1][j]){
                    sel[i]=pop[j];
                    break;
                }
            }
        }
    }
    
    public static void Seleksi(Chromosome[] pop, double[][] prob, Chromosome[] sel){
        for(int i=0;i<pop.length;i++){
            double r=Math.random();
            for(int j=0;j<prob[0].length;j++){
                if(r<prob[1][j]){
                    sel[i]=pop[j];
//                    System.out.println(format(r) +"\t"+ prob[1][j] +"\t= "+ sel[i].GetAngka());
                    break;
                }
            }
        }
    }
    
    public static Chromosome[] crossOver(Chromosome induk1, Chromosome induk2, double pc){
        Chromosome anak1 = new Chromosome(induk1.jmlGen);
        Chromosome anak2 = new Chromosome(induk2.jmlGen);
        
        for(int i=0; i<induk1.GetJmlGen(); i++){
            double r = Math.random();
            if(r<=pc){
               anak1.setGen(i, induk2.getGen(i));
               anak2.setGen(i, induk1.getGen(i));
            }else{
               anak1.setGen(i, induk1.getGen(i));
               anak2.setGen(i, induk2.getGen(i));
            }
        }
        
        Chromosome[] hasil = new Chromosome[2];
        hasil[0] = anak1;
        hasil[1] = anak2;
        return hasil;
    }
    
    public static Chromosome mutasi(Chromosome induk, double pm){
        for(int i=0; i<induk.jmlGen; i++){
            double r = Math.random();
            //System.out.println("R ="+r);
            if(r <= pm){
                Random a = new Random();
                int newGen = a.nextInt(30-1)+1;
                //System.out.println(newGen);
                Gen g = new Gen(newGen);
                induk.setGen(i, g);
            }
        }
        return induk;
    }
    
    public static Chromosome[] elitisme(Chromosome[] induk, Chromosome[] anak){
        double[] Max, Min;
        Max = terbaik(induk);
        Min = terburuk(anak);
        anak[(int)Min[1]] = induk[(int)Max[1]];
        return anak;
    }
    
    public static double[] terbaik(Chromosome[] pop){
        double terbaik = pop[0].GetFitness();
        double index = 0;
        double hasil[] = new double[2];
        for(int i=1;i<pop.length;i++){
            if(pop[i].GetFitness()>terbaik){
                terbaik = pop[i].GetFitness();
                index = i;
            }
        }
        hasil[0]=terbaik;
        hasil[1]=index;
        return hasil;
    }
    
    public static double[] terburuk(Chromosome[] pop){
        double terburuk = pop[0].GetFitness();
        double index = 0;
        double hasil[] = new double[2];
        for(int i=1;i<pop.length;i++){
            if(pop[i].GetFitness()<terburuk){
                terburuk = pop[i].GetFitness();
                index = i;
            }
        }
        hasil[0]=terburuk;
        hasil[1]=index;
        return hasil;
    }
    
    
    public static void SetEq(String[] equation){
        int[] temp = new int[equation.length];
        for(int i=0; i<equation.length;i++){
            temp[i]=Integer.parseInt(equation[i]);
        }
        Process.equation = temp;
    }
    
    
    

}
