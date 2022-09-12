package ro.mpm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;

import ro.mpm.graph.bean.GraphMPM;
import ro.mpm.tache.bean.Tache;

@Service
public class IGraphMPMService implements GraphMPMService {

    private List<Tache> path= new ArrayList<>(){};
    private List<Tache> path2= new ArrayList<>(){};
    private List<List<Tache>> paths = new ArrayList<List<Tache>>(){};
    GraphMPM graphMPM = new GraphMPM();

    public void FindAllPaths (GraphMPM graph, Tache src, Tache dst) {

        // Clear previously stored paths
        path = new ArrayList<Tache>();
        path.clear();

        System.out.println("Source : " + src.getLabel() + " Destination : " + dst.getLabel());

        path.add(src);

        DFS (graph, src, dst, path);
    }

    public void DFS (GraphMPM graph, Tache src , Tache dst, List<Tache> path) {

        if (src == dst) {
            //System.out.print("\nPath : " );
            for (Tache node : path){
                path2.add(node);
                if(node.getLabel().contentEquals("fin")){
                   paths.add(path2);
                   path2 = new ArrayList<>(){};
                }
                //System.out.print(node.getLabel() + " - ");
            }
                 
        } else {
            for (Tache adjnode : graph.getAdjVertices().get(src)) {
                path.add(adjnode);
                DFS (graph, adjnode, dst, path);
                path.remove(path.size()-1);
            }
        }
    }


    @Override
    public GraphMPM createGraphMPM(List<Tache> taches) {
        // write your code here
        GraphMPM graph = new GraphMPM();
        graph.createGraph(taches);
        
        graphMPM = graph;
        
        System.out.println("\n ------+++++++++++++++++++-------");
        depthFirstTraversal(graph);
           
        
        return graph;
    }

    @Override
    public void depthFirstTraversal(GraphMPM graph ) {
    
        // TODO Auto-generated method stub
        Tache rootTache = null;
        Tache endTache = null;

        for(Tache deb : graph.getAdjVertices().keySet()){
            if(deb.getLabel().contentEquals("deb"))
                rootTache = deb;
            if(deb.getLabel().contentEquals("fin"))
                endTache = deb;
        }
        
        FindAllPaths(graph,rootTache,endTache);
    }

    @Override
    public GraphMPM dateAuPlutot() {
        int i = 0;
        int duree = 0;
        for(List<Tache> taches : paths){
            i = taches.size();
            for (int j=0; j<i; j++) {
                if(taches.get(j).getLabel().contentEquals("deb")){
                    duree=0;
                    taches.get(j).setDateAuPlutot(duree);
                }
                else{
                    duree = duree + taches.get(j-1).getDuree();
                    if(taches.get(j).getDateAuPlutot() != null){
                        if(taches.get(j).getDateAuPlutot() <= duree){
                            taches.get(j).setDateAuPlutot(duree);
                        }
                   }
                   else
                        taches.get(j).setDateAuPlutot(duree);  
                }
            }
            System.out.println();
        }

        return graphMPM;
    }

    @Override
    public GraphMPM cheminCritique() {

        int duree = 0;
        int fin = 0;
        int test = 0;
        int i = 0;
            for(List<Tache> taches : paths){
                i = taches.size();
                for(int j=0; j<i; j++){
                    if(taches.get(j).getLabel().contentEquals("deb"))
                        duree = 0;
                    else
                        duree = duree + taches.get(j-1).getDuree();
                   
                   if(taches.get(j).getLabel().contentEquals("fin")){
                        fin = taches.get(j).getDateAuPlutot();
                        test = duree;
                   }
                }
                    if(test == fin )
                        for(Tache tache : taches){
                            tache.setCheminCritique(true);
                        }

                System.out.println();
        
            }
        return graphMPM;
    }

    @Override
    public GraphMPM dateAuPlutard() {
        int i = 0;
        int duree = 0;
        for(List<Tache> taches : paths){
            i = taches.size()-1;
            for (; true; i--) {
                System.out.println("i : " + i);
                if(taches.get(i).getDateAuPlutard() != null){
                    if(taches.get(i).getDateAuPlutard() >= duree){
                        taches.get(i).setDateAuPlutard(duree);
                    }
               }
                
                if(taches.get(i).getLabel().contentEquals("fin")){
                    duree = taches.get(i).getDateAuPlutot();
                    taches.get(i).setDateAuPlutard(duree);
                   
                }
                else{
                    duree = duree - taches.get(i).getDuree();
                    System.out.println("Duree : " + duree);
                    taches.get(i).setDateAuPlutard(duree);
                    System.out.println("Tache : " + taches.get(i).getLabel());
                }
                    
                if(i == 0) break;
            }
            System.out.println();
        }

        return graphMPM;
    }

    @Override
    public GraphMPM margeDeRetard() {
        for(List<Tache> taches : paths){
            for(Tache tache : taches)
                tache.setMarge(tache.getDateAuPlutard(), tache.getDateAuPlutot());
        }
        return graphMPM;
    }
    
 
}
