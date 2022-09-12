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
         List<Tache> pathTot= new ArrayList<>(){};
         List<List<Tache>> pathsTot = new ArrayList<List<Tache>>(){};

        int duree = 0;
        for(List<Tache> path: paths ){
            
            for(Tache tache : path){
               duree = duree + tache.getDuree();
               if(tache.getDateAuPlutot() != null){
                    if(tache.getDateAuPlutot() <= duree){
                        tache.setDateAuPlutot(duree);
                    }
               }
               else tache.setDateAuPlutot(duree);
               pathTot.add(tache);

               if(tache.getLabel().contentEquals("fin")){
                pathsTot.add(pathTot);
                pathTot = new ArrayList<>(){};
                    duree = 0;
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
            for(List<Tache> taches : paths){
                for(Tache tache : taches){
                   duree = duree + tache.getDuree();
                   if(tache.getLabel().contentEquals("fin")){
                        fin = tache.getDateAuPlutot();
                        test = duree;
                        duree = 0;
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
        // TODO Auto-generated method stub
        return graphMPM;
    }
    
 
}
