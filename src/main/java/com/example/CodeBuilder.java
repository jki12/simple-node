package com.example;

import java.util.HashSet;
import java.util.Set;

import com.example.node.Node;
import com.example.node.WirableNode;

public class CodeBuilder {
    private static Set<Node> nodes = new HashSet<>();
    // private static Set<Wire> wires = new HashSet<>();

    private CodeBuilder() {
    }

    public static void add(Node node) {
        nodes.add(node);
    }

    public static void getCode() {
        getCode(true);
    }

    public static void getCode(boolean useSimpleName) {
        StringBuilder sb = new StringBuilder();

        for (var node : nodes) {
            String className = (useSimpleName ? node.getClass().getSimpleName() : node.getClass().getName());
            sb.append(String.format("%s %s = new %s()", className, node.getName(), className));

            // TODO need to test.
            if (node instanceof WirableNode) {
                Set<Wire> wires = new HashSet<>();

                for (var wire : ((WirableNode) node).getWires()) {
                    if (wire == null) continue;

                    if (!wires.contains(wire)) sb.append(String.format("Wire %s = new Wire()", wire.getId()));
                    sb.append(String.format("node.getName().wire(%s)", wire.getId()));
                }
            }
        }

        System.out.println(sb.toString());
    }
}
