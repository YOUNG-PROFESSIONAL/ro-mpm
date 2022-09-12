package ro.mpm.graph.bean;

import ro.mpm.tache.bean.Tache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphMPM {
    private Map<Tache, List<Tache>> adjVertices;
    public GraphMPM() {
        adjVertices = new HashMap<Tache, List<Tache>>();
    }
    public Map<Tache, List<Tache>> getAdjVertices() {
        return adjVertices;
    }

    public Tache addTache(Tache v){
        this.adjVertices.putIfAbsent(v,new ArrayList<Tache>());
        return v;
    }
    public void removeTache(String label){
        for (Tache k : this.adjVertices.keySet())
            this.adjVertices.remove(k);
    }
    public void addEdge(Tache v1, Tache v2){
         this.adjVertices.get(v1).add(v2);
    }
    public void removeEdge(Tache v1, Tache v2){
        List<Tache> eV1 = this.adjVertices.get(v1);
        if(eV1 != null) eV1.remove(v2);
    }
    public void createGraph(List<Tache> taches){
    
        // Create Vertices
        addTache(new Tache("deb",0,null,""));
        for(Tache tache : taches){
            if(tache.getTacheA() == null ||tache.getTacheA().isEmpty() ) 
                tache.setTacheA("deb");
            addTache(tache);
        }
        addTache(new Tache("fin",0,"",null));
        
        // Create Edge
        for(Tache v1 : this.getAdjVertices().keySet()){
            if(v1.getLabel().contentEquals("deb")){
                for(Tache v2 : this.getAdjVertices().keySet())
                    if(v2.getTacheA() != null && v2.getTacheA().contentEquals("deb")){
                        if(v1.getTacheS() == null || v1.getTacheS().isEmpty())
                            v1.setTacheS(v2.getLabel());
                        else
                            v1.setTacheS(v1.getTacheS()+","+v2.getLabel());
                        addEdge(v1, v2);}
            }
            else if(v1.getLabel().contentEquals("fin")){
                for(Tache v2 : this.getAdjVertices().keySet()){
                    if(v2 != v1){
                    int i = 0;
                    for(Tache tA : this.getAdjVertices().keySet()){
                        if(tA.getTacheA()!= null && tA != v1)
                            for(int s=0;s<tA.getTacheA().length();s++){
                                if(tA.getTacheA().charAt(s) == v2.getLabel().charAt(0) ) i++;
                            }
                        }
                    if(i<=0){
                        System.out.println(v2.getTacheS());
                        v1.setTacheA(v2.getLabel());
                        v2.setTacheS(v1.getLabel());
                        addEdge(v2, v1);
                    }
                    }
                    
                }
            }
            else {
                for(Tache v2 : this.getAdjVertices().keySet()){
                    if(v2 != v1 && v2.getTacheA() != null && !v2.getTacheA().contentEquals("deb") 
                        && !v2.getTacheA().contentEquals("fin")){
                        for (int i = 0; i < v2.getTacheA().length(); i++) {
                            if(v2.getTacheA().charAt(i) == v1.getLabel().charAt(0)){
                                if(v1.getTacheS() == null || v1.getTacheS().length()<=0) v1.setTacheS(v2.getLabel());
                                else v1.setTacheS(v1.getTacheS() + "," + v2.getLabel());
                                addEdge(v1, v2);
                            }
                        }
                    }
                }

            }

        }
        
    }
}
