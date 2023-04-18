package edu.neu.info6205.algorithms.Christofides;

public class Edge implements Comparable<Edge> { 
    int src, dest;
    double weight; 
    
    public Edge(int src, int dest, double weight) {
    	this.src = src;
    	this.dest = dest;
    	this.weight = weight;
    }
    
    public Edge() {}
    
    @Override
	public int compareTo(Edge otherEdge) 
    { 
    	 return Double.compare(this.weight, otherEdge.weight);
    }	 

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	} 
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge other = (Edge) obj;
        return this.src == other.src && this.dest == other.dest && this.weight == other.weight;
    }
} 