package ro.mpm.graph;
import java.util.List;

import ro.mpm.graph.bean.GraphMPM;
import ro.mpm.tache.bean.Tache;

public interface GraphMPMService {
    public GraphMPM createGraphMPM(List<Tache> taches);
    void depthFirstTraversal(GraphMPM graph);
    public GraphMPM dateAuPlutot();
    public GraphMPM cheminCritique();
    public GraphMPM dateAuPlutard();
    public GraphMPM margeDeRetard();
        
}
