class connectionGene{

    Node fromNode;
    Node toNode;
    float weight;
    boolean enabled = true;
    int innovationNo;//each connection is given a innovation number to compare genomes
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //constructor
    connectionGene(Node from, Node to, float w, int inno) {
        fromNode = from;
        toNode = to;
        weight = w;
        innovationNo = inno;
    }
}