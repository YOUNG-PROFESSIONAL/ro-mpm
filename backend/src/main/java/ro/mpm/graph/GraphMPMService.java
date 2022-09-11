package ro.mpm.graph;

import java.util.ArrayList;
import java.util.List;

import ro.mpm.graph.bean.GraphMPM;
import ro.mpm.tache.bean.Tache;

public interface GraphMPMService {
    public GraphMPM createGraphMPM(List<Tache> taches);
    List<ArrayList<Tache>> depthFirstTraversal(GraphMPM graph);
}
