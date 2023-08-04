package ro.mpm.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.mpm.Response;
import ro.mpm.tache.bean.Tache;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/graph")
public class GraphMPMController {
    @Autowired
    private GraphMPMService graphMPMService;


    @PostMapping()
    public ResponseEntity<Response> createGraph(@RequestBody List<Tache> taches) throws IOException{


        System.out.println(taches);
        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("graph",(graphMPMService.createGraphMPM(taches))))
                        .build()
        );
    }

    @GetMapping("/date-au-plutot")
    public ResponseEntity<Response> getGraphDateAuPlutot(){

        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("graph",graphMPMService.dateAuPlutot()))
                        .build()
        );
    }

    @GetMapping("/chemin-critique")
    public ResponseEntity<Response> getGraphCheminCritique(){

        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("graph",graphMPMService.cheminCritique()))
                        .build()
        );
    }

    @GetMapping("/date-au-plutard")
    public ResponseEntity<Response> getGraphDateAuPlutard(){

        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("graph",graphMPMService.dateAuPlutard()))
                        .build()
        );
    }

    @GetMapping("/marge-tard")
    public ResponseEntity<Response> getGraphMargeTard(){

        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("graph",graphMPMService.margeDeRetard()))
                        .build()
        );
    }
}
