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
public class Chromosome {
    public int jmlGen;
    public Gen[] Gens;
    public double fitness;
    
    public Chromosome(int jmlGen){
        this.jmlGen = jmlGen;
        this.Gens = new Gen[jmlGen];
        BangkitkanChromosome();
        HitungFitness();
    }
    
    public Chromosome(Gen[] Gens){
        this.Gens = Gens;
        this.jmlGen = Gens.length;
        HitungFitness();
    }
    
    private void BangkitkanChromosome(){
        for(int i=0;i<this.jmlGen;i++){
            Gens[i]= new Gen();
        }
    }
    
    private void HitungFitness(){
        Process p = new Process();
        this.fitness = (double) p.HitungFitness(Gens);
    }
    
    public void setGen(int i, Gen g){
        this.Gens[i].setGen(g);
        HitungFitness();
    }
    
    public double GetFitness(){
        return fitness;
    }
    
    public int GetJmlGen(){
        return jmlGen;
    }
    
    public Gen getGen(int i){
        return Gens[i];
    }
    
    public String GetAngka(){
        StringBuilder tulisan = new StringBuilder();
        for(Gen gen : Gens){
            if(gen.getAngka()<10){
                tulisan.append("0").append(gen.getAngka()).append(" ");
            }
            else {
                tulisan.append(gen.getAngka()).append(" ");
            }
        }
        return tulisan.toString();
    }
    
    public int[] GetPerAbjad(){
        int[] hasil= new int[Gens.length];
        for(int i=0; i<Gens.length;i++){
            hasil[i]=Gens[i].getAngka();
        }
        return hasil;
    }
    
}
