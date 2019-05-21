import java.util.ArrayList;

class Genome {
  ArrayList<connectionGene> genes = new  ArrayList<connectionGene>();//a list of connections between nodes which represent the NN
  ArrayList<Node> nodes = new ArrayList<Node>();//list of nodes
  int inputs;
  int outputs;
  int layers =2;
  int nextNode = 0;
  int biasNode;

  ArrayList<Node> network = new ArrayList<Node>();//a list of the nodes in the order that they need to be considered in the NN
  //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  Genome(int in, int out) {
    //set input number and output number
    inputs = in;
    outputs = out;

    //create input nodes
    for (int i = 0; i<inputs; i++) {
      nodes.add(new Node(i));
      nextNode ++;
      nodes.get(i).layer =0;
    }

    //create output nodes
    for (int i = 0; i < outputs; i++) {
      nodes.add(new Node(i+inputs));
      nodes.get(i+inputs).layer = 1;
      nextNode++;
    }

    nodes.add(new Node(nextNode));//bias node
    biasNode = nextNode; 
    nextNode++;
    nodes.get(biasNode).layer = 0;
  }



  //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  //returns the node with a matching number
  //sometimes the nodes will not be in order
  Node getNode(int nodeNumber) {
    for (int i = 0; i < nodes.size(); i++) {
      if (nodes.get(i).number == nodeNumber) {
        return nodes.get(i);
      }
    }
    return null;
  }


  //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  //adds the conenctions going out of a node to that node so that it can acess the next node during feeding forward
  void connectNodes() {

    for (int i = 0; i< nodes.size(); i++) {//clear the connections
      nodes.get(i).outputConnections.clear();
    }

    for (int i = 0; i < genes.size(); i++) {//for each connectionGene 
      genes.get(i).fromNode.outputConnections.add(genes.get(i));//add it to node
    }
  }

  //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  //feeding in input values into the NN and returning output array
  float[] feedForward(float[] inputValues) {
    //set the outputs of the input nodes
    for (int i =0; i < inputs; i++) {
      nodes.get(i).outputValue = inputValues[i];
    }
    nodes.get(biasNode).outputValue = 1;//output of bias is 1

    for (int i = 0; i< network.size(); i++) {//for each node in the network engage it(see node class for what this does)
      network.get(i).engage();
    }

    //the outputs are nodes[inputs] to nodes [inputs+outputs-1]
    float[] outs = new float[outputs];
    for (int i = 0; i < outputs; i++) {
      outs[i] = nodes.get(inputs + i).outputValue;
    }

    for (int i = 0; i < nodes.size(); i++) {//reset all the nodes for the next feed forward
      nodes.get(i).inputSum = 0;
    }

    return outs;
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  //sets up the NN as a list of nodes in order to be engaged 

  void generateNetwork() {
    connectNodes();
    network = new ArrayList<Node>();
    //for each layer add the node in that layer, since layers cannot connect to themselves there is no need to order the nodes within a layer

    for (int l = 0; l< layers; l++) {//for each layer
      for (int i = 0; i< nodes.size(); i++) {//for each node
        if (nodes.get(i).layer == l) {//if that node is in that layer
          network.add(nodes.get(i));
        }
      }
    }
  }
}