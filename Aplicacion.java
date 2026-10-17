package com.ejemplo.decorador;
public class Aplicacion {
    public static void main(String[] args) {
        Bebida pedido = new CremaDecorador(new ChocolateDecorador(new LecheDecorador(new CafeSimple("Café americano", 12.00))));
        OrdenCafe<Bebida> orden = new OrdenCafe<>();
        orden.agregarItem(pedido);
        pedido.mostrarDetalle();
        System.out.printf(java.util.Locale.US, "Total: Q%.2f%n", orden.calcularTotal());
    }
}
