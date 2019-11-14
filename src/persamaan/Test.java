/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persamaan;

/**
 *
 * @author adityapeen
 */
public class Test {
    public static void tampilkan(Chromosome[] pop){
        double[] terbaik = Process.terbaik(pop);
        double[] terburuk = Process.terburuk(pop);
        for(int i=0;i<pop.length;i++){
            System.out.print(pop[i].GetAngka() +" F : "+pop[i].GetFitness());
            if(i==(int)terbaik[1]) System.out.println(" <-- Terbaik");
            else if(i==(int)terburuk[1]) System.out.println(" <-- Terburuk");
            else System.out.println();
        }
    }
    
    public static void main(String[] args){
        int jpop = 6;
        int jgen = 4;
        double pc = 0.25;
        double pm = 0.10;
        int best;
        String[][] Eq = {{"3","4","6","1","40"},{"a","b","c","d",""}};
        Process.SetEq(Eq[0]);
        
        int a[] = {12, 5, 23, 8};
        int b[] = {2, 21, 18, 3};
        int c[] = {10, 4, 13, 14};
        //int c[] = {7, 5, 3, 1};
        int d[] = {20, 1, 10, 6};
        int e[] = {1, 4, 13, 19};
        int f[] = {20, 5, 17, 1};
        Gen tes[] = new Gen[a.length];
        Gen tes2[] = new Gen[a.length];
        Gen tes3[] = new Gen[a.length];
        Gen tes4[] = new Gen[a.length];
        Gen tes5[] = new Gen[a.length];
        Gen tes6[] = new Gen[a.length];
        for(int i=0;i<a.length;i++){
            tes[i]=new Gen(a[i]);
            tes2[i]=new Gen(b[i]);
            tes3[i]=new Gen(c[i]);
            tes4[i]=new Gen(d[i]);
            tes5[i]=new Gen(e[i]);
            tes6[i]=new Gen(f[i]);
        }
        
        
        //satu.BangkitkanChromosome();
        Chromosome generasi[] = new Chromosome[6];
        generasi[0] = new Chromosome(tes);
        generasi[1] = new Chromosome(tes2);
        generasi[2] = new Chromosome(tes3);
        generasi[3] = new Chromosome(tes4);
        generasi[4] = new Chromosome(tes5);
        generasi[5] = new Chromosome(tes6);
        
        //System.out.println(Process.HitungTotalFitness(generasi));
        int iterasi = 0;
        double prob[][]= new double[generasi.length][generasi.length];
        do{
        System.out.println("\t\tGENERASI KE-"+iterasi);
        System.out.println("========POPULASI AWAL & PROBABILITAS=========");
        Process.HitungProbabilitas(generasi, prob);
        double[] terbaik = Process.terbaik(generasi);
        for(int i=0;i<generasi.length;i++){
            System.out.print(generasi[i].GetAngka() +" F= "+generasi[i].GetFitness() +" P="+prob[0][i] +"\t| "+ prob[1][i]);
            if(i!=(int)terbaik[1]) System.out.println();
            else System.out.println(" <-- Terbaik");
        }
        Chromosome seleksi[] = new Chromosome[generasi.length];
        double R[]={0.201, 0.284, 0.099, 0.822, 0.398, 0.501};
        
        System.out.println("========SELEKSI=========");
        Process.Seleksi(generasi, prob, seleksi);
        for(int i=0; i<seleksi.length; i++){
            System.out.println(R[i] +"\t"+ prob[1][i] +"\t= "+ seleksi[i].GetAngka());
        }
        
        System.out.println("========CROSSOVER=========");
        Chromosome[] individu = new Chromosome[jpop];
        Chromosome[] anak;
        for(int i=0; i<jpop; i+=2){
            anak = Process.crossOver(seleksi[i], seleksi[i+1], pc);
            individu[i] = anak[0];
            individu[i+1] = anak[1];
        }
        
        tampilkan(individu);
        
        System.out.println("========MUTASI=========");
        for(int i=0; i<jpop; i++){
            individu[i] = Process.mutasi(individu[i], pm);
            //Process.mutasi(individu[i], pm);
   
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
        System.out.println("-----------------------------------");
        System.out.print(" Persamaan >>");
        System.out.println("\t"+Eq[0]+"a + "+ Eq[1]+"b + "+Eq[2]+"c + "+Eq[3]+"d = "+Eq[4]);
        System.out.println(" Solusi ditemukan pada Generasi ke-"+(iterasi-1));
        System.out.print(" Solusi : ");
        System.out.print(" a="+solusi[0]);
        System.out.print(" b="+solusi[1]);
        System.out.print(" c="+solusi[2]);
        System.out.println(" d="+solusi[3]);
        System.out.println("-----------------------------------");
                

    }
    
}
