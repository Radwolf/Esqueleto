package com.esqueleto.esqueletosdk.test;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest extends TestCase {
    @Test
    public void testAdd() {
        // Preparar
        Set<Integer> s = new HashSet<Integer>();
        // Actuar
        s.add(42);
        // Verificar
        assertEquals(1, s.size());
    }

}