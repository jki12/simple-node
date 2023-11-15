package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.nio.Buffer;
import java.util.HashSet;
import java.util.Set;

import com.example.node.*;
// import com.example.node.WirableNode;

public class CodeBuilder {
    private static final String SPACE = "    ";
    private static final String NEW_LINE = System.lineSeparator();

    private static Set<Node> nodes = new HashSet<>();

    private CodeBuilder() {
        throw new IllegalStateException();
    }

    public static void add(Node node) {
        nodes.add(node);
    }

    public static String getCode() {
        return getCode(true);
    }

    public static String getCode(boolean useSimpleName) {
        StringBuilder sb = new StringBuilder();

        String space = SPACE;

        sb.append("import com.example.node.*;");
        sb.append(NEW_LINE);
        sb.append(NEW_LINE);

        sb.append("public class Test {");
        sb.append(NEW_LINE);
        sb.append(space);
        sb.append("public static void main(String[] args) throws Exception {");
        sb.append(NEW_LINE);

        space += SPACE;

        Set<Wire> wires = new HashSet<>();
        for (var node : nodes) {
            String className = (useSimpleName ? node.getClass().getSimpleName() : node.getClass().getName());
            sb.append(String.format("%s%s %s = new %s(\"%s\");%s", space, className, node.getName(), className, node.getName(), NEW_LINE));

            if (node instanceof WirableNode) {

                for (var wire : ((WirableNode) node).getWires()) {
                    if (wire == null) continue;

                    if (!wires.contains(wire)) {
                        sb.append(String.format("%sWire %s = new Wire();%s", space, wire.getName(), NEW_LINE));
                        wires.add(wire);
                    }

                    sb.append(String.format("%s%s.wire(%s);%s", space, node.getName(), wire.getName(), NEW_LINE));
                }
            }

            // sb.append(NEW_LINE);
            sb.append(String.format("%s%s.start();%s%s", space, node.getName(), NEW_LINE, NEW_LINE));
        }

        space = SPACE;

        sb.append(space);
        sb.append("}");
        sb.append(NEW_LINE);
        sb.append("}");

        return sb.toString();
    }
}
