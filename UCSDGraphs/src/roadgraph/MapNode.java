package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

class MapNode implements Comparable<MapNode> {
    GeographicPoint location;
    List<MapEdge> edges;
    double distance;
    double estimatedDistance;

    public MapNode(GeographicPoint location) {
        this.location = location;
        this.edges = new ArrayList<>();
        this.distance = Double.POSITIVE_INFINITY;
        this.estimatedDistance = Double.POSITIVE_INFINITY;
    }

    public void addEdge(MapEdge edge) {
        edges.add(edge);
    }

    public List<MapEdge> getEdges() {
        return edges;
    }

    public GeographicPoint getLocation() {
        return location;
    }

    @Override
    public int compareTo(MapNode other) {
        return Double.compare(this.estimatedDistance, other.estimatedDistance);
    }
}
