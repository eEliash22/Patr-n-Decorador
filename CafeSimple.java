package com.ejemplo.decorador;
public class CafeSimple implements Bebida {
    private final String descripcion;
    private final double precio;
    public CafeSimple(String descripcion, double precio) {
        if (descripcion == null || descripcion.trim().isEmpty() || !Double.isFinite(precio) || precio < 0)
            throw new BebidaInvalidaException("Descripción o precio inválido");
        this.descripcion = descripcion;
        this.precio = precio;
    }
    @Override public String getDescripcion() { return descripcion; }
    @Override public double costo() { return precio; }
    @Override public void mostrarDetalle() { System.out.println(getDescripcion() + " - Q" + String.format(java.util.Locale.US, "%.2f", costo())); }
}
