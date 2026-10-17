package com.ejemplo.decorador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class CatalogoCafe<T extends Bebida> {
    private final String nombre;
    private final List<T> bebidas = new ArrayList<>();
    public CatalogoCafe(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) throw new BebidaInvalidaException("Nombre inválido");
        this.nombre = nombre;
    }
    public void agregarBebida(T bebida) {
        if (bebida == null) throw new BebidaInvalidaException("Bebida nula");
        bebidas.add(bebida);
    }
    public List<T> obtenerBebidas() { return Collections.unmodifiableList(bebidas); }
    public void mostrarMenu() {
        System.out.println("Menú: " + nombre);
        for (T bebida : bebidas) bebida.mostrarDetalle();
    }
}
