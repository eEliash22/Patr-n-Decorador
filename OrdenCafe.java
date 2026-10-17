package com.ejemplo.decorador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class OrdenCafe<T extends Bebida> {
    private final List<T> items = new ArrayList<>();
    public void agregarItem(T item) {
        if (item == null) throw new BebidaInvalidaException("No se puede agregar una bebida nula");
        items.add(item);
    }
    public void eliminarItem(T item) { items.remove(item); }
    public List<T> obtenerItems() { return Collections.unmodifiableList(items); }
    public double calcularTotal() { return items.stream().mapToDouble(Bebida::costo).sum(); }
    public void vaciar() { items.clear(); }
}
