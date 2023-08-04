package ro.mpm.graph;

import java.util.ArrayList;
import java.util.List;

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

       // System.out.println("Source : " + src.getLabel() + " Destination : " + dst.getLabel());

        path.add(src);

        DFS (graph, src, dst, path);
    }

    public void DFS (GraphMPM graph, Tache src , Tache dst, List<Tache> path) {

        if (src == dst) {
          //  System.out.print("\nPath : " );
            for (Tache node : path){
                path2.add(node);
                if(node.getLabel().contentEquals("fin")){
                   paths.add(path2);
                   path2 = new ArrayList<>(){};
                }
              //  System.out.print(node.getLabel() + " - ");
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
        
        depthFirstTraversal(graph);
        dateAuPlutot();
        cheminCritique();
        dateAuPlutard();
        margeDeRetard();
        
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
            }
        return graphMPM;
    }

    @Override
    public GraphMPM dateAuPlutard() {
        int i = 0;
        int dateTard = 0;
        for(List<Tache> taches : paths){
            i = taches.size()-1;
            for (Tache tache : taches) {
                if(tache.getLabel().contentEquals("fin")){
                    dateTard = tache.getDateAuPlutot();
                    tache.setDateAuPlutard(dateTard);
                    break;
                }
            }
            
            for (;true;i--) {
                dateTard = dateTard - taches.get(i).getDuree();
                    if(taches.get(i).getDateAuPlutard() == null){
                        taches.get(i).setDateAuPlutard(dateTard);    
                    }
                    if(taches.get(i).getDateAuPlutard() != null){ // si date au plutard diff null
                        if(taches.get(i).getDateAuPlutard() >=dateTard){
                            taches.get(i).setDateAuPlutard(dateTard);
                        }
                   }
                   if(i == 0) break;
                }            
        }
        

        return graphMPM;
    }

    @Override
    public GraphMPM margeDeRetard() {
        for(List<Tache> taches : paths){
            for(Tache tache : taches){
                tache.setMarge(tache.getDateAuPlutard(), tache.getDateAuPlutot());
                //System.out.print(tache.getLabel()+". "+tache.getDateAuPlutard() + "| ");
            }
           // System.out.println();
        }
        return graphMPM;
    }
    
 
}
