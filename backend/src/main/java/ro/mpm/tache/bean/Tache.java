package ro.mpm.tache.bean;

public class Tache {
    private String label;
    private Integer duree;
    private String tacheA;
    private String tacheS;
    private Integer dateAuPlutot;
    private boolean cheminCritique;
    private Integer dateAuPlutard;
    private Integer marge;

    public Tache(){
        this.label = "";
        this.duree = 0;
        this.tacheA = "";
        this.tacheS = "";
    }
    public Tache(String label){
        this.label = label;
    }
    public Tache(String label, Integer duree){
        this.label = label;
        this.duree = duree;
    }
    public Tache(String label, Integer duree,String tacheA){
        this.label = label;
        this.duree = duree;
        this.tacheA = tacheA;
    }
    public Tache(String label, Integer duree,String tacheA,String tacheS){
        this.label = label;
        this.duree = duree;
        this.tacheA = tacheA;
        this.tacheS = tacheS;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getTacheA() {
        return tacheA;
    }

    public void setTacheA(String tacheA) {
        this.tacheA = tacheA;
    }

    public String getTacheS() {
        return tacheS;
    }

    public void setTacheS(String tacheS) {
        this.tacheS = tacheS;
    }
    
    public Integer getDateAuPlutot() {
        return dateAuPlutot;
    }
    public void setDateAuPlutot(Integer dateAuPlutot) {
        this.dateAuPlutot = dateAuPlutot;
    }
    public Integer getDateAuPlutard() {
        return dateAuPlutard;
    }
    public void setDateAuPlutard(Integer dateAuPlutard) {
        this.dateAuPlutard = dateAuPlutard;
    }
    public boolean isCheminCritique() {
        return cheminCritique;
    }
    public void setCheminCritique(boolean cheminCritique) {
        this.cheminCritique = cheminCritique;
    }
    public Integer getMarge() {
        return marge;
    }
    public void setMarge(Integer dateAuPlutard,Integer dateAuPlutot) {
        if(dateAuPlutard != null && dateAuPlutot!= null)
            this.marge = dateAuPlutard - dateAuPlutot;
    }
    @Override
    public String toString() {
        return "{\"cheminCritique\":\"" + cheminCritique + "\", \"dateAuPlutard\":\"" + dateAuPlutard + "\", \"dateAuPlutot\":\""
                + dateAuPlutot + "\", \"duree\":\"" + duree + "\", \"label\":\"" + label + "\", \"marge\":\"" + marge + "\", \"tacheA\":\"" + tacheA
                + "\", \"tacheS\":\"" + tacheS + "\"}";
    }
}
