package ro.mpm.graph;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.springframework.stereotype.Service;

import ro.mpm.graph.bean.GraphMPM;
import ro.mpm.tache.bean.Tache;

@Service
public class IGraphMPMService implements GraphMPMService {

    private List<Tache> path;

    public void FindAllPaths (GraphMPM graph, Tache src, Tache dst) {

        // Clear previously stored paths
        path = new ArrayList<Tache>();
        path.clear();

        System.out.print("Source : " + src.getLabel() + " Destination : " + dst.getLabel());

        path.add(src);

        DFS (graph, src, dst, path);
    }

    public void DFS (GraphMPM graph, Tache src , Tache dst, List<Tache> path) {

        if (src == dst) {
            System.out.print("\nPath : " );
            for (Tache node : path)
                 System.out.print(node.getLabel() + " ");
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
        depthFirstTraversal(graph);
        return graph;
    }

    @Override
    public List<ArrayList<Tache>> depthFirstTraversal(GraphMPM graph ) {
    
        // TODO Auto-generated method stub
        Tache rootTache = null;
        Tache endTache = null;
        ArrayList<ArrayList<Tache>> visited = new ArrayList<ArrayList<Tache>>(){};
        Stack<Tache> stack = new Stack<Tache>();

        for(Tache deb : graph.getAdjVertices().keySet()){
            if(deb.getLabel().contentEquals("deb"))
                rootTache = deb;
            if(deb.getLabel().contentEquals("fin"))
                endTache = deb;
        }
        
        FindAllPaths(graph,rootTache,endTache);

       /*  stack.push(rootTache);
        int i = 0;
        while(!stack.isEmpty()){
            Tache root = stack.pop();
            for(Tache v1 : graph.getAdjVertices().keySet()){
                if(v1 != rootTache){
                    if(v1.getTacheA().contentEquals("fin")){
                        visited.get(i).add(v1);
                        i++;
                        visited.add(new ArrayList<>());
                        stack.pop();stack.push(rootTache);
                    }
                    else {
                        
                    }
                }
            }
        }*/

        return visited;
    }
    
 
}
