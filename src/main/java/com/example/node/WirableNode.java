package com.example.node;

import java.util.HashSet;
import java.util.Set;

import com.example.Wire;

import lombok.Getter;

@Getter
public abstract class WirableNode extends ActiveNode {
    protected Set<Wire> wires = new HashSet<>();
    
    protected WirableNode(String name) {
        super(name);
    }

    public void wire(Wire wire) {
        wires.add(wire);
    }

    /**
     * ex) terminal input node, terminal input node 서로 연결 할 수 없음, 실제 구현 클래스에서 구현해주시면 됩니다.
     */
    public abstract boolean canWire(WirableNode other);

    public static boolean isWire(WirableNode node1, WirableNode node2) {
        for (var wire : node1.getWires()) if (node2.getWires().contains(wire)) return true;

        return false;
    }
}
