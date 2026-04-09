<script setup>
import { ref } from "vue";

const nombre = ref("Producto ejemplo");
const imagen = ref("/img/producto1.jpg");
const descripcion = ref("Descripción del producto");
const cantidad = ref(1);

const agregarAlCarrito = () => {
  const producto = {
    nombre: nombre.value,
    imagen: imagen.value,
    descripcion: descripcion.value,
    cantidad: cantidad.value,
    precio: 100
  };

  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];
  carrito.push(producto);
  localStorage.setItem("carrito", JSON.stringify(carrito));

  alert("Producto agregado al carrito");
};
</script>

<template>
  <form @submit.prevent="agregarAlCarrito">
    <h4>{{ nombre }}</h4>
    <img :src="imagen" alt="Producto" style="max-width: 200px;" />
    <p>{{ descripcion }}</p>
    <input type="number" v-model="cantidad" min="1" class="form-control mb-2" />
    <button type="submit" class="btn btn-primary">Agregar al carrito</button>
  </form>
</template>
