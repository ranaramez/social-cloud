package Graph;

import java.util.ArrayList;

public class EdgeWeightedGraph {
	
    private int V;
    private int E;
    private ArrayList<Bag<Edge>> adj;
    public ArrayList<User> users;
    
   /**
     * Create an empty edge-weighted graph with V vertices.
     */
    public EdgeWeightedGraph() {
        this.V = 0;
        this.E = 0;
        users= new ArrayList<User>();
        adj = new ArrayList<Bag<Edge>>();
       
    }



   /**
     * Return the number of vertices in this graph.
     */
    public int V() {
        return V;
    }

   /**
     * Return the number of edges in this graph.
     */
    public int E() {
        return E;
    }


   /**
     * Add the edge e to this graph.
     */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        
        ((Bag<Edge>) adj(v)).add(e);
        ((Bag<Edge>) adj(w)).add(e);
        
        E++;
    }
    
   /**
    * Add the vertex to this graph.
    */
    public void addVertex(User user,int dataCenterId){
    	user.profileId=V+"-"+dataCenterId;
    	V++;
    	users.add(user);
    	adj.add(new Bag<Edge>());
    	
    }


   /**
     * Return the edges incident to vertex v as an Iterable.
     * To iterate over the edges incident to vertex v, use foreach notation:
     * <tt>for (Edge e : graph.adj(v))</tt>.
     */
    public Iterable<Edge> adj(int v) {
        return adj.get(v);
    }

   /**
     * Return all edges in this graph as an Iterable.
     * To iterate over the edges, use foreach notation:
     * <tt>for (Edge e : graph.edges())</tt>.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // only add one copy of each self loop
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }



   /**
     * Return a string representation of this graph.
     */
    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
       // s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(users.get(v).profileId+"-" + users.get(v).name+ ": ");
            for (Edge e : adj.get(v)) {
                s.append( users.get(e.either()).name+"-" + users.get(e.other(e.either())).name + " Weight ="+  e.weight() + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

   /**
     * Test client.
     */
    public static void main(String[] args) {
    	
        
    }

}